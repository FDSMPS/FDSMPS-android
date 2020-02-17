package ca.ualberta.dorsa.seccam.entities;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * To test the methods in the UserSettings file
 */
public class UserSettingsTest {
    private UserSettings us;
    private Location l = new Location(88, 22);

    /**
     * Populates UserSettings object for testing.
     */
    @Before
    public void setUp() {
        us = new UserSettings(
                "code",
                null,
                l,
                true
        );
    }

    /**
     * Checks that getting QRCode is correct.
     */
    @Test
    public void getQRCode() {
        assertEquals("code", us.getQRCode());
    }

    /**
     * Checks that setting QRCode is correct.
     */
    @Test
    public void setQRCode() {
        us.setQRCode("another");
        assertEquals("another", us.getQRCode());
    }

    /**
     * Checks that getting location is correct.
     */
    @Test
    public void getLocation() {
        assertEquals(l, us.getLocation());
    }

    /**
     * Checks that setting location is correct.
     */
    @Test
    public void setLocation() {
        Location newLocation = new Location(15, 95);
        us.setLocation(newLocation);
        assertEquals(newLocation, us.getLocation());
    }

    /**
     * Checks that getting enabled is correct.
     */
    @Test
    public void getEnable() {
        assertTrue(us.getEnable());
    }

    /**
     * Checks that setting enabled is correct.
     */
    @Test
    public void setEnable() {
        us.setEnable(Boolean.FALSE);
        assertFalse(us.getEnable());
    }
}
