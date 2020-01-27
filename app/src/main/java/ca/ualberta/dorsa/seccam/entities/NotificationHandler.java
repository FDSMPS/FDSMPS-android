package ca.ualberta.dorsa.seccam.entities;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Notification;


public class NotificationHandler extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "cameraSecApp";
    public static String NOTIFICATION = "Please Take the picture as scheduled!";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(notificationId, notification);
    }
}
