package ca.ualberta.dorsa.seccam.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.security.crypto.EncryptedFile;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.Notification;

/**
 * The type Notification. this is to create the notification Adapter
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
import static ca.ualberta.dorsa.seccam.activities.FullscreenIntroActivity.masterKeyAlias;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * The Notification date.
         */
        public TextView notificationDate;
        /**
         * The Notification time.
         */
        public TextView notificationTime;
        /**
         * The Log notification date.
         */
        public TextView logNotificationDate;
        /**
         * The Log notification time.
         */
        public TextView logNotificationTime;
        /**
         * The Notification image.
         */
        public ImageView notificationImage;
        /**
         * The Notification item.
         */
        public Button saveImageNotification;
        public View notificationItem;
        /**
         * The Add dialog builder.
         */
        public AlertDialog.Builder addDialogBuilder;
        /**
         * The Dialog view.
         */
        public View dialogView;
        public Dialog addDialog;


        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);
            addDialogBuilder = new AlertDialog.Builder(context);
            dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.image_notification_dialog, null);
            addDialogBuilder.setView(dialogView);
            addDialog = addDialogBuilder.create();

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
                        logNotificationDate.setText(notification.getDate());
                        logNotificationTime.setText(notification.getTime());
                        notificationImage.setImageBitmap(StringToBitMap(imageData));

                        saveImageNotification.setOnClickListener(v -> {
                            saveToInternalStorage(StringToBitMap(imageData), notification.getNotificationId());
//                                Toast.makeText(context, notification.getNotificationId(), Toast.LENGTH_SHORT).show();
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

        private boolean saveToInternalStorage(Bitmap bitmapImage, String notificationId) {
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());

            folder.mkdir();
            if (folder.exists()) {
                try {
                    EncryptedFile encryptedFile = new EncryptedFile.Builder(
                            new File(folder.getAbsolutePath(), notificationId),
                            context,
                            masterKeyAlias,
                            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
                    ).build();

                    // Write to a file.
                    FileOutputStream encryptedOutputStream = encryptedFile.openFileOutput();
                    byte[] contentInBytes = BitMapToString(bitmapImage).getBytes();

                    encryptedOutputStream.write(contentInBytes);
                    encryptedOutputStream.flush();
                    encryptedOutputStream.close();
                    addDialog.dismiss();
                } catch (GeneralSecurityException gse) {
                    addDialog.dismiss();
                    gse.printStackTrace();
                    // Error occurred getting or creating keyset.
                } catch (IOException ex) {
                    addDialog.dismiss();
                    // Error occurred opening file for writing.
                }
                return false;
            } else {
                addDialog.dismiss();
                return false;
            }
        }
    }
    /**
     * Turns Bitmap into a string
     *
     * @param bitmap       the bitmap
     */
    private String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private Context context;
    private ArrayList<Notification> notifications;


    /**
     * Instantiates a new Notification adapter.
     *
     * @param context       the context
     * @param notifications the notifications
     */
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

    /**
     * String to bit map bitmap.
     *
     * @param image the image
     * @return the bitmap
     */
    private Bitmap StringToBitMap(String image) {

        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}

