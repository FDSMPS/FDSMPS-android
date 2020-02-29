package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Notification image.
 * UI and unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class NotificationImage {
    private String notificationId;
    private String imageString;

    /**
     * Instantiates a new Notification image.
     *
     * @param notificationId the notification id
     * @param imageString    the image string
     */
    public NotificationImage(String notificationId, String imageString) {
        this.notificationId = notificationId;
        this.imageString = imageString;
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
