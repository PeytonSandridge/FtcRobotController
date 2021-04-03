package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains.LinearHDrive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSetup extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        DriveLayout dl = new DriveLayout("L", "R", "BL", "BR", hardwareMap);
        DriveTrain<LinearHDrive> dt = new DriveTrain(new DriveTrainBuilder(dl, this::opModeIsActive));

        executor.execute(dt);
    }
}
