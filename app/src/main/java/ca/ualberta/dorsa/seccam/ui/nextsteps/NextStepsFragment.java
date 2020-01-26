package ca.ualberta.dorsa.seccam.ui.nextsteps;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.Contact;
import ca.ualberta.dorsa.seccam.ContactAdapter;

public class NextStepsFragment extends Fragment {

    private NextStepsViewModel nextStepsViewModel;
    private RecyclerView contactList;
    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nextStepsViewModel =
                ViewModelProviders.of(this).get(NextStepsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_next_steps, container, false);

        contactList = root.findViewById(R.id.contact_list);
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("911", "911")); // 911 is a contact by default

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid())
                .child("userSetting")
                .child("contacts");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dbContacts = dataSnapshot.getChildren();

                for (DataSnapshot ds : dbContacts) {
                    contacts.add(new Contact(
                            (String) ds.child("name").getValue(),
                            (String) ds.child("phoneNumber").getValue()
                    ));
                }

                adapter = new ContactAdapter(getActivity(), contacts);
                contactList.setAdapter(adapter);
                contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}