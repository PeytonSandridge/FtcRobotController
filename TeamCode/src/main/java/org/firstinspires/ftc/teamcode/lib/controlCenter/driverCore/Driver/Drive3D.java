package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver;

public abstract class Drive3D extends Drive {

    @Override
    public void drive(double y, double w) {
        drive(y, w, 0);
    }

}
