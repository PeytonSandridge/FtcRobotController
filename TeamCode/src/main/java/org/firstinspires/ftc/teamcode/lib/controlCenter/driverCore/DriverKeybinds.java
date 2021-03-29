package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore;

import java.util.function.DoubleSupplier;

public class DriverKeybinds {
    public DoubleSupplier yFun;
    public DoubleSupplier wFun;
    public DoubleSupplier xFun;
    public boolean is2D;

    public DriverKeybinds(DoubleSupplier yFun, DoubleSupplier wFun) {
        if (yFun != null) {
            this.yFun = yFun;
        } else {
            throw new IllegalArgumentException("yFun cannot be null");
        }

        if (wFun != null) {
            this.wFun = wFun;
        } else {
            throw new IllegalArgumentException("wFun cannot be null");
        }
        is2D = true;
    }

    public DriverKeybinds(DoubleSupplier yFun, DoubleSupplier wFun, DoubleSupplier xFun) {
        if (yFun != null) {
            this.yFun = yFun;
        } else {
            throw new IllegalArgumentException("yFun cannot be null");
        }

        if (wFun != null) {
            this.wFun = wFun;
        } else {
            throw new IllegalArgumentException("wFun cannot be null");
        }

        if (xFun != null) {
            this.xFun = xFun;
        } else {
            throw new IllegalArgumentException("xFun cannot be null");
        }
        is2D = false;
    }

    public boolean isControlable(){
        return (yFun == null || wFun == null) ? false : true;
    }
}
