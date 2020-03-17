package ca.ualberta.dorsa.seccam.entities;


/**
 * The interface Motor coordinates.
 */
public interface MotorCoordinates {


    /**
     * Gets max servo x position.
     *
     * @return the max servo x position
     */
    double getMaxServoXPosition();

    /**
     * Sets max servo x position.
     *
     * @param maxServoXPosition the max servo x position
     */
    void setMaxServoXPosition(int maxServoXPosition);

    /**
     * Gets max servo y position.
     *
     * @return the max servo y position
     */
    double getMaxServoYPosition();

    /**
     * Sets max servo y position.
     *
     * @param maxServoYPosition the max servo y position
     */
    void setMaxServoYPosition(int maxServoYPosition);

    /**
     * Gets min servo x position.
     *
     * @return the min servo x position
     */
    double getMinServoXPosition();

    /**
     * Sets min servo x position.
     *
     * @param minServoXPosition the min servo x position
     */
    void setMinServoXPosition(int minServoXPosition);

    /**
     * Gets min servo y position.
     *
     * @return the min servo y position
     */
    double getMinServoYPosition();

    /**
     * Sets min servo y position.
     *
     * @param minServoYPosition the min servo y position
     */
    void setMinServoYPosition(int minServoYPosition);

    /**
     * Load and change.
     *
     * @param x the x
     * @param y the y
     */
    void loadAndChange(double x, double y);

    }
