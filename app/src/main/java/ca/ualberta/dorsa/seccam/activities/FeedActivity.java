package ca.ualberta.dorsa.seccam.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.singletons.SecurityCameraInUse;

/**
 * The type Feed activity.
 * This activity is for the live feed coming from the camera
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha, worked on by Bryn Leonard-Fortune
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feed");

        String liveFeedImageString = SecurityCameraInUse.getInstance().getLiveFeedImage();

        ImageView imageView = findViewById(R.id.imageView1);
        SecurityCameraInUse.getInstance().decodeToImage(liveFeedImageString, imageView);

    }
}
