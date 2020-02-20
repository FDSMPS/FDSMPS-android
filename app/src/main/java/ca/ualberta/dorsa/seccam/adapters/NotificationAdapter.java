package ca.ualberta.dorsa.seccam.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView notificationDate;
        public TextView notificationTime;
        public TextView logNotificationDate;
        public TextView logNotificationTime;
        public ImageView notificationImage;
        public Button saveImageNotification;
        public View notificationItem;
        public AlertDialog.Builder addDialogBuilder;
        public View dialogView;

        public ViewHolder(View itemView) {
            super(itemView);
            addDialogBuilder = new AlertDialog.Builder(context);
            dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.image_notification_dialog, null);
            addDialogBuilder.setView(dialogView);

            notificationDate = (TextView) itemView.findViewById(R.id.notification_date);
            notificationTime = (TextView) itemView.findViewById(R.id.notification_time);
            logNotificationDate = (TextView) dialogView.findViewById(R.id.log_notification_date);
            logNotificationTime = (TextView) dialogView.findViewById(R.id.log_notification_time);
            notificationImage = (ImageView) dialogView.findViewById(R.id.notification_image);

            saveImageNotification = (Button) dialogView.findViewById(R.id.saveImageNotification);

            notificationItem = (View) itemView.findViewById(R.id.notificationItem);
            notificationItem.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Notification notification = notifications.get(getAdapterPosition());

            Log.d("IMGIDS", notification.getNotificationId());


            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("NotificationImages/" + notification.getImageId());

            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        String imageData = (String) dataSnapshot.child("imageData").getValue();
                        Log.d("IMGIDS", imageData);
                        Dialog addDialog = addDialogBuilder.create();
                        logNotificationDate.setText(notification.getDate());
                        logNotificationTime.setText(notification.getTime());
                        notificationImage.setImageBitmap(StringToBitMap(imageData));

                        saveImageNotification.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, notification.getNotificationId(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        addDialog.show();

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

        // Inflate the custom layoutcontact_row
        View contactView = inflater.inflate(R.layout.notification_row, parent, false);

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

    public Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

}

