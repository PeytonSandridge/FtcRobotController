package org.firstinspires.ftc.teamcode.test.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative.XDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

import java.io.InvalidObjectException;

public class XDriveIter extends OpMode {
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

        dt = new XDrive(new DriveTrainBuilder(dl,null,null,null,null));
        controller = new SmartController(gamepad1);
    }

    @Override
    public void loop() {
        double y = controller.getLeftStickY();
        double w = controller.getRightStickX();
        double x = controller.getLeftStickX();

        dt.drive(y, w, x);
    }




}
