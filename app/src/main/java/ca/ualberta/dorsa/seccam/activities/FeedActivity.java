package ca.ualberta.dorsa.seccam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.Motor;
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
 *
 * Modified by Dorsa Nahid
 * @date 2020-03-15
 */
public class FeedActivity extends AppCompatActivity {
    public User cameraUser=null;
    public SecurityCamera camera= null;

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

    /**
     * Get camera and update feed image.
     *
     * @param cameraCode the camera code
     */
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

                if (camera != null) {
                    Log.v("FeedActivity", "Got User. CameraCode:" + camera.getCameraCode());
                    updateLiveFeedImage();
                }
                else {
                    Toast.makeText(FeedActivity.this, "Please go to Settings and set up a camera with your account.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


    /**
     * Update live feed image.
     */
    public void updateLiveFeedImage(){
        ImageView imageView = findViewById(R.id.imageView1);
        imageView.setImageBitmap(camera.getDecodedLiveFeedImage());
    }


    /**
     * Get current user.
     */
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


    /**
     * Move up.
     *
     * @param view the view
     */
    public void moveUp(View view) {
        if (camera != null) {
            Motor motor = new Motor(camera.getCameraCode());
            motor.loadAndChange(0,.5);
        }
    }

    /**
     * Move left.
     *
     * @param view the view
     */
    public void moveLeft(View view) {
        if (camera != null) {
            Motor motor = new Motor(camera.getCameraCode());
            motor.loadAndChange(-.5,0);
        }
    }

    /**
     * Move right.
     *
     * @param view the view
     */
    public void moveRight(View view) {
        if (camera != null) {
            Motor motor = new Motor(camera.getCameraCode());
            motor.loadAndChange(.5,0);
        }
    }

    /**
     * Move down.
     *
     * @param view the view
     */
    public void moveDown(View view) {
        if (camera != null) {
            Motor motor = new Motor(camera.getCameraCode());
            motor.loadAndChange(0,-.5);
        }
    }
}
