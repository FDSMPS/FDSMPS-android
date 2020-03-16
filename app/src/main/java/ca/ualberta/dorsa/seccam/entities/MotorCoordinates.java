package ca.ualberta.dorsa.seccam.entities;


public interface MotorCoordinates {
    

    double getMaxServoXPosition();
    void setMaxServoXPosition(int maxServoXPosition);

    double getMaxServoYPosition();
    void setMaxServoYPosition(int maxServoYPosition);

    double getMinServoXPosition();
    void setMinServoXPosition(int minServoXPosition);

    double getMinServoYPosition();
    void setMinServoYPosition(int minServoYPosition);

    void loadAndChange(double x, double y);

    }
