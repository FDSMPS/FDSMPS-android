package ca.ualberta.dorsa.seccam.entities;

import android.util.Log;


/**
 * The type motor
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -3-15 Project: ECE 492 Group 1
 */
public class MotorPosition {
    /**
     * The X position.
     */
    double xPosition;
    /**
     * The Y position.
     */
    double yPosition;

    /**
     * Instantiates a new Motor position.
     */
    public MotorPosition() {
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public double getxPosition() {
        return xPosition;
    }

    /**
     * Sets position.
     *
     * @param xPosition the x position
     */
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public double getyPosition() {
        return yPosition;
    }

    /**
     * Sets position.
     *
     * @param yPosition the y position
     */
    public void setyPosition(double yPosition) {
        Log.d("setyposition",String.valueOf(yPosition));
        this.yPosition = yPosition;
    }

    /**
     * Instantiates a new Motor position.
     *
     * @param xPosition the x position
     * @param yPosition the y position
     */
    public MotorPosition(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


}
