package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.Drive3D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveTrain;

public class XDrive extends DriveTrain implements Drive3D {


    XDrive(DriveConfig driveConfig, DriveLayout driveLayout) {
        super(driveConfig, driveLayout);
    }

    public XDrive(DriveLayout driveLayout) {
        super(driveLayout);
    }

    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations3D();
    }

    public void drive(double straight, double rotational, double strafe) {
        // stores the two power Vectors into respective variables
        y = straight;
        w = rotational;
        x = strafe;


        applyMovementSpecificTransformations();

        // equations for calculation the power of each individual motor
        frontLeftPow = y + w + x;
        backLeftPow = y + w - x;
        frontRightPow = w - y + x;
        backRightPow = w - y - x;

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }

}
