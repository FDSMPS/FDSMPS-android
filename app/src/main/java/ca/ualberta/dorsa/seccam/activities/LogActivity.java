package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ca.ualberta.dorsa.seccam.R;


/**
 * The type Log activity. This is to allow the user to see the previous
 * logs of different notifications
 * Executed UI tested yet to be unit tested
 *  @author Jessica D'Cunha
 *  @date 2020-1-31
 *  Project: ECE 492 Group 1
 */
public class LogActivity extends AppCompatActivity {
    /**
     * The Qr code scanned.
     */
    String qrCodeScanned = "";
    private SharedPreferences sharedPref;
    /**
     * The Editor.
     */
    SharedPreferences.Editor editor;
    /**
     * The Firebase auth.
     */
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_alerts, R.id.navigation_feed, R.id.navigation_settings, R.id.navigation_next_steps)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        BottomNavigationItemView feedButton = findViewById(R.id.navigation_feed);
        feedButton.setOnClickListener(view -> {
            Intent i = new Intent(LogActivity.this, FeedActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
        );
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                qrCodeScanned = result.getContents();
                Log.i("MYTAG", qrCodeScanned);

                //update database with the qr code scan

                FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("cameraCode")
                        .setValue(qrCodeScanned);

                FirebaseDatabase.getInstance().getReference("SecurityCameras/" + qrCodeScanned).child("registered").setValue("true");
//                FirebaseDatabase.getInstance().getReference("SecurityCameras/" + qrCodeScanned).child("registeredUserName").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* SETTINGS ----------------------------------------------------------------------------------*/

    /**
     * Open camera.
     *
     * @param view the view
     */
    public void openCamera(View view) {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

    }

    /**
     * Forget me.
     *
     * @param view the view
     */
    public void forgetMe(View view) {
        editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.saved_high_score_key), false);
        editor.apply();

        //TODO handle forget me logic


        //End of forget me logic

        Intent logedInIntent = new Intent(getBaseContext(),   LoginActivity.class);
        logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logedInIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();

    }

    /**
     * Choose location on the map.
     *
     * @param view the view
     */
    public void chooseLocation(View view) {
        Intent mapsIntent = new Intent(getBaseContext(), MapsActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startActivity(mapsIntent);

    }

    /**
     * Log out.
     *
     * @param view the view
     */
    public void logOut(View view) {
        editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.saved_high_score_key), false);
        editor.apply();
        firebaseAuth.signOut();
        Intent logedInIntent = new Intent(getBaseContext(),   LoginActivity.class);
        logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logedInIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();

    }
}
