package org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.driveTrains;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveConfig;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.LinearDriveTrain3D;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.LinearDriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriverKeybinds;
import org.firstinspires.ftc.teamcode.lib.mathTools.Vector;

import java.util.function.BooleanSupplier;

public class LinearMecanumDrive extends LinearDriveTrain3D {

    public LinearMecanumDrive(DriveLayout driveLayout, DriveConfig driveConfig, DriverKeybinds controls, Telemetry telemetry, BooleanSupplier running, String threadName) {
        super(driveLayout, driveConfig, controls, telemetry, running, threadName);
    }

    public LinearMecanumDrive(LinearDriveTrainBuilder builder) {
        super(builder);
    }

    @Override
    public void drive(double y, double w, double x) {
        // stores the two power Vectors into respective variables
        Vector lateral = new Vector(x, y);
        double turn = w;
        //applyMovementSpecificTransformations();


        // equations for calculation the power of each individual motor
        frontLeftPow = Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        backLeftPow = Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        frontRightPow = -Math.sin(lateral.direction - (1.0/4.0) * Math.PI) * lateral.magnitude + turn;
        backRightPow = -Math.sin(lateral.direction + (1.0/4.0) * Math.PI) * lateral.magnitude + turn;

        // method to apply motor specific multipliers

    }
}
