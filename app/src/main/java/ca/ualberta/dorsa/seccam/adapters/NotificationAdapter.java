package ca.ualberta.dorsa.seccam.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView notificationDate;
        public TextView notificationTime;

        public ViewHolder(View itemView) {
            super(itemView);

            notificationDate = (TextView) itemView.findViewById(R.id.notification_date);
            notificationTime = (TextView) itemView.findViewById(R.id.notification_time);

        }

        @Override
        public void onClick(View view) {
            Notification notification = notifications.get(getAdapterPosition());
//            TODO grab image from firebase and display

            AlertDialog.Builder addDialogBuilder = new AlertDialog.Builder(context);
            addDialogBuilder.setTitle("Add Emergency Contact");

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            final TextView date = new TextView(context);
            date.setText(notification.getDate());

            final TextView time = new TextView(context);
            time.setText(notification.getTime());

            final ImageView notImage = new ImageView(context);
            notImage.setImageDrawable(context.getDrawable(R.drawable.logo_transparent));

            layout.addView(date);
            layout.addView(time);
            layout.addView(notImage);

            addDialogBuilder.setView(layout);

            addDialogBuilder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.cancel());

            AlertDialog addDialog = addDialogBuilder.create();
            addDialog.show();


        }
    }

    private Context context;
    private ArrayList<Notification> notifications;


    public NotificationAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
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
        Notification notification = notifications.get(position);

        // Set item views based on your views and data model
        TextView dateTextView = holder.notificationDate;
        dateTextView.setText(notification.getDate());

        TextView timeTextView = holder.notificationTime;
        timeTextView.setText(notification.getTime());

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

}

