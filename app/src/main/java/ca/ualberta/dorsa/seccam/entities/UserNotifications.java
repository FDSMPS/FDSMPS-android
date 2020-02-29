package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Notification. this is to create the user notification
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class UserNotifications {
    public UserNotifications(String notificationId, Boolean read) {
        this.notificationId = notificationId;
        this.read = read;
    }

    public UserNotifications() {
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    String notificationId;
    Boolean read;

}
