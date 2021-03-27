package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.LinearDriveTrain2D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;

import java.util.function.BooleanSupplier;

public class LinearHDrive extends LinearDriveTrain2D {


    protected LinearHDrive(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }

    public LinearHDrive(LinearDriveTrainBuilder builder) {
        super(builder);
    }

    @Override
    public void drive(double y, double w) {
        // equations for calculation the power of each individual motor
        frontLeftPow = y + w;
        backLeftPow = y + w;
        frontRightPow = w - y;
        backRightPow = w - y;
    }
}
