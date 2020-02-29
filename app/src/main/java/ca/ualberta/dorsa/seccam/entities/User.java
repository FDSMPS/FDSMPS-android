package ca.ualberta.dorsa.seccam.entities;


import java.util.HashMap;

/**
 * The type User.
 * UI and unit tested
 *
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class User extends Person {
    private String email, uId;
    private String cameraCode;
    private HashMap<String,Notification> notifications;
    private UserSettings userSettings;

    /**
     * Instantiates a new User.
     *
     * @param name          the name
     * @param email         the email
     * @param phone         the phone
     * @param uId           the unique id
     * @param cameraCode    the camera code
     * @param notifications the notifications
     * @param userSettings   the user setting
     */
    public User(String name, String phone, String email, String uId, String cameraCode, HashMap<String, Notification> notifications, UserSettings userSettings) {
        super(name, phone);
        this.email = email;
        this.uId = uId;
        this.cameraCode = cameraCode;
        this.notifications = new HashMap<String, Notification>();
        this.userSettings = userSettings;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getuId() {
        return uId;
    }

    /**
     * Sets id.
     *
     * @param uId the u id
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * Gets camera code.
     *
     * @return the camera code
     */
    public String getCameraCode() {
        return cameraCode;
    }

    /**
     * Sets camera code.
     *
     * @param cameraCode the camera code
     */
    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    /**
     * Gets notifications.
     *
     * @return the notifications
     */
    public HashMap<String,Notification>  getNotifications() {
        return notifications;
    }

    /**
     * Sets notifications.
     *
     * @param notifications the notifications
     */
    public void setNotifications(HashMap<String,Notification>  notifications) {
        this.notifications = notifications;
    }

    /**
     * Gets user settings.
     *
     * @return the user settings
     */
    public UserSettings getUserSettings() {
        return userSettings;
    }

    /**
     * Sets user settings.
     *
     * @param userSettings the user settings
     */
    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }
}
