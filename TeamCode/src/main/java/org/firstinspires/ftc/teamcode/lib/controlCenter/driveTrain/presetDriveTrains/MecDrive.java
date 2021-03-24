package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.Drive3D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.lib.Vector;

public class MecDrive extends DriveTrain implements Drive3D {


    MecDrive(DriveConfig driveConfig, DriveLayout driveLayout) {
        super(driveConfig, driveLayout);
    }

    public MecDrive(DriveLayout driveLayout) {
        super(driveLayout);
    }

    @Override
    protected void applyMovementSpecificTransformations() {
        applyMovementSpecificTransformations3D();
    }

    public void drive(double straight, double rotational, double strafe) {
        // stores the two power Vectors into respective variables
        Vector lateral = new Vector(strafe, straight);
        double turn = rotational;
        //applyMovementSpecificTransformations();


        // equations for calculation the power of each individual motor
        frontLeftPow = Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        backLeftPow = Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        frontRightPow = -Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        backRightPow = -Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;

        // method to apply motor specific multipliers
        applyMotorSpecificTransformations();

        normalizePowers();

        setMotorPowers();
    }



}
