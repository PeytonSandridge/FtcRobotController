package org.firstinspires.ftc.teamcode.test.linear;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;


@TeleOp
public class HDriveLin extends LinearOpMode {
    DriveTrain drive;
    DriveLayout dl;

    SmartController controller;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        dl = new DriveLayout(
                "FrontLeftDrive",
                "BackLeftDrive",
                "FrontRightDrive",
                "BackRightDrive",
                hardwareMap
        );

        dl.getFrontRight().setDirection(DcMotorSimple.Direction.REVERSE);
        dl.getBackLeft().setDirection(DcMotorSimple.Direction.REVERSE);

        controller = new SmartController(gamepad1);
        DriverKeybinds controls = new DriverKeybinds(controller::getLeftStickY, controller::getRightStickX);

        drive = new DriveTrain(new DriveTrainBuilder(dl, controls, telemetry, this::opModeIsActive));

        waitForStart();
        runtime.reset();

        drive.start();

        while (opModeIsActive()) {
            idle();
        }

    }
}

