package ca.ualberta.dorsa.seccam.ui.nextsteps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.RecyclerViewListener;
import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.adapters.ContactAdapter;

/**
 * The type Next steps fragment.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class NextStepsFragment extends Fragment implements RecyclerViewListener {

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
        contacts = new ArrayList<>();

        registerForContextMenu(contactList); // on long-click menu for editing and deleting

        addButton = root.findViewById(R.id.add_button);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid())
                .child("userSetting")
                .child("contacts");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contacts.clear();
                contacts.add(new Contact("911", "911")); // 911 is a contact by default

                for (DataSnapshot contactSnapshot : dataSnapshot.getChildren()) {
                    contacts.add(new Contact(
                            contactSnapshot.child("name").getValue(String.class),
                            contactSnapshot.child("phone").getValue(String.class)
                    ));
                }

                adapter = new ContactAdapter(NextStepsFragment.this, getActivity(), contacts);
                contactList.setAdapter(adapter);
                contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addButton.setOnClickListener(view -> {
            AlertDialog.Builder addDialogBuilder = new AlertDialog.Builder(getActivity());
            addDialogBuilder.setTitle("Add Emergency Contact");
            View dialogView = inflater.inflate(R.layout.next_steps_dialog, null);
            addDialogBuilder.setView(dialogView);

            EditText nameInput = (EditText) dialogView.findViewById(R.id.name_input);
            EditText phoneInput = (EditText) dialogView.findViewById(R.id.phone_input);

            addDialogBuilder.setPositiveButton("SAVE", null);

            addDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog addDialog = addDialogBuilder.create();

            addDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(final DialogInterface dialog) {
                    Button positiveButton = addDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!nameInput.getText().toString().isEmpty() && !phoneInput.getText().toString().isEmpty()) {
                                addContact(nameInput.getText().toString(), phoneInput.getText().toString());
                                dialog.dismiss();
                            }
                            else {
                                Toast.makeText(getActivity(),"Please enter all fields.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            addDialog.show();
        });

        return root;
    }

    @Override
    public void recyclerViewOnLongClick(View view, int position) {
        // creating a popup menu
        PopupMenu popup = new PopupMenu(getActivity(), view);
        // inflating menu from xml resource
        popup.inflate(R.menu.nextsteps_context_menu);
        // adding click listener
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.edit:
                    AlertDialog.Builder editDialogBuilder = new AlertDialog.Builder(getActivity());
                    editDialogBuilder.setTitle("Edit Emergency Contact");
                    LayoutInflater inflater = this.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.next_steps_dialog, null);
                    editDialogBuilder.setView(dialogView);

                    EditText nameInput = (EditText) dialogView.findViewById(R.id.name_input);
                    EditText phoneInput = (EditText) dialogView.findViewById(R.id.phone_input);

                    String prevName = contacts.get(position).getName();
                    nameInput.setText(prevName);
                    phoneInput.setText(contacts.get(position).getPhone());

                    editDialogBuilder.setPositiveButton("SAVE", null);

                    editDialogBuilder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.cancel());

                    AlertDialog editDialog = editDialogBuilder.create();

                    editDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(final DialogInterface dialog) {
                            Button positiveButton = editDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            positiveButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!nameInput.getText().toString().isEmpty() && !phoneInput.getText().toString().isEmpty()) {
                                        deleteContact(prevName);
                                        addContact(nameInput.getText().toString(), phoneInput.getText().toString());
                                        dialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getActivity(),"Please enter all fields.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                    editDialog.show();
                    break;

                case R.id.delete:
                    deleteContact(contacts.get(position).getName());
                    contacts.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;

                default:
                    break;
            }
            return false;
        });

        // displaying the popup
        popup.show();
    }
    /**
     * Add contact.
     *
     * @param name the name
     * @param phone the phone
     */
    private void addContact(String name, String phone) {
        Contact c = new Contact(name, phone);

        FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("userSetting")
                .child("contacts")
                .child(name)
                .setValue(c);
    }

    /**
     * Delete contact.
     *
     * @param name the name
     */
    public void deleteContact(String name) {
        FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("userSetting")
                .child("contacts")
                .child(name)
                .removeValue();
    }
}