package ca.ualberta.dorsa.seccam.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.RecyclerViewListener;
import ca.ualberta.dorsa.seccam.entities.Contact;

/**
 * The type Contact adapter. This is the adaptor that shows the list of the contacts of the users
 * Executed UI tested yet to be unit tested
 *  @author Jessica D'Cunha
 *  @date 2020-1-31
 *  Project: ECE 492 Group 1
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * The Contact name.
         */
        public TextView contactName;
        /**
         * The Message button.
         */
        public ImageButton messageButton;
        /**
         * The Call button.
         */
        public ImageButton callButton;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
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

    /**
     * Instantiates a new Contact adapter.
     *
     * @param listener the listener
     * @param context  the context
     * @param contacts the contacts
     */
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
            holder.contactName.setOnLongClickListener(view -> {
                listener.recyclerViewOnLongClick(view, position);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

