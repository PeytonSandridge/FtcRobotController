package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.Drive3D;

public class LinearXDrive extends Drive3D {

    @Override
    public void drive(double y, double w, double x) {
        this.frontLeftPower = y + w + x;
        this.backLeftPower = y + w - x;
        this.frontRightPower = w - y + x;
        this.backRightPower = w - y - x;
    }
}
