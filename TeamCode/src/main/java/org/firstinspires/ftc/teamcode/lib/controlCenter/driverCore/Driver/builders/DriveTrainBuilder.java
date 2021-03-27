package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.io.InvalidObjectException;
import java.util.function.DoubleSupplier;

public class DriveTrainBuilder {
    protected DriveConfig driveConfig;
    protected DriveLayout driveLayout;

    protected DriverKeybinds controls;

    protected Telemetry telemetry;

    public DriveTrainBuilder(DriveLayout driveLayout) {
        factory(driveLayout, null, null, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig) {
        factory(driveLayout, driveConfig, null, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls) {
        factory(driveLayout, driveConfig, controls, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls) {
        factory(driveLayout, null, controls, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), null);
    }

    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, Telemetry telemetry) {
        factory(driveLayout, driveConfig, null, telemetry);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, Telemetry telemetry) {
        factory(driveLayout, null, null, telemetry);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry) {
        factory(driveLayout, driveConfig, controls, telemetry);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry) {
        factory(driveLayout, null, controls, telemetry);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), telemetry);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), telemetry);
    }

    public void factory(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry) {
        if (driveLayout == null) {
            throw new Error("Null DriveLayout found at DriveTrainBuilder.java on lin 90");
        } else {
            this.driveLayout = driveLayout;
        }

        if (driveConfig == null) {
            this.driveConfig = new DriveConfig();
        } else {
            this.driveConfig = driveConfig;
        }

        this.controls = controls;
        this.telemetry = telemetry;
    }

    public DriveConfig getDriveConfig() {
        return driveConfig;
    }
    public void setDriveConfig(DriveConfig driveConfig) {
        this.driveConfig = driveConfig;
    }

    public DriveLayout getDriveLayout() {
        return driveLayout;
    }
    public void setDriveLayout(DriveLayout driveLayout) {
        this.driveLayout = driveLayout;
    }

    public DriverKeybinds getControls() {
        return controls;
    }
    public void setControls(DriverKeybinds controls) {
        this.controls = controls;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
}
