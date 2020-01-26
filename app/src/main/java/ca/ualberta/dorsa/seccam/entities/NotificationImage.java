package ca.ualberta.dorsa.seccam.entities;

public class NotificationImage {
    private String notificationID;
    private String imageString;

    public NotificationImage(String notificationID, String imageString) {
        this.notificationID = notificationID;
        this.imageString = imageString;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
