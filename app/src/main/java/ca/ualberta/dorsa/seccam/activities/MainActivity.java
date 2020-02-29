package ca.ualberta.dorsa.seccam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;

/**
 * The type Main activity.
 * Executed UI tested yet to be unit tested
 *
 *  @author Dorsa Nahid
 *  @date 2020-1-31
 *  Project: ECE 492 Group 1
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Qr code scanned.
     */
    String qrCodeScanned = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Open camera, change the activity
     *
     * @param view the view
     */
    public void openCamera(View view) {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

    }

    /**
     * Forget me, delete the user completely from the database
     *
     * @param view the view
     */
    public void forgetMe(View view) {

    }
    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                qrCodeScanned = result.getContents();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
