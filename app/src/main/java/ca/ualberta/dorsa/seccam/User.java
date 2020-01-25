package ca.ualberta.dorsa.seccam;


public class User {
    private String name, email, phone, uID;
    private String cameraCode;
    private SecCamNotification notifications;
    private UserSettings userSettings;

    public User(String name, String email, String phone, String uID, String cameraCode, SecCamNotification notifications, UserSettings userSetting) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.uID = uID;
        this.cameraCode = cameraCode;
        this.notifications = notifications;
        this.userSettings = userSetting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public SecCamNotification getNotifications() {
        return notifications;
    }

    public void setNotifications(SecCamNotification notifications) {
        this.notifications = notifications;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public User() {

    }


}
