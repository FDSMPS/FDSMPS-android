package ca.ualberta.dorsa.seccam.entities;


import ca.ualberta.dorsa.seccam.entities.Notification;
import ca.ualberta.dorsa.seccam.entities.UserSettings;

public class User extends Person {
    private String email, uID;
    private String cameraCode;
    private Notification notifications;
    private UserSettings userSettings;

    public User(String name, String email, String phone, String uID, String cameraCode, Notification notifications, UserSettings userSetting) {
        super(name, phone);
        this.email = email;
        this.uID = uID;
        this.cameraCode = cameraCode;
        this.notifications = notifications;
        this.userSettings = userSetting;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public Notification getNotifications() {
        return notifications;
    }

    public void setNotifications(Notification notifications) {
        this.notifications = notifications;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }
}
