package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;


public abstract class Drive2D extends Drive {

    @Override
    public void drive(double y, double w, double x) {
        drive(y, w, 0);
    }

}
