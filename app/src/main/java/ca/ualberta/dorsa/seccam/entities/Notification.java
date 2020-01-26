package ca.ualberta.dorsa.seccam.entities;

import java.util.Date;

public class Notification {
    private Date datetime;
    private String notificationID;

    public Notification(Date datetime, String notificationID) {
        this.datetime = datetime;
        this.notificationID = notificationID;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }
}
