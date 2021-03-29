package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public abstract class DriveTrainLinear extends DriveTrain implements Runnable{
    protected String threadName;
    protected Thread thread;
    protected BooleanSupplier running;

    protected DriveTrainLinear(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry);
        this.running = running;
        this.threadName = threadName;
    }

    protected DriveTrainLinear(LinearDriveTrainBuilder builder) {
        this(builder.getDriveLayout(), builder.getDriveConfig(), builder.getControls(), builder.getTelemetry(), builder.getRunning(), builder.getThreadName());
    }


    @Override
    public void run() {
        while (running.getAsBoolean()) {
            double y = controls.yFun.getAsDouble();
            double w = controls.wFun.getAsDouble();

            _drive();
        }
    }


    protected abstract void _drive();


    public void start() {
        if (telemetry != null) {
            telemetry.addData("Starting ", this.threadName);
        }
        if (!controls.isControlable()) {
            throw new IllegalArgumentException("Controls should not be null");
        }
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
