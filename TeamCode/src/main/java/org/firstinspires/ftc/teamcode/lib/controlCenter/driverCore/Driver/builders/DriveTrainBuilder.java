package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.io.InvalidObjectException;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveTrainBuilder {
    protected DriveConfig driveConfig;
    protected DriveLayout driveLayout;

    protected DriverKeybinds controls;
    protected Telemetry telemetry;
    protected BooleanSupplier running;
    protected String threadName;



    public DriveTrainBuilder(DriveLayout driveLayout, BooleanSupplier running) {
        factory(driveLayout, null, null, null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, BooleanSupplier running) {
        factory(driveLayout, driveConfig, null, null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, BooleanSupplier running) {
        factory(driveLayout, driveConfig, controls, null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, BooleanSupplier running) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun, xFun), null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        factory(driveLayout, null, controls, null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, BooleanSupplier running) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun, xFun), null, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, driveConfig, null, telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, null, null, telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, driveConfig, controls, telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, null, controls, telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xfun, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun, xfun), telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), telemetry, running, null);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, Telemetry telemetry, BooleanSupplier running) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun, xFun), telemetry, running, null);
    }

    public DriveTrainBuilder(DriveLayout driveLayout, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, null, null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, null, null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, controls, null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun, xFun), null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, controls, null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun, xFun), null, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, null, telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, null, telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, controls, telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun), telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DriveConfig driveConfig, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xfun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, driveConfig, new DriverKeybinds(yFun, wFun, xfun), telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun), telemetry, running, threadName);
    }
    public DriveTrainBuilder(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        factory(driveLayout, null, new DriverKeybinds(yFun, wFun, xFun), telemetry, running, threadName);
    }

    public void factory(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
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

        if (running == null) {
            throw new Error("Null BooleanSupplier running found");
        } else {
            this.running = running;
        }

        if (threadName == null) {
            this.threadName = "Drive Train";
        } else {
            this.threadName = threadName;
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

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
    public String getThreadName() {
        return threadName;
    }

    public void setRunning(BooleanSupplier running) {
        this.running = running;
    }
    public BooleanSupplier getRunning() {
        return running;
    }
}
