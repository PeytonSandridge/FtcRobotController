package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.Drive2D;

public class LinearHDrive extends Drive2D {
    @Override
    public void drive(double y, double w) {
        // equations for calculation the power of each individual motor
        this.frontLeftPower = y + w;
        this.backLeftPower = y + w;
        this.frontRightPower = w - y;
        this.backRightPower = w - y;
    }
}
