package ca.ualberta.dorsa.seccam.entities;


import com.google.android.gms.maps.model.LatLng;


/**
 * The type Location, it incldues latitude and longitude
 * UI and unit tested
 *
 * @author Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class Location {

    private double latitude;
    private double longitude;

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Instantiates a new Location.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Location (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get lat lng lat lng.
     *
     * @return the lat lng
     */
    public LatLng getLatLng(){
        return new LatLng(latitude,longitude);
    }

}
