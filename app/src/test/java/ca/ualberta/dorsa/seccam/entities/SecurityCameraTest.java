package ca.ualberta.dorsa.seccam.entities;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * To test the methods in the SecurityCamera file
 */
public class SecurityCameraTest {
    private SecurityCamera sc;

    /**
     * Populate SecurityCamera object for testing.
     */
    @Before
    public void setUp() {
        sc = new SecurityCamera(
                "code",
                true,
                "image",
                true,
                "12345"
        );
    }

    /**
     * Checks that getting cameraCode is correct.
     */
    @Test
    public void getCameraCode() {
        assertEquals("code", sc.getCameraCode());
    }

    /**
     * Checks that setting currentImageString is correct.
     */
    @Test
    public void setCameraCode() {
        sc.setCameraCode("another");
        assertEquals("another", sc.getCameraCode());
    }

    /**
     * Checks that getting cameraEnabled is correct.
     */
    @Test
    public void getCameraEnabled() {
        assertTrue(sc.getCameraEnabled());
    }

    /**
     * Checks that setting cameraEnabled is correct.
     */
    @Test
    public void setCameraEnabled() {
        sc.setCameraEnabled(Boolean.FALSE);
        assertFalse(sc.getCameraEnabled());
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
}
