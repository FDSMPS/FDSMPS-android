package ca.ualberta.dorsa.seccam.entities;

import android.util.Log;

public class MotorPosition {
    double xPosition;
    double yPosition;

    public MotorPosition() {
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        Log.d("setyposition",String.valueOf(yPosition));
        this.yPosition = yPosition;
    }

    public MotorPosition(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


}
