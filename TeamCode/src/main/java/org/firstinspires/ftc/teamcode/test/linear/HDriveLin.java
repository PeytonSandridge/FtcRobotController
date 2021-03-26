package org.firstinspires.ftc.teamcode.test.linear;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative.HDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.linear.LinearHDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

import java.util.function.DoubleSupplier;

public class HDriveLin extends LinearOpMode {
    LinearHDrive hDrive;
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


        controller = new SmartController(gamepad1);


        hDrive = new LinearHDrive(dl, controller::getLeftStickY, controller::getRightStickX, this::running);

        waitForStart();
        runtime.reset();

        hDrive.start();
        while (opModeIsActive()) {
            wait(100);
            telemetry.addData("Time passed: ", runtime.seconds() + " seconds.");
            telemetry.update();
        }

    }

    public synchronized boolean running() {
        return opModeIsActive();
    }
}

