package ca.ualberta.dorsa.seccam.entities;

import java.util.Date;

/**
 * The type Notification. this is to createthe notification object
 * @author Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class Notification {
    private Date datetime;
    private String notificationID;

    /**
     * Instantiates a new Notification.
     *
     * @param datetime       the datetime
     * @param notificationID the notification id
     */
    public Notification(Date datetime, String notificationID) {
        this.datetime = datetime;
        this.notificationID = notificationID;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Gets notification id.
     *
     * @return the notification id
     */
    public String getNotificationID() {
        return notificationID;
    }

    /**
     * Sets notification id.
     *
     * @param notificationID the notification id
     */
    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }
}
