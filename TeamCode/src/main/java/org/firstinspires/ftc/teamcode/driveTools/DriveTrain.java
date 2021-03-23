package org.firstinspires.ftc.teamcode.driveTools;

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

    protected void setMotorPowers() {
        driveLayout.backLeft.setPower(backLeftPow);
        driveLayout.backRight.setPower(backRightPow);
        driveLayout.frontRight.setPower(frontRightPow);
        driveLayout.frontLeft.setPower(frontLeftPow);
    }
    public void setMotorPowers(double fLP, double bLP, double fRP, double bRP) {
        driveLayout.backLeft.setPower(bLP);
        driveLayout.backRight.setPower(bRP);
        driveLayout.frontRight.setPower(fRP);
        driveLayout.frontLeft.setPower(fLP);
    }
}
