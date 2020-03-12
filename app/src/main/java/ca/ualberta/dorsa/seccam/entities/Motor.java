package ca.ualberta.dorsa.seccam.entities;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Motor implements  MotorCoordinates{
    private int maxServoXPosition;
    private int maxServoYPosition;
    private int minServoXPosition;
    private int minServoYPosition;
    private String cameraCode;


    public Motor() {
        maxServoXPosition = 12;
        maxServoYPosition = 12;
        minServoXPosition = 2;
        minServoYPosition = 3;

    }

    public Motor(String cameraCode) {
        maxServoXPosition = 12;
        maxServoYPosition = 12;
        minServoXPosition = 2;
        minServoYPosition = 3;
        this.cameraCode = cameraCode;
    }

    public int getMaxServoXPosition() {
        return maxServoXPosition;
    }

    public void setMaxServoXPosition(int maxServoXPosition) {
        this.maxServoXPosition = maxServoXPosition;
    }

    public int getMaxServoYPosition() {
        return maxServoYPosition;
    }

    public void setMaxServoYPosition(int maxServoYPosition) {
        this.maxServoYPosition = maxServoYPosition;
    }

    public int getMinServoXPosition() {
        return minServoXPosition;
    }

    public void setMinServoXPosition(int minServoXPosition) {
        this.minServoXPosition = minServoXPosition;
    }

    public int getMinServoYPosition() {
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

                    int xPosition = (int) dataSnapshot.child("servoXPosition").getValue();
                    motorPosition.setxPosition(xPosition);
                    if (xPosition<minServoXPosition){
                        Log.d("MOTORPOsition", String.valueOf(xPosition));
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(minServoXPosition);
                    }
                    if (xPosition>maxServoXPosition){
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(maxServoXPosition);
                    }

                    int yPosition = (int) dataSnapshot.child("servoYPosition").getValue();
                    motorPosition.setyPosition(yPosition);
                    if (xPosition<minServoYPosition){
                        Log.d("MOTORPOsition", String.valueOf(yPosition));
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(minServoYPosition);
                    }
                    if (xPosition>maxServoYPosition){
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode)
                                .child("servoXPosition")
                                .setValue(maxServoYPosition);
                    }


                } catch (NullPointerException np) {
                    throw np;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return motorPosition;


    }
}
