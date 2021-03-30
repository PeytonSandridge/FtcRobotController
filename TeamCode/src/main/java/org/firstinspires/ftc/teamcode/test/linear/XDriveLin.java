package org.firstinspires.ftc.teamcode.test.linear;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains.LinearXDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

import java.util.function.DoubleSupplier;

public class XDriveLin extends LinearOpMode {
    LinearXDrive dt;
    DriveLayout dl;

    SmartController controller;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        dl = new DriveLayout(
                "frontLeftDrive",
                "backLeftDrive",
                "frontRightDrive",
                "backRightDrive",
                hardwareMap
        );


//        dt = new XDrive(dl);
        controller = new SmartController(gamepad1);

        Drive d = new XDriveLin.Drive("driver", controller::getLeftStickY, controller::getRightStickX, controller::getLeftStickX);

        waitForStart();
        runtime.reset();

        d.start();
        while (running()) {
            Thread.sleep(100);
        }
    }

    public synchronized boolean running() {
        return opModeIsActive();
    }

    class Drive implements Runnable {
        String threadName;
        Thread thread;

        DoubleSupplier yFun;
        DoubleSupplier wFun;
        DoubleSupplier xFun;

        Drive(String name, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun) {
            this.threadName = name;
            this.yFun = yFun;
            this.wFun = wFun;
            this.xFun = xFun;

            telemetry.addData("Creating ", threadName);
        }


        Drive(DoubleSupplier yFun, DoubleSupplier wFun) {
            this.threadName = "driver";
        }

        @Override
        public void run() {
            while (running()) {
                double y = yFun.getAsDouble();
                double w = wFun.getAsDouble();
                double x = xFun.getAsDouble();

                dt.drive(y, w, x);
            }
        }

        public void start() {
            telemetry.addData("Starting " , threadName);
            if (thread == null) {
                thread = new Thread(this, threadName);
                thread.start();
            }
        }
    }
}