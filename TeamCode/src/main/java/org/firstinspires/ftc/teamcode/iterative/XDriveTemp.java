package org.firstinspires.ftc.teamcode.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.driveTools.Drive3D;
import org.firstinspires.ftc.teamcode.driveTools.DriveConfig;
import org.firstinspires.ftc.teamcode.driveTools.DriveLayout;
import org.firstinspires.ftc.teamcode.driveTools.DriveTrain;
import org.firstinspires.ftc.teamcode.driveTools.SmartController;

public class XDriveTemp extends OpMode {
    XDrive dt;
    DriveLayout dl;
    SmartController controller;

    @Override
    public void init() {
        dl = new DriveLayout(
                "frontLeftDrive",
                "backLeftDrive",
                "frontRightDrive",
                "backRightDrive",
                hardwareMap
        );

        dt = new XDrive(dl);
        controller = new SmartController(gamepad1);
    }

    @Override
    public void loop() {
        double y = controller.getLeftStickY();
        double w = controller.getRightStickX();
        double x = controller.getLeftStickX();

        dt.drive(y, w, x);
    }


    class XDrive extends DriveTrain implements Drive3D {


        XDrive(DriveConfig driveConfig, DriveLayout driveLayout) {
            super(driveConfig, driveLayout);
        }

        XDrive(DriveLayout driveLayout) {
            super(driveLayout);
        }

        @Override
        protected void applyMovementSpecificTransformations() {
            applyMovementSpecificTransformations3D();
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

}
