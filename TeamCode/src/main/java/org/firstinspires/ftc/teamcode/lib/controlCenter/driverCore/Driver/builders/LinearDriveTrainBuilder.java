package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.io.InvalidObjectException;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class LinearDriveTrainBuilder extends DriveTrainBuilder {
    protected BooleanSupplier running;
    protected String threadName;


    public LinearDriveTrainBuilder(DriveLayout driveLayout, BooleanSupplier running) {
        super(driveLayout);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, BooleanSupplier running) {
        super(driveLayout, driveConfig);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, BooleanSupplier running) {
        super(driveLayout, driveConfig, controls);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveLayout, driveConfig, yFun, wFun);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        super(driveLayout, controls);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveLayout, yFun, wFun);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, driveConfig, telemetry);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, telemetry);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, driveConfig, controls, telemetry);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, controls, telemetry);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, driveConfig, yFun, wFun, telemetry);
        factory(running, null);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, yFun, wFun, telemetry);
        factory(running, null);
    }


    public LinearDriveTrainBuilder(DriveLayout driveLayout, BooleanSupplier running, String threadName) {
        super(driveLayout);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, yFun, wFun);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveLayout, controls);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, telemetry);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, telemetry);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, controls, telemetry);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, yFun, wFun, telemetry);
        factory(running, threadName);
    }

    public LinearDriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun, telemetry);
        factory(running, threadName);
    }

    public void factory(BooleanSupplier running, String threadName) {
        super.factory(driveLayout, driveConfig, controls, telemetry);
    }

    public BooleanSupplier getRunning() {
        return running;
    }
    public void setRunning(BooleanSupplier running) {
        this.running = running;
    }

    public String getThreadName() {
        return threadName;
    }
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}

