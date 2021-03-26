package org.firstinspires.ftc.teamcode.test.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative.HDrive;


@TeleOp
public class HDriveIter extends OpMode {
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






}
