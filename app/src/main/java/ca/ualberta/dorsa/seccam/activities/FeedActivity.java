package ca.ualberta.dorsa.seccam.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;

import ca.ualberta.dorsa.seccam.R;

/**
 * The type Feed activity.
 * This activity is for the live feed coming from the camera
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
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
    }
}
