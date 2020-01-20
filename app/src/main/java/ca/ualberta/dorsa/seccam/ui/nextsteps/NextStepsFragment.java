package ca.ualberta.dorsa.seccam.ui.nextsteps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.myapplication.R;
import ca.ualberta.dorsa.seccam.ContactAdapter;

public class NextStepsFragment extends Fragment {

    private NextStepsViewModel nextStepsViewModel;
    private RecyclerView contactList;
    private ArrayList<String> contacts;
    private ContactAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nextStepsViewModel =
                ViewModelProviders.of(this).get(NextStepsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_next_steps, container, false);

        contactList = root.findViewById(R.id.contact_list);
        contacts = new ArrayList<String>();
        contacts.add("Jessica D'Cunha");
        contacts.add("Bryn Leonard-Fortune");
        contacts.add("Dorsa Nahid");
        contacts.add("Tymoore Jamal");
        adapter = new ContactAdapter(this.getActivity(), contacts);
        contactList.setAdapter(adapter);
        contactList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return root;
    }
}