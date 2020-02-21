package ca.ualberta.dorsa.seccam.ui.alerts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.adapters.NotificationAdapter;
import ca.ualberta.dorsa.seccam.entities.Notification;
import ca.ualberta.dorsa.seccam.entities.UserNotifications;


public class AlertsFragment extends Fragment {

    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL = 20;
    private RecyclerView notificationList;
    private ArrayList<Notification> notifications;
    private NotificationAdapter adapter;
    private ArrayList<UserNotifications> userNotificationIds;
    private ArrayList<Notification> userNotification;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_alerts, container, false);

        notificationList = root.findViewById(R.id.notification_list);
        notifications = new ArrayList<>();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid())
                .child("UserNotifications");

        userNotificationIds = new ArrayList<UserNotifications>();
        userNotification = new ArrayList<Notification>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifications.clear();

                for (DataSnapshot notificationIdSnapshot : dataSnapshot.getChildren()) {

                    //From Users -> UID -> UserNotifications get the list of notification Ids

                    UserNotifications userNotifications = new UserNotifications(
                            notificationIdSnapshot.child("notificationId").getValue(String.class),
                            Boolean.valueOf(notificationIdSnapshot.child("read").getValue(String.class)));

                    userNotificationIds.add(userNotifications);

                    //From Notifications, create a notification object

                    DatabaseReference dbRefNotifications = FirebaseDatabase.getInstance().getReference("Notifications/" + userNotifications.getNotificationId());

                    dbRefNotifications.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("NOTID", dataSnapshot.getValue().toString());

                            Notification thisUserNotification = new Notification(
                                    dataSnapshot.child("cameraCode").getValue(String.class),
                                    dataSnapshot.child("datetime").getValue(String.class),
                                    dataSnapshot.child("imageId").getValue(String.class),
                                    dataSnapshot.child("notificationId").getValue(String.class));

                            userNotification.add(thisUserNotification);

                            Log.d("NOTIDS", dataSnapshot.child("imageId").getValue(String.class));

                            Collections.sort(userNotification, new CustomComparator());

                            adapter = new NotificationAdapter(getActivity(), userNotification);
                            notificationList.setAdapter(adapter);
                            notificationList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("PERMG", "NOT GRANTED");

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Log.d("PERMG", "GRANTED");
            // Permission has already been granted
        }

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //TODO: save the state of the permission
//                    File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "iSecurity");
//                    folder.mkdir();
                } else {
                    //TODO: save the state of the permission, ask upon launch if this is the case
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public class CustomComparator implements Comparator<Notification> {
        @Override
        public int compare(Notification o1, Notification o2) {
            return o1.getDatetime().compareTo(o2.getDatetime());
        }
    }


}