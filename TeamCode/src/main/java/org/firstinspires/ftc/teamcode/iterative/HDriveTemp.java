package org.firstinspires.ftc.teamcode.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.driveTools.Drive2D;
import org.firstinspires.ftc.teamcode.driveTools.DriveConfig;
import org.firstinspires.ftc.teamcode.driveTools.DriveLayout;
import org.firstinspires.ftc.teamcode.driveTools.DriveTrain;
import org.firstinspires.ftc.teamcode.driveTools.SmartController;

@TeleOp
public class HDriveTemp extends OpMode {
    HDrive dt;
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

        dt = new HDrive(dl);
        controller = new SmartController(gamepad1);
    }

    @Override
    public void loop() {
        double y = controller.getLeftStickY();
        double w = controller.getRightStickX();
        
        dt.drive(y, w);
    }


    class HDrive extends DriveTrain implements Drive2D {

        HDrive(DriveConfig driveConfig, DriveLayout driveLayout) {
            super(driveConfig, driveLayout);
        }

        HDrive(DriveLayout driveLayout) {
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



}
