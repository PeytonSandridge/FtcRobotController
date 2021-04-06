package org.firstinspires.ftc.teamcode.test;

import android.icu.util.TimeUnit;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;

import java.util.PrimitiveIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDelegator {
    private static final int CORE_THREADS = 3;
    private static final int MAX_THREADS = 70;
    private static final long ALIVE_TIME = 22L;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECOND;
    DriveTrain dt;

    ExecutorService executor;

    private ThreadDelegator(DriveTrain dt) {
        this.dt = dt;
        this.executor = Executors.newCachedThreadPool();
    }

    class ThreadDelegatorBuilder {

        DriveTrain dt;
        ExecutorService executorService;

        public ThreadDelegatorBuilder(){}

        public void addDriveTrainThread(DriveTrain dt) {
            this.dt = dt;
        }

    }


}
