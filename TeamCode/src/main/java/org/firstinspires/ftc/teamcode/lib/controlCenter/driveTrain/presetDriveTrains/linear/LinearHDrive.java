package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.Drive2D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveTrainLinear;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriverKeybinds;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class LinearHDrive extends DriveTrainLinear implements Drive2D {

    // shit ton of constructors that call super and thats it
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        super(driveConfig, driveLayout, controls, running);
    }
    public LinearHDrive(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        super(driveLayout, controls, running);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveConfig, driveLayout, yFun, wFun, running);
    }
    public LinearHDrive(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveLayout, yFun, wFun, running);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveConfig, driveLayout, controls, telemetry, running);
    }
    public LinearHDrive(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, controls, telemetry, running);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveConfig, driveLayout, yFun, wFun, telemetry, running);
    }
    public LinearHDrive(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, yFun, wFun, telemetry, running);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, controls, running, threadName);
    }
    public LinearHDrive(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveLayout, controls, running, threadName);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, yFun, wFun, running, threadName);
    }
    public LinearHDrive(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun, running, threadName);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, controls, telemetry, running, threadName);
    }
    public LinearHDrive(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, controls, telemetry, running, threadName);
    }
    public LinearHDrive(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, yFun, wFun, telemetry, running, threadName);
    }
    public LinearHDrive(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun, telemetry, running, threadName);
    }

    @Override
    public void run() {
        while (running.getAsBoolean()) {
            double y = controls.yFun.getAsDouble();
            double w = controls.wFun.getAsDouble();

            drive(y, w);
        }
    }

    @Override
    public void drive(double y, double w) {
        // stores the two power Vectors into respective variables
        this.y = y;
        this.w = w;

        applyMovementSpecificTransformations();

        // equations for calculation the power of each individual motor
        frontLeftPow = y + w;
        backLeftPow = y + w;
        frontRightPow = w - y;
        backRightPow = w - y;

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }

    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations2D();
    }
}
