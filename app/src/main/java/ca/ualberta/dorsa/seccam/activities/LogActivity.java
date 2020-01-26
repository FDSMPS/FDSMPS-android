package ca.ualberta.dorsa.seccam.activities;

import android.content.Intent;
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


public class LogActivity extends AppCompatActivity {
    String qrCodeScanned = "";


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
                }
        );
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
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* SETTINGS ----------------------------------------------------------------------------------*/

    public void openCamera(View view) {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

    }

    public void forgetMe(View view) {
//        AuthUI.getInstance()
//                .delete(this)
//                .addOnCompleteListener((OnCompleteListener<Void>) task -> {
//                    // ...
//                });

    }

    public void chooseLocation(View view) {
        Intent mapsIntent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(mapsIntent);

    }

    public void logOut(View view) {
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener((OnCompleteListener<Void>) task -> {
//                    // ...
//                });

    }
}