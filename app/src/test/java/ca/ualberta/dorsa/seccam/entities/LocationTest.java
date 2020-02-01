package ca.ualberta.dorsa.seccam.entities;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To test the methods in the location file
 */
public class LocationTest {

    /**
     * Gets longitude.
     */
    @Test
    public void getLongitude() {
        Location loc = new Location(12,13.14);
        Assert.assertEquals(13.14, loc.getLongitude(),0);
    }

    /**
     * Sets longitude.
     */
    @Test
    public void setLongitude() {
        Location loc = new Location(0,0);
        loc.setLatitude(12);
        Assert.assertEquals(0, loc.getLongitude(),0);
    }

    /**
     * Gets latitude.
     */
    @Test
    public void getLatitude() {
        Location loc = new Location(12,13.14);
        Assert.assertEquals(12, loc.getLatitude(),0);
    }

    /**
     * Sets latitude.
     */
    @Test
    public void setLatitude() {
        Location loc = new Location(0,0);
        loc.setLatitude(12);
        Assert.assertEquals(12, loc.getLatitude(),0);
    }

    /**
     * Gets lat lng.
     */
    @Test
    public void getLatLng() {
        Location loc = new Location(12.13,13.14);
        assertEquals(new LatLng(12.13,13.14), loc.getLatLng());
    }
}