package ca.ualberta.dorsa.seccam.entities;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Motor implements  MotorCoordinates{
    private double maxServoXPosition = 12.0;
    private double maxServoYPosition = 12.0;
    private double minServoXPosition = 2.0;
    private double minServoYPosition = 3.0;
    private String cameraCode;


    public Motor() {

    }

    public Motor(String cameraCode) {
        maxServoXPosition = 12.0;
        maxServoYPosition = 12.0;
        minServoXPosition = 2.0;
        minServoYPosition = 3.0;
        this.cameraCode = cameraCode;
    }

    public double getMaxServoXPosition() {
        return maxServoXPosition;
    }

    public void setMaxServoXPosition(int maxServoXPosition) {
        this.maxServoXPosition = maxServoXPosition;
    }

    public double getMaxServoYPosition() {
        return maxServoYPosition;
    }

    public void setMaxServoYPosition(int maxServoYPosition) {
        this.maxServoYPosition = maxServoYPosition;
    }

    public double getMinServoXPosition() {
        return minServoXPosition;
    }

    public void setMinServoXPosition(int minServoXPosition) {
        this.minServoXPosition = minServoXPosition;
    }

    public double getMinServoYPosition() {
        return minServoYPosition;
    }

    public void setMinServoYPosition(int minServoYPosition) {
        this.minServoYPosition = minServoYPosition;
    }

    public void loadAndChange(double x, double y) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {

                    double xPosition = dataSnapshot.child("servoXPosition").getValue(Double.class);
                    double yPosition = dataSnapshot.child("servoYPosition").getValue(Double.class);
                    writeToDatabase((x+xPosition> maxServoXPosition)? maxServoXPosition: x+xPosition,(y+yPosition>maxServoYPosition)? maxServoYPosition:y+yPosition);

                } catch (NullPointerException np) {
                    throw np;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void writeToDatabase(double xPosition, double yPosition){

            FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                    .child("servoXPosition")
                    .setValue((xPosition>=minServoXPosition)? xPosition : minServoXPosition);
            FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                    .child("servoYPosition")
                    .setValue((yPosition>=minServoYPosition)? yPosition : minServoYPosition);

    }
}
