package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.Drive2D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveTrain;

public class HDrive extends DriveTrain implements Drive2D {

    HDrive(DriveConfig driveConfig, DriveLayout driveLayout) {
        super(driveConfig, driveLayout);
    }

    public HDrive(DriveLayout driveLayout) {
        super(driveLayout);
    }

    @Override
    public void drive(double straightPower, double rotationPower) {
        // stores the two power Vectors into respective variables
        y = straightPower;
        w = rotationPower;

        applyMovementSpecificTransformations();

        // equations for calculation the power of each individual motor
        frontLeftPow = y + w;
        backLeftPow = y + w;
        frontRightPow = w - y;
        backRightPow = w - y;

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        setMotorPowers();
    }

    @Override
    protected void applyMovementSpecificTransformations() {
        applyMovementSpecificTransformations2D();
    }
}
