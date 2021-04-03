package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.util.function.BooleanSupplier;

public class DriveTrain<T extends Drive>  implements Runnable{

    protected T driver;

    protected DriveLayout driveLayout;
    protected DriveConfig driveConfig;

    protected DriverKeybinds controls;

    protected Telemetry telemetry;

    protected double y;
    protected double w;
    protected double x;

    protected double frontLeftPow = 0;
    protected double frontRightPow = 0;
    protected double backLeftPow = 0;
    protected double backRightPow = 0;

    protected String threadName;
    protected Thread thread;

    protected BooleanSupplier running;


    private static final byte CONCURRENCY = 0b0000_0001;
    private static final byte READY_TO_RUN = 0b0000_0010;
    private static final byte PASS_IN_POWER_VARIABLES = 0b0000_0100;
    private volatile byte concurrent;



    public DriveTrain(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        this.driveLayout = driveLayout;
        this.driveConfig = driveConfig;
        this.controls = controls;
        this.telemetry = telemetry;
        this.y = 0;
        this.w = 0;
        this.x = 0;
        // 
        this.concurrent = 0b00000001;
        this.running = running;
        this.threadName = threadName;
    }

    public DriveTrain(DriveTrainBuilder dTBuilder) {
        this(dTBuilder.getDriveLayout(),
                dTBuilder.getDriveConfig(),
                dTBuilder.getControls(),
                dTBuilder.getTelemetry(),
                dTBuilder.getRunning(),
                dTBuilder.getThreadName()
        );
    }

    protected void applyMotorSpecificTransformations() {
        // applies motor specific multipliers
        if (driveConfig.areMotorSpecificMultipliersEnabled()) {
            frontLeftPow *= driveConfig.getFrontLeftMult();
            frontRightPow *= driveConfig.getFrontRightMult();
            backLeftPow *= driveConfig.getBackLeftMult();
            backRightPow *= driveConfig.getBackRightMult();
        }
    }

    protected void applyMovementSpecificTransformations() {
        y *= driveConfig.getStraightMult();
        w *= driveConfig.getRotationMult();
        x *= driveConfig.getStrafeMult();
    }

    protected void normalizePowers() {
        double max = max(Math.abs(frontRightPow), Math.abs(frontLeftPow));
        max = max(max, Math.abs(backLeftPow));
        max = max(max, Math.abs(backRightPow));

        if (max > 1) {
            frontLeftPow /= max;
            frontRightPow /= max;
            backLeftPow /= max;
            backRightPow /= max;
        }
    }

    private double max(double a, double b) {
        return a > b ? a : b;
    }

    protected void  setMotorPowers() {
        normalizePowers();

        driveLayout.getBackLeft().setPower(backLeftPow);
        driveLayout.getBackRight().setPower(backRightPow);
        driveLayout.getFrontRight().setPower(frontRightPow);
        driveLayout.getFrontLeft().setPower(frontLeftPow);
    }

    public void setMotorPowers(double fLP, double bLP, double fRP, double bRP) {
        driveLayout.getBackLeft().setPower(bLP);
        driveLayout.getBackRight().setPower(bRP);
        driveLayout.getFrontRight().setPower(fRP);
        driveLayout.getFrontLeft().setPower(fLP);
    }

    public void updateInputs() {
        if (controls == null) {
            throw new Error("DriverKeybinds controls not initialized.");
        }

        if (!controls.is2D) {
            this.x = controls.xFun.getAsDouble();
        }

        this.y = controls.yFun.getAsDouble();
        this.w = controls.wFun.getAsDouble();
    }

    public void updateInputs(double y, double w, double x) {
        this.y = y;
        this.w = w;
        this.x = x;
    }

    protected void applyDriveFunction() {
        driver.drive(y, w, x);
        this.frontLeftPow = driver.frontLeftPower;
        this.frontRightPow = driver.frontLeftPower;
        this.backLeftPow = driver.backLeftPower;
        this.backRightPow = driver.backRightPower;
    }


    @Override
    public void run() {
        while (running.getAsBoolean()) {
            if ((concurrent & CONCURRENCY) != CONCURRENCY) {

            }
                updateInputs();

                applyMovementSpecificTransformations();

                applyDriveFunction();

                normalizePowers();

                applyMotorSpecificTransformations();

                setMotorPowers();
            }
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

    public void drive() {
        if ((concurrent & READY_TO_RUN) != READY_TO_RUN) {
            concurrent |= READY_TO_RUN;
        }
    }

    public void drive(double y, double w, double x) {
        this.drive();

        if ((concurrent & PASS_IN_POWER_VARIABLES) == PASS_IN_POWER_VARIABLES) {
            updateInputs(y, w, x);
        } else {
            updateInputs();
        }
    }

    public void drive(double y, double w) {
        this.drive(y, w);
    }
}
