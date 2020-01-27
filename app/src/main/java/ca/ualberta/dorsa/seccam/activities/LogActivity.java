package ca.ualberta.dorsa.seccam.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.NotificationHandler;



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

    /* Notifications ----------------------------------------------------------------------------------*/


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = (getString(R.string.channel_name));
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NotificationHandler.NOTIFICATION_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void scheduleNotification(Context context, long delay, int notificationId) {
        //delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHandler.NOTIFICATION_ID)
                .setContentTitle("Mark Me")
                .setContentText("Please Take a Picture!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.logo)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, LogActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, NotificationHandler.class);
        notificationIntent.putExtra(NotificationHandler.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(NotificationHandler.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    public void sendNotification(View view) {
        createNotificationChannel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        scheduleNotification(this, 1000, 12);
        dialog.show();
    }
}
