package org.firstinspires.ftc.teamcode.driveTools;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveLayout {
    // TODO: replace with setters and getters
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

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
}

