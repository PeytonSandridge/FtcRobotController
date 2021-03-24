package org.firstinspires.ftc.teamcode.lib.mathTools;

public class Vector {
    public double magnitude;
    public double direction;

    public Vector(double x, double y) {
        updateVector(x, y);
    }

    public void updateVector(double x, double y) {
        updateDirection(x, y);
        updateMagnitude(x, y);
    }

    public void updateDirection(double x, double y) {
        direction = Math.atan2(y, x);
    }

    public void updateMagnitude(double x, double y) {
        magnitude = Math.sqrt((x * x) + (y * y));
    }

}
