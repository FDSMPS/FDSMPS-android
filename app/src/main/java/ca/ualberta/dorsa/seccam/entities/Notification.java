package ca.ualberta.dorsa.seccam.entities;

import java.util.Date;

/**
 * The type Notification. this is to createthe notification object
 * Executed UI tested yet to be unit tested
 * @author Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class Notification {
    private Date datetime;
    private String notificationId;

    /**
     * Instantiates a new Notification.
     *
     * @param datetime       the datetime
     * @param notificationId the notification id
     */
    public Notification(Date datetime, String notificationId) {
        this.datetime = datetime;
        this.notificationId = notificationId;
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
