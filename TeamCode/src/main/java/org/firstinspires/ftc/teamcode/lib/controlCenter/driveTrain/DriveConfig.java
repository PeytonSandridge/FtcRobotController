package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

public class DriveConfig {
    // variables for handling motor specific multipliers
    private boolean motorSpecificMultipliersEnabled;
    private double frontLeftMult;
    private double frontRightMult;
    private double backLeftMult;
    private double backRightMult;

    // variables for movement based multipliers
    private boolean movementSpecificMultipliersEnabled;
    private double rotationMult;
    private double straightMult;
    private double strafeMult;

    public DriveConfig() {
        motorSpecificMultipliersEnabled = false;
        frontLeftMult = 1;
        frontRightMult = 1;
        backLeftMult = 1;
        backRightMult = 1;

        movementSpecificMultipliersEnabled = false;
        rotationMult = 1;
        straightMult = 1;
        strafeMult = 1;
    }

    public void enableMotorsSpecificMultipliers(boolean enable) {
        this.motorSpecificMultipliersEnabled = enable;
    }
    public boolean areMotorSpecificMultipliersEnabled() {
        return motorSpecificMultipliersEnabled;
    }

    public void enableMovementSpecificMultipliers(boolean enable) {
        this.movementSpecificMultipliersEnabled = enable;
    }
    public boolean areMovementSpecificMultipliersEnabled() {
        return movementSpecificMultipliersEnabled;
    }

    public void setMotorSpecificMultipliers(double frontLeftMult, double frontRightMult, double backLeftMult, double backRightMult) {
        this.frontRightMult = frontRightMult;
        this.frontLeftMult = frontLeftMult;
        this.backRightMult = backRightMult;
        this.backLeftMult = backLeftMult;
    }

    public void setFrontLeftMult(double frontLeftMult) {
        this.frontLeftMult = frontLeftMult;
    }
    public double getFrontLeftMult() {
        return frontLeftMult;
    }

    public void setFrontRightMult(double frontRightMult) {
        this.frontRightMult = frontRightMult;
    }
    public double getFrontRightMult() {
        return frontRightMult;
    }

    public void setBackLeftMult(double backLeftMult) {
        this.backLeftMult = backLeftMult;
    }
    public double getBackLeftMult() {
        return backLeftMult;
    }

    public void setBackRightMult(double backRightMult) {
        this.backRightMult = backRightMult;
    }
    public double getBackRightMult() {
        return backRightMult;
    }




    public void setMovementSpecificMultipliers(double rotationMult, double straightMult) {
        this.rotationMult = rotationMult;
        this.straightMult = straightMult;
    }
    public void setMovementSpecificMultipliers(double rotationMult, double straightMult, double strafeMult) {
        this.rotationMult = rotationMult;
        this.straightMult = straightMult;
        this.strafeMult = strafeMult;
    }

    public void setRotationMult(double rotationMult) {
        this.rotationMult = rotationMult;
    }
    public double getStraightMult() {
        return straightMult;
    }

    public void setStraightMult(double straightMult) {
        this.straightMult = straightMult;
    }
    public double getRotationMult() {
        return rotationMult;
    }

    public void setStrafeMult(double strafeMult) {
        this.strafeMult = strafeMult;
    }
    public double getStrafeMult() {
        return strafeMult;
    }
}
