package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.LinearDriveTrain3D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public class LinearXDrive extends LinearDriveTrain3D {
    public LinearXDrive(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }

    public LinearXDrive(LinearDriveTrainBuilder builder) {
        super(builder);
    }

    @Override
    public void drive(double y, double w, double x) {
        this.frontLeftPow = y + w + x;
        this.backLeftPow = y + w - x;
        this.frontRightPow = w - y + x;
        this.backRightPow = w - y - x;
    }
}
