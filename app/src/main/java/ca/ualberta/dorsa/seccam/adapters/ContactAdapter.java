package ca.ualberta.dorsa.seccam.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.RecyclerViewListener;
import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.ui.nextsteps.NextStepsFragment;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView contactName;
        public ImageButton messageButton;
        public ImageButton callButton;

        public ViewHolder(View itemView) {
            super(itemView);

            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (ImageButton) itemView.findViewById(R.id.message_button);
            callButton = (ImageButton) itemView.findViewById(R.id.call_button);

            messageButton.setOnClickListener(this);
            callButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Contact c = contacts.get(getAdapterPosition());

            switch (view.getId()) {
                case R.id.message_button:
                    Intent messageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + c.getPhone()));
                    context.startActivity(messageIntent);
                    break;

                case R.id.call_button:
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + c.getPhone()));
                    context.startActivity(callIntent);
                    break;

                default:
                    break;
            }
        }
    }

    private RecyclerViewListener listener;
    private Context context;
    private ArrayList<Contact> contacts;

    public ContactAdapter(RecyclerViewListener listener, Context context, ArrayList<Contact> contacts) {
        this.listener = listener;
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.contact_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Contact contact = contacts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.contactName;
        textView.setText(contact.getName());

        // 911 cannot be messaged
        if (contact.getName().equals("911")) {
            holder.messageButton.setEnabled(false);
            holder.messageButton.setVisibility(View.INVISIBLE);
        }
        else {
            holder.contactName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.recyclerViewOnLongClick(view, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

