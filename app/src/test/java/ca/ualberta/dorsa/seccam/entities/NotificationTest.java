package ca.ualberta.dorsa.seccam.entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the methods in the Notification file
 */
public class NotificationTest {
    /**
     * Checks that getting datetime is correct.
     */
    @Test
    public void getDatetime() {
        Notification n = new Notification(
                new Date(1, 2, 3, 4, 5), "11111"
        );
        assertEquals(1, n.getDatetime().getYear());
        assertEquals(2, n.getDatetime().getMonth());
        assertEquals(3, n.getDatetime().getDate());
        assertEquals(4, n.getDatetime().getHours());
        assertEquals(5, n.getDatetime().getMinutes());
    }

    /**
     * Checks that setting datetime is correct.
     */
    @Test
    public void setDateTime() {
        Notification n = new Notification(
                new Date(), ""
        );
        n.setDatetime(new Date(1, 2, 3, 4, 5));
        assertEquals(2, n.getDatetime().getMonth());
        assertEquals(3, n.getDatetime().getDate());
        assertEquals(4, n.getDatetime().getHours());
        assertEquals(5, n.getDatetime().getMinutes());
    }

    /**
     * Checks that getting notificationId is correct.
     */
    @Test
    public void getNotificationId() {
        Notification n = new Notification(
                new Date(1, 1, 1, 1, 1), "11111"
        );
        assertEquals("11111", n.getNotificationId());
    }

    /**
     * Checks that setting notificationId is correct.
     */
    @Test
    public void setNotificationId() {
        Notification n = new Notification(
                new Date(), ""
        );
        n.setNotificationId("11111");
        assertEquals("11111", n.getNotificationId());
    }
}
