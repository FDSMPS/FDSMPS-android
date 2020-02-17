package ca.ualberta.dorsa.seccam.ui.alerts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.adapters.ContactAdapter;
import ca.ualberta.dorsa.seccam.adapters.NotificationAdapter;
import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.entities.Notification;
import ca.ualberta.dorsa.seccam.ui.nextsteps.NextStepsFragment;
import ca.ualberta.dorsa.seccam.ui.nextsteps.NextStepsViewModel;


public class AlertsFragment extends Fragment {

    private RecyclerView notificationList;
    private ArrayList<Notification> notifications;
    private NotificationAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_alerts, container, false);

        notificationList = root.findViewById(R.id.notification_list);
        notifications = new ArrayList<>();

//       TODO correct the db reference
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid())
                .child("userSetting")
                .child("notifications");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifications.clear();

                for (DataSnapshot notificationSnapshot : dataSnapshot.getChildren()) {
                    notifications.add(new Notification(null,null));
//                    TODO get a list of notifications instead
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