package org.firstinspires.ftc.teamcode.test;

import android.icu.util.TimeUnit;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;

import java.util.HashSet;
import java.util.PrimitiveIterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDelegator {
    private AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int SHIFT = Integer.SIZE - 3;

    // Keeps track of the value associated with runstate
    private static final int RUNNING = -1 << SHIFT; // running: completes all tasks
    private static final int STOPPING = 0 << SHIFT; // completes current tasks and tasks in queue; does not accept new tasks
    private static final int TERMINATING = 1 << SHIFT; // Forcibly shuts down all tasks and begins termination process
    private static final int CLEANING = 2 << SHIFT; // Shuts down all work's and destroys them
    private static final int TERMINATED = 3 << SHIFT; // Delegate process is terminated

    private static final int MAX_WORKERS = 1 << SHIFT - 1; // the theoretical max number of workers allowed by the atomic Integer (not by the hardware)

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static int getWorkerCount(int ctl) {
        return ctl & MAX_WORKERS;
    }
    private static int getRunState(int ctl) {
        return ctl & ~MAX_WORKERS;
    }

    private static boolean runStateAtLeast(int ctl, int rs) {
        return getRunState(ctl) >= rs;
    }

    private static boolean isRunning(int c) {
        return c < STOPPING;
    }



    // Blocks threads trying to get from the Queue when its empty until another thread inserts a new item
    private volatile BlockingQueue<Runnable> tasks; // queue of all runnable "tasks" to execute

    private volatile ThreadFactory factory; // creates all threads
    public ThreadFactory getFactory() {
        return factory;
    } // gets thread factory

    private final HashSet<Worker> workers = new HashSet<>(); // set of all workers

    private ReentrantLock mainLock = new ReentrantLock(); // a lock used to "own" its own variables


    private final Condition termination = mainLock.newCondition();

    private int largestPoolSize; // largest pool size reached
    private int maxPoolSize; // max allowed pool size
    private int corePoolSize; // core (retainer) pool size


    private long completedTasks; // number of completed tasks


    public ThreadDelegator(int coreThreads, int maxPoolSize) {
        if (coreThreads < 0 || maxPoolSize <= 0) {
            throw new IllegalArgumentException();
        }

        this.largestPoolSize = 0;
        this.maxPoolSize = maxPoolSize;
        this.corePoolSize = coreThreads;
    }

    // increments worker count using AtomicInteger.compareAndSet()
    private boolean compareAndIncrementWorkerCount(int expected) {
        return ctl.compareAndSet(expected, expected + 1);
    }
    private boolean compareAndDecrementWorkerCount(int expected) {
        return ctl.compareAndSet(expected, expected - 1);
    }
    private void decrementWorkerCount() {
        do {} while (! compareAndDecrementWorkerCount(ctl.get()));
    }



    class Worker extends AbstractQueuedSynchronizer implements Runnable {
        Thread thread;
        Runnable task;

        Worker(Runnable task) {
            setState(-1); // cannot be interrupted (until runWorker)
            this.task = task;
            this.thread = getFactory().newThread(this); // uses threadFactory to create new thread
        }



        @Override
        public void run() {
            runWorker(this);
        }



        // Ownership states:
        // unlocked = 0
        // locked = 1

        // does this thread have exclusive access to the worker?
        protected boolean exclusiveOwnership() {
            return getState() == 1;
        }

        // acquires the worker lock only if another object hasn't already
        protected boolean tryAquire() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // attempts to release the worker lock
        protected boolean tryRelease() {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // locks worker
        protected void lock() {
            acquire(1);
        }
        // attempts to lock the worker unless something else owns it
        protected boolean tryLock() {
            return tryAquire();
        }
        // unlocks the worker
        protected void unlock() {
            release(0);
        }
        // attempts to unlock worker
        protected boolean tryUnlock() {
            return tryUnlock();
        }
    }

    // gets next task in queue
    private final Runnable getTask() {
        for(;;) {
            try {
                return tasks.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // replaces run() method in worker object
    final void runWorker(Worker w) {
        // gets current thread
        Thread thread = Thread.currentThread();
        Runnable task = w.task;
        w.task = null;

        // makes sure worker is unlocked
        w.unlock();
        while (task != null || (task = getTask()) != null) {
            w.lock();

            if (runStateAtLeast(ctl.get(), STOPPING) || thread.interrupted()) {
                tryTerminate();
            }

            // runs task
            task.run();
            // sets task to null so it can be updated
            task = null;
            // increments total number of completed tasks
            completedTasks++;
            // unlocks worker object
            w.unlock();
        }


    }

    public void execute(Runnable task) {
        // rejects null tasks
        if (task == null) {
            throw new NullPointerException();
        }

        // gets the control object
        int c = ctl.get();
        // if there are less workers than the core pool size requires, create a new worker
        if (getWorkerCount(c) < corePoolSize) {
            if (addWorker(task, true)) {
                return;
            }
            c = ctl.get();
        }

        if (isRunning(c) && tasks.offer(task)) {
            c = ctl.get();
            if (!isRunning(c) && tasks.remove(task)) {
                reject(task);
            } else if (getWorkerCount(c) == 0) {
                addWorker(null, false);
            }
        } else if (!addWorker(task, false)) {
            reject(task);
        }
    }

    // Uses the "Caller run policy" if the delegator cant run it, the caller thread will
    private void reject (Runnable task) {
        if(isRunning(ctl.get())) {
            task.run();
        }
    }


    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        while (true) {
            int c = ctl.get();
            int rs = getRunState(c);

            /*
            addWorker fails if the run state is greater than the STOPPING state
            OR
            the run state is STOPPING AND there's no first task AND the tasks queue is empty
             */
            if (rs > STOPPING || (rs == STOPPING && firstTask == null && tasks.isEmpty())) {
            return false;
            }

            for (;;) {
                int wc = getWorkerCount(c);
                if (wc >= MAX_WORKERS || wc >= (core ? corePoolSize : maxPoolSize)) {
                    return false;
                }
                if (compareAndIncrementWorkerCount(c)) {
                    break retry;
                }
                c = ctl.get();
                if (getRunState(c) != rs) {
                    continue retry;
                }
            }

        }
        boolean workerAdded = false;
        boolean workerStarted = false;

        Worker w = new Worker(firstTask);
        final Thread t = w.thread;

        final ReentrantLock lock = this.mainLock;
        lock.lock();

        int rs = getRunState(ctl.get());
        if (rs < STOPPING || rs == STOPPING && firstTask == null) {
            workers.add(w);
            int s = workers.size();
            if (s > largestPoolSize) {
                largestPoolSize = s;
            }
            workerAdded = true;
        }

        if (workerAdded) {
            t.start();
            workerStarted = true;
        }

        if (!workerStarted) {
            addWorkerFailed(w);
        }

        return  workerStarted;
    }

    final void tryTerminate() {
        for (;;) {
            int c = ctl.get();
            if (isRunning(c) ||
                    runStateAtLeast(c, CLEANING) ||
                    (getRunState(c) == STOPPING && ! tasks.isEmpty()))
                return;
            if (getWorkerCount(c) != 0) { // Eligible to terminate
                interruptIdleWorkers(true);
                return;
            }

            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                if (ctl.compareAndSet(c, ctlOf(CLEANING, 0))) {
                    ctl.set(ctlOf(TERMINATED, 0));
                    termination.signalAll();
                    return;
                }
            } finally {
                mainLock.unlock();
            }
            // else retry on failed CAS
        }
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null)
                workers.remove(w);
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    private void interruptIdleWorkers(boolean onlyOne) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
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
                if (onlyOne)
                    break;
            }
        } finally {
            mainLock.unlock();
        }
    }
}
