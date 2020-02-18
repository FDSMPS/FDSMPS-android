package ca.ualberta.dorsa.seccam.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
        return datetime;

    }

    public String getTime() {
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
