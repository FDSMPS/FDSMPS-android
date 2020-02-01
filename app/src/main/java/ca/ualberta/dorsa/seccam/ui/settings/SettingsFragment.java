package ca.ualberta.dorsa.seccam.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ca.ualberta.dorsa.seccam.R;

/**
 * The type Settings fragment.
 *
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020 -1-31
 * Project: ECE 492 Group 1
 */
public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private TextView qrCode;
    private TextView propertyAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        qrCode = root.findViewById(R.id.qr_code);
        propertyAddress = root.findViewById((R.id.owner_coordinates));
        loadDataFromFireBase();
        return root;
    }


    private void loadDataFromFireBase() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String address = (String) dataSnapshot.child("userSetting").child("location").child("address").getValue();

                    if (address != null) {
                        propertyAddress.setText(address);
                    }
                    String cameraCode = (String) dataSnapshot.child("cameraCode").getValue();
                    if (cameraCode != null) {
                        qrCode.setText(cameraCode);
                    }
                } catch (NullPointerException np) {
                    throw np;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}