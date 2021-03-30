package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public abstract class LinearDriveTrain2D extends DriveTrainLinear implements Drive2D {


    protected LinearDriveTrain2D(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }

    protected LinearDriveTrain2D(LinearDriveTrainBuilder builder) {
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

        applyMovementSpecificTransformations();

        drive(this.y, this.w);

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }


    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations2D();
    }
}
