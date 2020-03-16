package ca.ualberta.dorsa.seccam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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


    public void moveUp(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        motor.loadAndChange(0,.5);

    }

    public void moveLeft(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        motor.loadAndChange(-.5,0);

    }

    public void moveRight(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        motor.loadAndChange(.5,0);

    }

    public void moveDown(View view) {
        Motor motor = new Motor(camera.getCameraCode());
        motor.loadAndChange(0,-.5);

    }
}
