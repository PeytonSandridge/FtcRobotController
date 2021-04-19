package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains.LinearHDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

@TeleOp
public class NewThreadDelegatorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ThreadDelegator delegator = new ThreadDelegator(1, 5);

        SmartController controller = new SmartController(gamepad1);

        DriveTrain<LinearHDrive> drive = new DriveTrain(new DriveTrainBuilder(
                new DriveLayout("FrontLeftDrive", "BackLeftDrive", "FrontRightDrive", "BackRightDrive", hardwareMap),
                new DriverKeybinds(controller::getLeftStickY, controller::getLeftStickX),
                this::opModeIsActive));

        delegator.execute(drive);
    }
}
