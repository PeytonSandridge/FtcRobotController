package org.firstinspires.ftc.teamcode.test;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RobotExecutor extends AbstractExecutorService {
    // creates a control variable ctl that is in the RUNNING state and has zero workers
    private final AtomicInteger ctl     = new AtomicInteger(ctlOf(RUNNING, 0));
    // how much to shift an int so it can fit run state and worker count
    private static final int SHIFT      = Integer.SIZE - 3;
    // the maximuum worker count
    private static final int CAPACITY   = (1 << SHIFT) - 1;

    // runState is stored in the high-order bits
    // starts -1 to fit within 3 bits
    private static final int RUNNING    = -1 << SHIFT;
    private static final int SHUTDOWN   =  0 << SHIFT;
    private static final int STOP       =  1 << SHIFT;
    private static final int TIDYING    =  2 << SHIFT;
    private static final int TERMINATED =  3 << SHIFT;

    // Packing and unpacking ctl
    // c == ctl.get()
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    private static boolean runStateLessThan(int c, int s) { return c < s; }
    private static boolean runStateAtLeast(int c, int s) { return c >= s; }
    private static boolean isRunning(int c) { return c < SHUTDOWN; }

    private boolean compareAndSetIncrementWorkerCount(int expected) {
        return ctl.compareAndSet(expected, expected + 1);
    }

    private boolean compareAndSetDecrementWorkerCount(int expected) {
        return ctl.compareAndSet(expected, expected - 1);
    }

    private void decrementWorkerCount() {
        do {} while (! compareAndSetDecrementWorkerCount(ctl.get()));
    }



    private volatile int corePoolSize;

    private volatile int maxPoolSize;

    private volatile long keepAliveTime;

    private volatile int totalCompletedTasks;

    BlockingQueue<Runnable> taskQueue;



    private final ReentrantLock mainLock = new ReentrantLock();

    private final Condition termination = mainLock.newCondition();

    private final HashSet<Worker> workers = new HashSet<>();

    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                        (Thread.interrupted() &&
                                runStateAtLeast(ctl.get(), STOP))) &&
                        !wt.isInterrupted())
                    wt.interrupt();
                try {
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }



    private void processWorkerExit(Worker w, boolean completedAbruptly) {
        if (completedAbruptly) {
            decrementWorkerCount();
        }
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            totalCompletedTasks += w.completedTasks;
            workers.remove(w);
        } finally {
            mainLock.unlock();
        }

        tryTerminate();

        int c = ctl.get();
        if (runStateLessThan(c, STOP)) {
            if(!completedAbruptly) {
                int min = corePoolSize;
                if (workerCountOf(c) >= min) {
                    return;
                }
            }
            addWorker(null, false);
        }
    }


    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for(;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            if ((rs > SHUTDOWN) || !(rs == SHUTDOWN && firstTask == null && !taskQueue.isEmpty())) {
                return false;
            }

            for (;;) {
                int wc = workerCountOf(c);
                if (wc > CAPACITY || wc >= (core ? corePoolSize : maxPoolSize)) {
                    return false;
                }
                if (compareAndSetIncrementWorkerCount(c)) {
                    break retry;
                }
                c = ctl.get();
                if(runStateOf(c) != rs) {
                    continue retry;
                }
            }
        }

        boolean workerAdded = false;
        boolean workerStarted = false;
        Worker w = null;

        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (! workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;
    }


    private void addWorkerFailed(Worker w) {
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            if (w != null)
                workers.remove(w);
            decrementWorkerCount();
            tryTerminate();
        } finally {
            lock.unlock();
        }
    }


    final void tryTerminate() {
        for (;;) {
            int c = ctl.get();
            if(isRunning(c) || runStateAtLeast(c, TIDYING) || (runStateOf(c) == SHUTDOWN && !taskQueue.isEmpty())) {
                return;
            }
            if (workerCountOf(c) != 0) {
                interuptIdleWorker(true);
                return;
            }

            final ReentrantLock lock = this.mainLock;
            lock.lock();
            try {
                if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
                    ctl.set(ctlOf(TERMINATED, 0));
                    termination.signalAll();
                    return;
                }
            }finally {
                mainLock.unlock();
            }
        }
    }

    private void interuptIdleWorker(boolean oneOnly) {
        final ReentrantLock lock = this.mainLock;
        lock.lock();
        try {
            for (Worker w : workers) {
                Thread t = w.thread;
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    } finally {
                        w.unlock();
                    }
                }
                if (oneOnly) {
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private Runnable getTask() {
        boolean timedOut = false;

        for(;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            if (rs >= SHUTDOWN) {
                if (rs >= STOP || taskQueue.isEmpty()) {
                    decrementWorkerCount();
                    return null;
                }
            }

            int wc = workerCountOf(ctl.get());
            boolean timed = wc > corePoolSize;
            if (wc > maxPoolSize || (timed && timedOut) && (wc > 1 || taskQueue.isEmpty())) {
                if (compareAndSetDecrementWorkerCount(ctl.get())) {
                    return null;
                }
                continue;
            }

            try {
                Runnable task = timed ? taskQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : taskQueue.take();
                if (task != null) {
                    return task;
                }
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }

    }


    private class Worker extends AbstractQueuedSynchronizer implements Runnable {
        final Thread thread;

        Runnable firstTask;


        private Worker(Runnable runnable) {
            this.firstTask = runnable;
            completedTasks = 0;
            this.thread = getThreadFactory().newThread(this);
        }

        @Override
        public void run() {
            runWorker(this); 
        }

        public void unlock () { release(1); }
        public void lock () { acquire(1); }

        public boolean tryLock() { return tryAcquire(1); }

        public volatile int completedTasks;
    }


    private volatile ThreadFactory threadFactory;

    private ThreadFactory getThreadFactory() { return threadFactory; }
    private  void setThreadFactory(ThreadFactory factory) {
        this.threadFactory = factory;
    }



    @Override
    public void shutdown() {
    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void execute(Runnable command) {

    }
}
