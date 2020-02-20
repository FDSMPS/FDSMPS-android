package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.SecurityCamera;


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
    Boolean isPreviouslyRegisteredCameraCode;
    private String oldCameraCode = "empty";



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
        isPreviouslyRegisteredCameraCode = sharedPref.getBoolean(getString(R.string.registered_camera),false);

        if (isPreviouslyRegisteredCameraCode){
            notificationSubscription();
        }

    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                if (isPreviouslyRegisteredCameraCode){
                    notificationRemoveSubscription(oldCameraCode);

                }

                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                qrCodeScanned = result.getContents();
                Log.i("MYTAG", qrCodeScanned);

                //update database with the qr code scan

                FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("cameraCode")
                        .setValue(qrCodeScanned);

                SecurityCamera securityCamera = new SecurityCamera(qrCodeScanned,false,null, true, FirebaseAuth.getInstance().getCurrentUser().getUid());
                oldCameraCode = qrCodeScanned;
                FirebaseDatabase.getInstance().getReference("SecurityCameras")
                        .child(qrCodeScanned)
                        .setValue(securityCamera);

                isPreviouslyRegisteredCameraCode = true;
                notificationSubscription();

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

    private void notificationSubscription() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String cameraCode = (String) dataSnapshot.child("cameraCode").getValue();

                    FirebaseMessaging.getInstance().subscribeToTopic(cameraCode)
                            .addOnCompleteListener(task -> {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d("TAG", msg);
//                                Toast.makeText(LogActivity.this, msg, Toast.LENGTH_SHORT).show();
                            });

                } catch (NullPointerException np) {
                    throw np;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void notificationRemoveSubscription(String oldCameraCode) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(oldCameraCode)
                .addOnCompleteListener(task -> {
                    String msg = getString(R.string.msg_unsubscribed);
                    if (!task.isSuccessful()) {
                        msg = getString(R.string.msg_unsubscribe_failed);
                    }
                    else{
                        FirebaseDatabase.getInstance().getReference("SecurityCameras/" + oldCameraCode)
                                .removeValue();
                    }
                    Log.d("TAG", msg);
                    Toast.makeText(LogActivity.this, msg, Toast.LENGTH_SHORT).show();
                });


    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            //Page not found
        }
    }


    public void connectCameraToWifi(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.connect_to_wifi)
                .setTitle(R.string.camera_config);

        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            openWebPage("http://192.168.0.1:3000");
            // User clicked OK button
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
