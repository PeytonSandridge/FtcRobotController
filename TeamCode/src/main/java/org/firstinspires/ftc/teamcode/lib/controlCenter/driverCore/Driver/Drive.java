package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

public abstract class Drive {
    public double frontLeftPower;
    public double backLeftPower;
    public double frontRightPower;
    public double backRightPower;

    public abstract void drive(double y, double w, double x);
    public abstract void drive(double y, double w);
}
