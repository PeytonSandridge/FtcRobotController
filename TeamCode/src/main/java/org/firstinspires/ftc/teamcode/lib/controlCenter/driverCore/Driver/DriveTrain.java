package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

import java.io.InvalidObjectException;
import java.util.function.DoubleSupplier;

public class DriveTrain<T extends Drive> {

    protected T driver;

    protected DriveLayout driveLayout;
    protected DriveConfig driveConfig;

    protected DriverKeybinds controls;

    protected Telemetry telemetry;

    protected Double y;
    protected Double w;
    protected Double x;

    protected double frontLeftPow = 0;
    protected double frontRightPow = 0;
    protected double backLeftPow = 0;
    protected double backRightPow = 0;



    protected DriveTrain(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry) {
        this.driveLayout = driveLayout;
        this.driveConfig = driveConfig;
        this.controls = controls;
        this.telemetry = telemetry;
        this.y = new Double(0);
        this.w = new Double(0);
        this.x = new Double(0);
    }

    protected DriveTrain(DriveTrainBuilder dTBuilder) {
        this(dTBuilder.getDriveLayout(),
                dTBuilder.getDriveConfig(),
                dTBuilder.getControls(),
                dTBuilder.getTelemetry());
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


    // TODO: FIX THIS
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations3D();
    }
    protected void applyGenericMovementSpecificTransformations2D() {
        if (driveConfig.areMovementSpecificMultipliersEnabled()) {
            y *= driveConfig.getStraightMult();
            w *= driveConfig.getRotationMult();
        }
    }
    protected void applyGenericMovementSpecificTransformations3D() {
        if (driveConfig.areMovementSpecificMultipliersEnabled()) {
            y *= driveConfig.getStraightMult();
            w *= driveConfig.getRotationMult();
            x *= driveConfig.getStrafeMult();
        }
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

    protected void applyDriveFunction() {
        driver.drive(y, w, x);
        this.frontLeftPow = driver.frontLeftPower;
        this.frontRightPow = driver.frontLeftPower;
        this.backLeftPow = driver.backLeftPower;
        this.backRightPow = driver.backRightPower;
    }
}
