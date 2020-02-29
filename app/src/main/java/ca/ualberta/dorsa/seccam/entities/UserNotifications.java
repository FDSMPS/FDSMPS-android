package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Notification. this is to create the user notification
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class UserNotifications {
    /**
     * Instantiates a new User notifications.
     *
     * @param notificationId the notification id
     * @param read           the read
     */
    public UserNotifications(String notificationId, Boolean read) {
        this.notificationId = notificationId;
        this.read = read;
    }

    /**
     * Instantiates a new User notifications.
     */
    public UserNotifications() {
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

    /**
     * Gets read.
     *
     * @return the read
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Sets read.
     *
     * @param read the read
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * The Notification id.
     */
    String notificationId;
    /**
     * The Read.
     */
    Boolean read;

}
