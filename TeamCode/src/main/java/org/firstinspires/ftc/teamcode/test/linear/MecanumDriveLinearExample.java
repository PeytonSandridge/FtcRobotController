package org.firstinspires.ftc.teamcode.test.linear;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains.LinearHDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains.LinearMecanumDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

@TeleOp
public class MecanumDriveLinearExample extends LinearOpMode {
    LinearMecanumDrive drive;
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
        DriverKeybinds controls = new DriverKeybinds(controller::getLeftStickY, controller::getRightStickX, controller::getLeftStickX);

        //hDrive = new LinearDriveTrain2D(dl, controller::getLeftStickY, controller::getRightStickX, this::running);
        drive = new LinearMecanumDrive(new LinearDriveTrainBuilder(dl, controls, telemetry, this::opModeIsActive));
        waitForStart();
        runtime.reset();

        telemetry.update();
        drive.start();

        while (opModeIsActive()) {

            idle();
        }

    }
}
