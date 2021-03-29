package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public abstract class LinearDriveTrain3D extends DriveTrainLinear {
    protected LinearDriveTrain3D(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }

    protected LinearDriveTrain3D(LinearDriveTrainBuilder builder) {
        super(builder);
    }

    @Override
    public void run() {
        while (running.getAsBoolean()) {
            _drive();
        }
    }


    @Override
    protected void _drive() {
        // stores the two power Vectors into respective variables
        double y = controls.yFun.getAsDouble();
        double w = controls.wFun.getAsDouble();

        applyMovementSpecificTransformations();

        drive(this.y, this.w);

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }


    public abstract void drive(double y, double w);

    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations2D();
    }
}
