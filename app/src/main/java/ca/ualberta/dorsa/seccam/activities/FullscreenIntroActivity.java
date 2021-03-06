package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;

import java.io.IOException;
import java.security.GeneralSecurityException;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.MasterKeys;
import ca.ualberta.dorsa.seccam.R;

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
    public static KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
    public static String masterKeyAlias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_intro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean sign_in = sharedPref.getBoolean(getString(R.string.saved_high_score_key),false);


        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
