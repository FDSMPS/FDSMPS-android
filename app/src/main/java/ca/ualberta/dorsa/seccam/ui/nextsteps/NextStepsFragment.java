package ca.ualberta.dorsa.seccam.ui.nextsteps;

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
import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.adapters.ContactAdapter;

public class NextStepsFragment extends Fragment {

    private NextStepsViewModel nextStepsViewModel;
    private RecyclerView contactList;
    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;
    private FloatingActionButton addButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nextStepsViewModel =
                ViewModelProviders.of(this).get(NextStepsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_next_steps, container, false);

        contactList = root.findViewById(R.id.contact_list);
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("911", "911")); // 911 is a contact by default

        addButton = (FloatingActionButton) root.findViewById(R.id.add_button);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid())
                .child("userSetting")
                .child("contacts");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot contactSnapshot : dataSnapshot.getChildren()) {
                    contacts.add(new Contact(
                            contactSnapshot.child("name").getValue(String.class),
                            contactSnapshot.child("phone").getValue(String.class)
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder addDialogBuilder = new AlertDialog.Builder(getActivity());
                addDialogBuilder.setTitle("Add Emergency Contact");

                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText nameInput = new EditText(getActivity());
                nameInput.setHint("Name");

                final EditText phoneInput = new EditText(getActivity());
                phoneInput.setHint("Phone number");

                layout.addView(nameInput);
                layout.addView(phoneInput);
                addDialogBuilder.setView(layout);

                addDialogBuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addContact(nameInput.getText().toString(), phoneInput.getText().toString());
                    }
                });

                addDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog addDialog = addDialogBuilder.create();
                addDialog.show();
            }
        });

        return root;
    }

    private void addContact(String name, String phone) {
        Contact c = new Contact(name, phone);

        FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("userSetting")
                .child("contacts")
                .child(name)
                .setValue(c);
    }

    private void editContact() {

    }

    private void deleteContact() {

    }
}