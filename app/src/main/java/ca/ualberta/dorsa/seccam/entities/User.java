package ca.ualberta.dorsa.seccam.entities;



/**
 * The type User.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class User extends Person {
    private String email, uId;
    private String cameraCode;
    private Notification notifications;
    private UserSettings userSettings;

    /**
     * Instantiates a new User.
     *
     * @param name          the name
     * @param email         the email
     * @param phone         the phone
     * @param uId           the u id
     * @param cameraCode    the camera code
     * @param notifications the notifications
     * @param userSetting   the user setting
     */
    public User(String name, String email, String phone, String uId, String cameraCode, Notification notifications, UserSettings userSetting) {
        super(name, phone);
        this.email = email;
        this.uId = uId;
        this.cameraCode = cameraCode;
        this.notifications = notifications;
        this.userSettings = userSetting;
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
    public Notification getNotifications() {
        return notifications;
    }

    /**
     * Sets notifications.
     *
     * @param notifications the notifications
     */
    public void setNotifications(Notification notifications) {
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
