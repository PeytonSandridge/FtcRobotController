package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public abstract class DriveTrain {
    protected DriveConfig driveConfig;
    protected DriveLayout driveLayout;

    protected DriverKeybinds controls;

    protected Telemetry telemetry;

    protected double y = 0;
    protected double w = 0;
    protected double x = 0;

    protected double frontLeftPow = 0;
    protected double frontRightPow = 0;
    protected double backLeftPow = 0;
    protected double backRightPow = 0;


    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = null;
    }
    public DriveTrain(DriveLayout driveLayout) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = null;
    }

    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = controls;
    }
    public DriveTrain(DriveLayout driveLayout, DriverKeybinds controls) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = controls;
    }

    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = new DriverKeybinds(yFun, wFun);
    }
    public DriveTrain(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = new DriverKeybinds(yFun, wFun);
    }





    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout, Telemetry telemetry){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = null;
        this.telemetry = telemetry;
    }
    public DriveTrain(DriveLayout driveLayout, Telemetry telemetry) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = null;
        this.telemetry = telemetry;
    }
    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = controls;
        this.telemetry = telemetry;
    }
    public DriveTrain(DriveLayout driveLayout, DriverKeybinds controls, Telemetry telemetry) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = controls;
        this.telemetry = telemetry;
    }
    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
        this.controls = new DriverKeybinds(yFun, wFun);
        this.telemetry = telemetry;
    }
    public DriveTrain(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun, Telemetry telemetry) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
        this.controls = new DriverKeybinds(yFun, wFun);
        this.telemetry = telemetry;
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


    protected abstract void applyMovementSpecificTransformations();
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


    protected void setMotorPowers() {
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


}
