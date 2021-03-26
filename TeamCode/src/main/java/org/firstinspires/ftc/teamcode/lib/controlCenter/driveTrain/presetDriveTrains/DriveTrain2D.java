package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.Drive2D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveTrain;

import java.util.function.DoubleSupplier;

public abstract class DriveTrain2D extends DriveTrain implements Drive2D {
    protected DoubleSupplier yFun;
    protected DoubleSupplier wFun;

    public DriveTrain2D(DriveConfig driveConfig, DriveLayout driveLayout) {
        super(driveConfig, driveLayout);
        this.yFun = null;
        this.wFun = null;
    }

    public DriveTrain2D(DriveLayout driveLayout) {
        super(driveLayout);
        this.yFun = null;
        this.wFun = null;
    }

    public DriveTrain2D(DriveConfig driveConfig, DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun) {
        super(driveConfig, driveLayout);
        this.yFun = yFun;
        this.wFun = wFun;
    }

    public DriveTrain2D(DriveLayout driveLayout, DoubleSupplier yFun, DoubleSupplier wFun) {
        super(driveLayout);
        this.yFun = yFun;
        this.wFun = wFun;
    }

    public abstract void drive();

    protected void handleInvalidInputFunctions() {
        if (yFun != null) {
            throw new IllegalArgumentException("The wFun cannot be null");
        } else if (wFun != null) {
            throw new IllegalArgumentException("The yFun cannot be null");
        } else {
            throw new IllegalArgumentException("The yFun and wFun cannot be null");
        }
    }
}
