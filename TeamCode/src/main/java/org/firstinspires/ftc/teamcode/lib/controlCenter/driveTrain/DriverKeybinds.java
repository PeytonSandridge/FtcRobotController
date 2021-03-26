package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain;

import java.util.function.DoubleSupplier;

public class DriverKeybinds {
    public DoubleSupplier yFun;
    public DoubleSupplier wFun;

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
    }

    public boolean isControlable(){
        return (yFun == null || wFun == null) ? false : true;
    }
}
