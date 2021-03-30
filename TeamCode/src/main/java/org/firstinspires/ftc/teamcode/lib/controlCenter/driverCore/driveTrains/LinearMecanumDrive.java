package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.Drive3D;
import org.firstinspires.ftc.teamcode.lib.mathTools.Vector;

public class LinearMecanumDrive extends Drive3D {

    @Override
    public void drive(double y, double w, double x) {
        // stores the two power Vectors into respective variables
        Vector lateral = new Vector(x, y);
        double turn = w;
        //applyMovementSpecificTransformations();


        // equations for calculation the power of each individual motor
        this.frontLeftPower = Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        this.backLeftPower = Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        this.frontRightPower = -Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        this.backRightPower = -Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;

        // method to apply motor specific multipliers

    }
}
