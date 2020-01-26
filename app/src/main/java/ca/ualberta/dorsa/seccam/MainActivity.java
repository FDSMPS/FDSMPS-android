package ca.ualberta.dorsa.seccam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;

public class MainActivity extends AppCompatActivity {
    String qrCodeScanned = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openCamera(View view) {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

    }
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
