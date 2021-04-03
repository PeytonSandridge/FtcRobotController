package org.firstinspires.ftc.teamcode.test;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDelegator {
    DriveTrain dt;

    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ThreadDelegator(DriveTrain dt, ExecutorService executor) {
        this.dt = dt;
        this.executor = executor;
    }

    class ThreadDelegatorBuilder {

        DriveTrain dt;
        ExecutorService executorService;

        public ThreadDelegatorBuilder(){}

        public void addDriveTrainThread(DriveTrain dt) {
            this.dt = dt;
        }

        public ThreadDelegator build() {
            return new ThreadDelegator(this.dt, )
        }
    }


}
