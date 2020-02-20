package ca.ualberta.dorsa.seccam.entities;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Notification. this is to create the notification object
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -1-31 Project: ECE 492 Group 1
 */
public class Notification {
    private String cameraCode;
    private String datetime;
    private String imageId;
    private String notificationId;

    public Notification() {
    }

    /**
     * Instantiates a new Notification.
     *
     * @param cameraCode     the camera code
     * @param datetime       the datetime
     * @param imageId        the image id
     * @param notificationId the notification id
     */
    public Notification(String cameraCode, String datetime, String imageId, String notificationId) {
        this.cameraCode = cameraCode;
        this.datetime = datetime;
        this.imageId = imageId;
        this.notificationId = notificationId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(datetime);
            Log.d("MYDATETIME",date.toString());
            SimpleDateFormat convetDateFormat = new SimpleDateFormat("E, MMMM dd, yyyy");
            return convetDateFormat.format(date);
        } catch (ParseException e) {
            Log.d("MYDATETIME","failed");

            e.printStackTrace();
        }
        Log.d("MYDATETIME",datetime);
        return datetime;

    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = dateFormat.parse(datetime);
            Log.d("MYDATETIME",time.toString());
            SimpleDateFormat convetDateFormat = new SimpleDateFormat("h:mm a");
            return convetDateFormat.format(time);
        } catch (ParseException e) {
            Log.d("MYDATETIME","failed");

            e.printStackTrace();
        }
        Log.d("MYDATETIME",datetime);
        return datetime;
    }


    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Gets notification id.
     *
     * @return the notification id
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * Sets notification id.
     *
     * @param notificationId the notification id
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }
}
