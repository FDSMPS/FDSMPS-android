package ca.ualberta.dorsa.seccam.ui.alerts;

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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.adapters.NotificationAdapter;
import ca.ualberta.dorsa.seccam.entities.Notification;
import ca.ualberta.dorsa.seccam.entities.UserNotifications;


public class AlertsFragment extends Fragment {

    private RecyclerView notificationList;
    private ArrayList<Notification> notifications;
    private NotificationAdapter adapter;

    private List<UserNotifications> userNotificationIds;
    private List<Notification> userNotification;


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
                    Log.d("NOTID",notificationIdSnapshot.getValue().toString());

                    UserNotifications userNotifications = (UserNotifications) (notificationIdSnapshot.getValue());
                    userNotificationIds.add(userNotifications);

                    //From Notifications, create a notification object

                    DatabaseReference dbRefNotifications = FirebaseDatabase.getInstance().getReference("Notifications/");

                    dbRefNotifications.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot notificationSnapshot : dataSnapshot.getChildren()) {
                                Notification thisUserNotification = (Notification) notificationSnapshot.getValue();
                                userNotification.add(thisUserNotification);
                            }

                            adapter = new NotificationAdapter (getActivity(), notifications);
                            notificationList.setAdapter(adapter);
                            notificationList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                adapter = new NotificationAdapter (getActivity(), notifications);
                notificationList.setAdapter(adapter);
                notificationList.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }

}