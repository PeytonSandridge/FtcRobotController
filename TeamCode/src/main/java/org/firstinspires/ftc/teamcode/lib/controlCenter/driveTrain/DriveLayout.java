package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveLayout {
    // TODO: replace with setters and getters
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    public DriveLayout(DcMotor frontLeft, DcMotor backLeft, DcMotor frontRight, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
    }

    // TODO: figure out better way to deal with hardware maps
    public DriveLayout(String frontLeftName, String backLeftName, String frontRightName, String backRightName, HardwareMap hardwareMap) {
        init(frontLeftName, backLeftName, frontRightName, backRightName, hardwareMap);
    }

    // TODO: make more user friendly
    void init (String frontLeftName, String backLeftName, String frontRightName, String backRightName, HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, frontLeftName);
        backLeft = hardwareMap.get(DcMotor.class, backLeftName);
        frontRight = hardwareMap.get(DcMotor.class, frontRightName);
        backRight = hardwareMap.get(DcMotor.class, backRightName);
    }

    @Deprecated
    public void setFrontLeft(DcMotor frontLeft) {
        this.frontLeft = frontLeft;
    }
    public DcMotor getFrontLeft() {
        return frontLeft;
    }

    @Deprecated
    public void setFrontRight(DcMotor frontRight) {
        this.frontRight = frontRight;
    }
    public DcMotor getFrontRight() {
        return frontRight;
    }

    @Deprecated
    public void setBackLeft(DcMotor backLeft) {
        this.backLeft = backLeft;
    }
    public DcMotor getBackLeft() {
        return backLeft;
    }

    @Deprecated
    public void setBackRight(DcMotor backRight) {
        this.backRight = backRight;
    }
    public DcMotor getBackRight() {
        return backRight;
    }
}

