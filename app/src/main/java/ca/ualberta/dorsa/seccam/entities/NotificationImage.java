package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Notification image.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class NotificationImage {
    private String notificationID;
    private String imageString;

    /**
     * Instantiates a new Notification image.
     *
     * @param notificationID the notification id
     * @param imageString    the image string
     */
    public NotificationImage(String notificationID, String imageString) {
        this.notificationID = notificationID;
        this.imageString = imageString;
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

    /**
     * Gets image string.
     *
     * @return the image string
     */
    public String getImageString() {
        return imageString;
    }

    /**
     * Sets image string.
     *
     * @param imageString the image string
     */
    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
