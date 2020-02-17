package ca.ualberta.dorsa.seccam.entities;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * To test the methods in the Contact file
 */
public class SecurityCameraTest {
    private SecurityCamera sc;

    /**
     * Populate SecurityCamera object for testing.
     */
    @Before
    public void setUp() {
        sc = new SecurityCamera("image", "qrcode", true, "12345");
    }

    /**
     * Checks that getting currentImageString is correct.
     */
    @Test
    public void getCurrentImageString() {
        assertEquals("image", sc.getCurrentImageString());
    }

    /**
     * Checks that setting currentImageString is correct.
     */
    @Test
    public void setCurrentImageString() {
        sc.setCurrentImageString("another");
        assertEquals("another", sc.getCurrentImageString());
    }

    /**
     * Checks that getting QRCode is correct.
     */
    @Test
    public void getQRCode() {
        assertEquals("qrcode", sc.getQRCode());
    }

    /**
     * Checks that setting QRCode is correct.
     */
    @Test
    public void setQRCode() {
        sc.setQRCode("different");
        assertEquals("different", sc.getQRCode());
    }

    /**
     * Checks that getting registered is correct.
     */
    @Test
    public void getRegistered() {
        assertTrue(sc.getRegistered());
    }

    /**
     * Checks that setting registered is correct.
     */
    @Test
    public void setRegistered() {
        sc.setRegistered(Boolean.FALSE);
        assertFalse(sc.getRegistered());
    }

    /**
     * Checks that getting registeredUid is correct.
     */
    @Test
    public void getRegisteredUid() {
        assertEquals("12345", sc.getRegisteredUid());
    }

    /**
     * Checks that setting registeredUid is correct.
     */
    @Test
    public void setRegisteredUid() {
        sc.setRegisteredUid("67890");
        assertEquals("67890", sc.getRegisteredUid());
    }
}
