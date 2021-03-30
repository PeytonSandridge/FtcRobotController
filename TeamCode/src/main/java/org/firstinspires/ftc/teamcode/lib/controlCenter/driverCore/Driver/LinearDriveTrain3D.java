package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public abstract class LinearDriveTrain3D extends DriveTrainLinear implements Drive3D {
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
        this.y = controls.yFun.getAsDouble();
        this.w = controls.wFun.getAsDouble();
        this.x = controls.xFun.getAsDouble();

        applyMovementSpecificTransformations();

        drive(this.y, this.w, this.x);

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }


    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations2D();
    }
}
