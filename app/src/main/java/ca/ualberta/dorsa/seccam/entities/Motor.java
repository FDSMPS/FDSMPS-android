package ca.ualberta.dorsa.seccam.entities;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Motor implements  MotorCoordinates{
    private double maxServoXPosition;
    private double maxServoYPosition;
    private double minServoXPosition;
    private double minServoYPosition;
    private String cameraCode;


    public Motor() {
        maxServoXPosition = 12.0;
        maxServoYPosition = 12.0;
        minServoXPosition = 2.0;
        minServoYPosition = 3.0;

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
    public MotorPosition loadPositiomFromFireBase() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode);

        MotorPosition motorPosition = new MotorPosition();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {

                    double xPosition = dataSnapshot.child("servoXPosition").getValue(Double.class);
//                    int xPosition = 3;
//                    Log.d("MOTORPOsition",String.valueOf(dataSnapshot.child("servoXPosition").getValue()));
                    motorPosition.setxPosition(xPosition);
                    if (xPosition<minServoXPosition){
                        Log.d("MOTORPOsition", String.valueOf(xPosition));
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(minServoXPosition);
                        motorPosition.setxPosition(minServoXPosition);

                    }
                    if (xPosition>maxServoXPosition){
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(maxServoXPosition);
                        motorPosition.setxPosition(maxServoXPosition);
                    }

                    double yPosition = dataSnapshot.child("servoYPosition").getValue(Double.class);
                    Log.d("MOTORPOsition-y", String.valueOf(yPosition));
                    Log.d("MOTORPOsition-y", String.valueOf(minServoYPosition));
                    motorPosition.setyPosition(yPosition);
                    if (yPosition<minServoYPosition){
//                        Log.d("MOTORPOsition", String.valueOf(yPosition));
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoYPosition")
                                .setValue(minServoYPosition);
                        Log.d("MOTORPOsition-y", String.valueOf(minServoYPosition));
                        motorPosition.setyPosition(minServoYPosition);
                    }
                    if (yPosition>maxServoYPosition){
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoYPosition")
                                .setValue(maxServoYPosition);
                        motorPosition.setyPosition(maxServoYPosition);
                    }


                } catch (NullPointerException np) {
                    throw np;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("MOTORPOsition-motor", String.valueOf(motorPosition.getxPosition()));
        Log.d("MOTORPOsition-motor", String.valueOf(motorPosition.getyPosition()));
        return motorPosition;


    }
}
