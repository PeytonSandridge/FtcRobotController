package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public abstract class DriveTrainLinear extends DriveTrain implements Runnable{
    protected String threadName;
    protected Thread thread;
    protected BooleanSupplier running;

    // Required DriveTrainLinear parameters with optional DriveTrain parameters (NO TELEMETRY)
//    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, BooleanSupplier running) {
//        super(driveConfig, driveLayout);
//        this.threadName = "Linear Drive Train";
//        this.running = running;
//    }
//    public DriveTrainLinear(DriveLayout driveLayout, BooleanSupplier running) {
//        super(driveLayout);
//        this.threadName = "Linear Drive Train";
//        this.running = running;
//    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        super(driveConfig, driveLayout, controls);
        this.threadName = "Linear Drive Train";
        this.running = running;
    }
    public DriveTrainLinear(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running) {
        super(driveLayout, controls);
        this.threadName = "Linear Drive Train";
        this.running = running;
    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveConfig, driveLayout, yFun, wFun);
        this.threadName = "Linear Drive Train";
        this.running = running;
    }
    public DriveTrainLinear(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running) {
        super(driveLayout, yFun, wFun);
        this.threadName = "Linear Drive Train";
        this.running = running;
    }

    // Required DriveTrainLinear parameters with optional DriveTrain parameters with Telemetry
//    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running) {
//        super(driveConfig, driveLayout, telemetry);
//        this.threadName = "Linear Drive Train";
//        this.running = running;
//        telemetry.addData("Creating ", this.threadName);
//    }
//    public DriveTrainLinear(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running) {
//        super(driveLayout, telemetry);
//        this.threadName = "Linear Drive Train";
//        this.running = running;
//        telemetry.addData("Creating ", this.threadName);
//    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveConfig, driveLayout, controls, telemetry);
        this.threadName = "Linear Drive Train";
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, controls, telemetry);
        this.threadName = "Linear Drive Train";
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveConfig, driveLayout, yFun, wFun, telemetry);
        this.threadName = "Linear Drive Train";
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running) {
        super(driveLayout, yFun, wFun, telemetry);
        this.threadName = "Linear Drive Train";
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }

    // Optional DriveTrainLinear parameters and optional DriveTrain parameters except for Telemetry
//    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, BooleanSupplier running, String threadName) {
//        super(driveConfig, driveLayout);
//        this.threadName = threadName;
//        this.running = running;
//    }
//    public DriveTrainLinear(DriveLayout driveLayout, BooleanSupplier running, String threadName) {
//        super(driveLayout);
//        this.threadName = threadName;
//        this.running = running;
//    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, controls);
        this.threadName = threadName;
        this.running = running;
    }
    public DriveTrainLinear(DriveLayout driveLayout, DriverKeybinds controls, BooleanSupplier running, String threadName) {
        super(driveLayout, controls);
        this.threadName = threadName;
        this.running = running;
    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, yFun, wFun);
        this.threadName = threadName;
        this.running = running;
    }
    public DriveTrainLinear(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun);
        this.threadName = threadName;
        this.running = running;
    }

    // EVERYTHING
//    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running, String threadName) {
//        super(driveConfig, driveLayout, telemetry);
//        this.threadName = threadName;
//        this.running = running;
//        telemetry.addData("Creating ", this.threadName);
//    }
//    public DriveTrainLinear(DriveLayout driveLayout, Telemetry telemetry, BooleanSupplier running, String threadName) {
//        super(driveLayout, telemetry);
//        this.threadName = threadName;
//        this.running = running;
//        telemetry.addData("Creating ", this.threadName);
//    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, controls, telemetry);
        this.threadName = threadName;
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, controls, telemetry);
        this.threadName = threadName;
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveConfig, driveLayout, yFun, wFun, telemetry);
        this.threadName = threadName;
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }
    public DriveTrainLinear(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, yFun, wFun, telemetry);
        this.threadName = threadName;
        this.running = running;
        telemetry.addData("Creating ", this.threadName);
    }

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
