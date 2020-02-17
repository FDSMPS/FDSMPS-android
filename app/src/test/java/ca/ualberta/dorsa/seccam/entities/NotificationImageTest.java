package ca.ualberta.dorsa.seccam.entities;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the methods in the NotificationImage file
 */
public class NotificationImageTest {
    /**
     * Checks that getting notificationId is correct.
     */
    @Test
    public void getNotificationId() {
        NotificationImage ni = new NotificationImage("12345", "abcde");
        assertEquals("12345", ni.getNotificationId());
    }

    /**
     * Checks that setting notificationId is correct.
     */
    @Test
    public void setNotificationId() {
        NotificationImage ni = new NotificationImage("", "");
        ni.setNotificationId("12345");
        assertEquals("12345", ni.getNotificationId());
    }

    /**
     * Checks that getting imageString is correct.
     */
    @Test
    public void getImageString() {
        NotificationImage ni = new NotificationImage("12345", "abcde");
        assertEquals("abcde", ni.getImageString());
    }

    /**
     * Checks that setting imageString is correct.
     */
    @Test
    public void setImageString() {
        NotificationImage ni = new NotificationImage("", "");
        ni.setImageString("abcde");
        assertEquals("abcde", ni.getImageString());
    }
}
