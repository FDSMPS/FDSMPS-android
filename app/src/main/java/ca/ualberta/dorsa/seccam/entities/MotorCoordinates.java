package ca.ualberta.dorsa.seccam.entities;


public interface MotorCoordinates {
    

    int getMaxServoXPosition();
    void setMaxServoXPosition(int maxServoXPosition);

    int getMaxServoYPosition();
    void setMaxServoYPosition(int maxServoYPosition);

    int getMinServoXPosition();
    void setMinServoXPosition(int minServoXPosition);

    int getMinServoYPosition();
    void setMinServoYPosition(int minServoYPosition);

    MotorPosition loadPositiomFromFireBase();

}
