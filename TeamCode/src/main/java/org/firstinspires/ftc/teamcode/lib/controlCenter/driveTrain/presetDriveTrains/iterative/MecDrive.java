package org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.mathTools.Vector;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.Drive3D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.DriveTrain;

public class MecDrive extends DriveTrain implements Drive3D {


    public MecDrive(DriveTrainBuilder driveTrainBuilder) {
        super(driveTrainBuilder);
    }

    @Override
    protected void applyMovementSpecificTransformations() {
        applyGenericMovementSpecificTransformations3D();
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
