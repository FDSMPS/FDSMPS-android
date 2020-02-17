package ca.ualberta.dorsa.seccam.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the methods in the User file
 */
public class UserTest {
    private User u;

    /**
     * Populates User object for testing.
     */
    @Before
    public void setUp() {
        u = new User(
                "Jessica D'Cunha",
                "7801234567",
                "jdcunha@example.com",
                "11111",
                "code",
                null,
                null
        );
    }

    /**
     * Checks that getting name is correct.
     */
    @Test
    public void getName() {
        assertEquals("Jessica D'Cunha", u.getName());
    }

    /**
     * Checks that setting name is correct.
     */
    @Test
    public void setName() {
        u.setName("Dorsa Nahid");
        assertEquals("Dorsa Nahid", u.getName());
    }

    /**
     * Checks that getting phone is correct.
     */
    @Test
    public void getPhone() {
        assertEquals("7801234567", u.getPhone());
    }

    /**
     * Checks that setting phone is correct.
     */
    @Test
    public void setPhone() {
        u.setPhone("7809876543");
        assertEquals("7809876543", u.getPhone());
    }

    /**
     * Checks that getting email is correct.
     */
    @Test
    public void getEmail() {
        assertEquals("jdcunha@example.com", u.getEmail());
    }

    /**
     * Checks that setting email is correct.
     */
    @Test
    public void setEmail() {
        u.setEmail("dcunhaj@example.com");
        assertEquals("dcunhaj@example.com", u.getEmail());
    }

    /**
     * Checks that getting uid is correct.
     */
    @Test
    public void getUid() {
        assertEquals("11111", u.getuId());
    }

    /**
     * Checks that setting uid is correct.
     */
    @Test
    public void setUid() {
        u.setuId("99999");
        assertEquals("99999", u.getuId());
    }

    /**
     * Checks that getting cameraCode is correct.
     */
    @Test
    public void getCameraCode() {
        assertEquals("code", u.getCameraCode());
    }

    /**
     * Checks that setting cameraCode is correct.
     */
    @Test
    public void setCameraCode() {
        u.setCameraCode("anothercode");
        assertEquals("anothercode", u.getCameraCode());
    }
}
