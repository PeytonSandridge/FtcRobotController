package org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools;

import com.qualcomm.robotcore.hardware.Gamepad;

public class SmartController {
    private Gamepad gamepad;
    double leftStickYDeadZone;
    double leftStickXDeadZone;
    double rightStickYDeadZone;
    double rightStickXDeadZone;

    public SmartController(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public double getRawLeftStickX(){
        return gamepad.left_stick_x;
    }
    public double getLeftStickX() {
        return checkThreshhold(gamepad.left_stick_x, leftStickXDeadZone);
    }
    public void setLeftStickXDeadZone(double leftStickXDeadZone) {
        this.leftStickXDeadZone = leftStickXDeadZone;
    }
    public double getLeftStickXDeadZone() {
        return leftStickXDeadZone;
    }

    public double getRawLeftStickY(){
        return gamepad.left_stick_y;
    }
    public double getLeftStickY() {
        return checkThreshhold(gamepad.left_stick_y, leftStickYDeadZone);
    }
    public void setLeftStickYDeadZone(double leftStickYDeadZone) {
        this.leftStickYDeadZone = leftStickYDeadZone;
    }
    public double getLeftStickYDeadZone() {
        return leftStickYDeadZone;
    }

    public double getRawRightStickX(){
        return gamepad.right_stick_x;
    }
    public double getRightStickX(){
        return checkThreshhold(gamepad.right_stick_x, rightStickXDeadZone);
    }
    public void setRightStickXDeadZone(double rightStickXDeadZone) {
        this.rightStickXDeadZone = rightStickXDeadZone;
    }
    public double getRightStickXDeadZone() {
        return rightStickXDeadZone;
    }

    public double getRawRightStickY(){
        return gamepad.right_stick_y;
    }
    public double getRightStickY() {
        return checkThreshhold(gamepad.right_stick_y, rightStickYDeadZone);
    }
    public void setRightStickYDeadZone(double rightStickYDeadZone) {
        this.rightStickYDeadZone = rightStickYDeadZone;
    }
    public double getRightStickYDeadZone() {
        return rightStickYDeadZone;
    }

    private double checkThreshhold(double val, double threshhold) {
        if (val > threshhold || val < -threshhold){
            return val;
        }

        return 0;
    }
}
