package org.firstinspires.ftc.teamcode.test.iterative;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.DriveLayout;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driveTrain.presetDriveTrains.iterative.MecDrive;
import org.firstinspires.ftc.teamcode.lib.controlCenter.driverCore.Driver.builders.DriveTrainBuilder;
import org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools.SmartController;

import java.io.InvalidObjectException;

@TeleOp
public class MechDriveIter extends OpMode {
    MecDrive dt;
    DriveLayout dl;
    SmartController controller;


    @Override
    public void init() {



        dl = new DriveLayout(
                "FrontLeftDrive",
                "BackLeftDrive",
                "FrontRightDrive",
                "BackRightDrive",
                hardwareMap
        );

        dl.getFrontRight().setDirection(DcMotorSimple.Direction.REVERSE);
        dl.getBackLeft().setDirection(DcMotorSimple.Direction.REVERSE);

        DriveTrainBuilder dtb = null;
//            dtb = new DriveTrainBuilder(dl,null,null,null,null);
        dt = new MecDrive(dtb);
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
