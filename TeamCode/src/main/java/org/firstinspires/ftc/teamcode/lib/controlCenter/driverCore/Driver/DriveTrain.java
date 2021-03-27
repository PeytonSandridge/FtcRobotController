package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;

public abstract class DriveTrain {
    protected DriveLayout driveLayout;
    protected DriveConfig driveConfig;

    protected DriverKeybinds controls;

    protected Telemetry telemetry;

    protected double y = 0;
    protected double w = 0;
    protected double x = 0;

    protected double frontLeftPow = 0;
    protected double frontRightPow = 0;
    protected double backLeftPow = 0;
    protected double backRightPow = 0;



    protected DriveTrain(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry) {
        this.driveLayout = driveLayout;
        this.driveConfig = driveConfig;
        this.controls = controls;
        this.telemetry = telemetry;
    }

    protected DriveTrain(DriveTrainBuilder dTBuilder) {
        this.driveLayout = dTBuilder.getDriveLayout();
        this.driveConfig = dTBuilder.getDriveConfig();
        this.controls = dTBuilder.getControls();
        this.telemetry = dTBuilder.getTelemetry();
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
