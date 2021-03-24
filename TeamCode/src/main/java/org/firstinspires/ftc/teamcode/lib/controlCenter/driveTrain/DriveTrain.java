package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

public abstract class DriveTrain {
    protected DriveConfig driveConfig;

    protected double y = 0;
    protected double w = 0;
    protected double x = 0;

    protected double frontLeftPow = 0;
    protected double frontRightPow = 0;
    protected double backLeftPow = 0;
    protected double backRightPow = 0;


    protected DriveLayout driveLayout;



    public DriveTrain(DriveConfig driveConfig, DriveLayout driveLayout){
        this.driveConfig = driveConfig;
        this.driveLayout = driveLayout;
    }
    public DriveTrain(DriveLayout driveLayout) {
        this.driveConfig = new DriveConfig();
        this.driveLayout = driveLayout;
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
    protected void applyMovementSpecificTransformations2D() {
        if (driveConfig.areMovementSpecificMultipliersEnabled()) {
            y *= driveConfig.getStraightMult();
            w *= driveConfig.getRotationMult();
        }
    }
    protected void applyMovementSpecificTransformations3D() {
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
