package org.firstinspires.ftc.teamcode.test.linear;

import android.content.res.Resources.Theme;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.HDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

import java.util.concurrent.locks.Lock;

public class HDriveLin extends LinearOpMode {
    HDrive dt;
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

        dt = new HDrive(dl);
        controller = new SmartController(gamepad1);

        Drive d = new Drive();

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

        Drive(String name) {
            this.threadName = name;
            telemetry.addData("Creating ", threadName);
        }
        Drive() {
            this.threadName = "driver";
        }

        @Override
        public void run() {
            while (running()) {
                double y = controller.getLeftStickY();
                double w = controller.getRightStickX();

                dt.drive(y, w);
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
