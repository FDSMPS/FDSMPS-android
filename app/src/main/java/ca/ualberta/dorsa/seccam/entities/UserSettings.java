package ca.ualberta.dorsa.seccam.entities;

import java.util.ArrayList;

import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.entities.Location;

public class UserSettings {
    private String QRCode;
    private ArrayList<Contact> contacts;
    private Location location;
    private Boolean enable;

    public UserSettings(String QRCode, ArrayList<Contact> contacts, Location location, Boolean enable) {
        this.QRCode = QRCode;
        this.contacts = contacts;
        this.location = location;
        this.enable = enable;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
