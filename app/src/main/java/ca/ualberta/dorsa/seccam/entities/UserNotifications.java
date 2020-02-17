package ca.ualberta.dorsa.seccam.entities;

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
