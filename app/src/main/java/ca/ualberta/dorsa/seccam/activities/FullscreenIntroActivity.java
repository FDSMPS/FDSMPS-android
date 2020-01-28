package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenIntroActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_intro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean sign_in = sharedPref.getBoolean(getString(R.string.saved_high_score_key),false);
        if (sign_in){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent logedInIntent = new Intent(getBaseContext(),   LogActivity.class);
                    logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logedInIntent);
                    finish();

                }
            }, 750);

        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent logedInIntent = new Intent(getBaseContext(),   LoginActivity.class);
                    logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logedInIntent);
                    finish();

                }
            }, 750);

        }

    }

}
