package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.database.DatabaseHelper;

/**
 * A full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * This is to allow all the backend for logging in the user to occur
 * And it creates a nice animation
 * Executed UI tested yet to be unit tested
 *  @author Dorsa Nahid
 *  @date 2020-1-31
 *  Project: ECE 492 Group 1
 */
public class FullscreenIntroActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_intro);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getCurrentUser();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean sign_in = sharedPref.getBoolean(getString(R.string.saved_high_score_key),false);
        if (sign_in){

            new Handler().postDelayed(() -> {
                Intent logedInIntent = new Intent(getBaseContext(),   LogActivity.class);
                logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logedInIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }, 750);

        }
        else {
            new Handler().postDelayed(() -> {
                Intent logedInIntent = new Intent(getBaseContext(),   LoginActivity.class);
                logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logedInIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }, 750);

        }

    }

}
