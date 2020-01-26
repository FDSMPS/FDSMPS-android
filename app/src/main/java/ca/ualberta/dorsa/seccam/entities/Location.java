package ca.ualberta.dorsa.seccam.entities;


import com.google.android.gms.maps.model.LatLng;

public class Location {

    private double latitude;
    private double longitude;

    public Location (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
