package org.firstinspires.ftc.teamcode.lib.controlCenter.teleOpTools;

import com.qualcomm.robotcore.hardware.Gamepad;

public class SmartController {
    private Gamepad gamepad;

    double leftStickYDeadZone;
    double leftStickXDeadZone;
    double rightStickYDeadZone;
    double rightStickXDeadZone;

    double leftTriggerDeadZone;
    double rightTriggerDeadZone;

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

    public double getRawLeftTrigger() {
        return gamepad.left_trigger;
    }
    public double getLeftTrigger() {
        return checkThreshhold(gamepad.left_trigger, leftTriggerDeadZone);
    }
    public void setLeftTriggerDeadZone(double leftTriggerDeadZone) {
        this.leftTriggerDeadZone = leftTriggerDeadZone;
    }
    public double getLeftTriggerDeadZone() {
        return leftTriggerDeadZone;
    }

    public double getRawRightTrigger() {
        return gamepad.right_trigger;
    }
    public double getRightTrigger() {
        return checkThreshhold(gamepad.right_trigger, rightTriggerDeadZone);
    }
    public void setRightTriggerDeadZone(double rightTriggerDeadZone) {
        this.rightTriggerDeadZone = rightTriggerDeadZone;
    }
    public double getRightTriggerDeadZone() {
        return rightTriggerDeadZone;
    }

    public boolean getA() {
        return gamepad.a;
    }

    public boolean getB() {
        return gamepad.b;
    }

    public boolean getX() {
        return gamepad.x;
    }

    public boolean getY() {
        return gamepad.y;
    }

    public boolean getDpad_Up() {
        return gamepad.dpad_up;
    }

    public boolean getDpad_Down() {
        return gamepad.dpad_down;
    }

    public boolean getDpad_Left() {
        return gamepad.dpad_left;
    }

    public boolean getDpad_Right() {
        return gamepad.dpad_right;
    }

    public boolean getLeftBumper() {
        return gamepad.left_bumper;
    }

    public boolean getRightBumper() {
        return gamepad.right_bumper;
    }

    private double checkThreshhold(double val, double threshhold) {
        if (val > threshhold || val < -threshhold){
            return val;
        }

        return 0;
    }
}
