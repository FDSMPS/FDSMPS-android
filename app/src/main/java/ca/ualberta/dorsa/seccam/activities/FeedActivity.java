package ca.ualberta.dorsa.seccam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.Motor;
import ca.ualberta.dorsa.seccam.entities.MotorPosition;
import ca.ualberta.dorsa.seccam.entities.SecurityCamera;
import ca.ualberta.dorsa.seccam.entities.User;

/**
 * The type Feed activity.
 * This activity is for the live feed coming from the camera
 * Executed UI tested yet to be unit tested
 *
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class FeedActivity extends AppCompatActivity {
    public User cameraUser=null;
    public SecurityCamera camera= null;
    protected int maxServoXPosition = 12;
    protected int maxServoYPosition = 12;
    protected int minServoXPosition = 2;
    protected int minServoYPosition = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feed");

        Log.v("FeedActivity", "At start.");

        //get camera code by making the user
        getCurrentUser();

    }

    /*
     Grabs the camera from the database based on the current user. Uses the valueEventLinstener
     to
     */
    public void getCameraAndUpdateFeedImage(String cameraCode){


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cameraRef = database.getReference("SecurityCameras/" + cameraCode);

        cameraRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                camera = dataSnapshot.getValue(SecurityCamera.class);
                Log.v("FeedActivity", "Got User. CameraCode:" + camera.getCameraCode());

                updateLiveFeedImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


    public void updateLiveFeedImage(){
        ImageView imageView = findViewById(R.id.imageView1);
        imageView.setImageBitmap(camera.getDecodedLiveFeedImage());
    }




    public void getCurrentUser(){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        DatabaseReference rDatabase = FirebaseDatabase.getInstance().getReference("Users/" + firebaseUser.getUid());
        Log.v("FeedActivity", "Grabbed user ref.");

        rDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        cameraUser = dataSnapshot.getValue(User.class);

                        //getCamera and autoupdate the feed image
                        getCameraAndUpdateFeedImage(cameraUser.getCameraCode());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}

                });
    }

    private void writeToDatabase(double xPosition, double yPosition){

        if (yPosition == -1) {
            FirebaseDatabase.getInstance().getReference("SecurityCameras/" + camera.getCameraCode())
                    .child("servoXPosition")
                    .setValue(xPosition).addOnCompleteListener(task -> {


                    });
        }
        if(xPosition == -1) {
            FirebaseDatabase.getInstance().getReference("SecurityCameras/" + camera.getCameraCode())
                    .child("servoYPosition")
                    .setValue(yPosition);
        }

    }

    public void moveUp(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        MotorPosition motorPosition = motor.loadPositiomFromFireBase();
        double yPosition = motorPosition.getyPosition();
        if (yPosition<maxServoYPosition){
            writeToDatabase(-1, yPosition+1);
        }

    }

    public void moveLeft(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        MotorPosition motorPosition = motor.loadPositiomFromFireBase();
        double xPosition = motorPosition.getxPosition();
        Log.d("MOTORPOsition",String.valueOf(xPosition));
        if (xPosition>minServoXPosition){
            writeToDatabase(xPosition-1, -1);
        }

    }

    public void moveRight(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        MotorPosition motorPosition = motor.loadPositiomFromFireBase();
        double xPosition = motorPosition.getxPosition();
        Log.d("MOTORPOsition",String.valueOf(xPosition));
        if (xPosition<maxServoXPosition){
            writeToDatabase(xPosition+1, -1);
        }

    }

    public void moveDown(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        MotorPosition motorPosition = motor.loadPositiomFromFireBase();
        double yPosition = motorPosition.getyPosition();

        if (yPosition>minServoYPosition){
            writeToDatabase(-1, yPosition-1);
        }

    }
}
