package ca.ualberta.dorsa.seccam.entities;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * To test the methods in the Contact file
 */
public class ContactTest {
    /**
     * Checks that getting name is correct.
     */
    @Test
    public void getName() {
        Contact c = new Contact("Jessica D'Cunha", "7801234567");
        assertEquals("Jessica D'Cunha", c.getName());
    }

    /**
     * Checks that setting name is correct.
     */
    @Test
    public void setName() {
        Contact c = new Contact("", "");
        c.setName("Jessica D'Cunha");
        assertEquals("Jessica D'Cunha", c.getName());
    }

    /**
     * Checks that getting phone is correct.
     */
    @Test
    public void getPhone() {
        Contact c = new Contact("Jessica D'Cunha", "7801234567");
        assertEquals("7801234567", c.getPhone());
    }

    /**
     * Checks that setting phone is correct.
     */
    @Test
    public void setPhone() {
        Contact c = new Contact("", "");
        c.setPhone("7801234567");
        assertEquals("7801234567", c.getPhone());
    }
}
