package ca.ualberta.dorsa.seccam.entities;

import java.util.ArrayList;

import ca.ualberta.dorsa.seccam.entities.Contact;
import ca.ualberta.dorsa.seccam.entities.Location;

/**
 * The type User settings.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class UserSettings {
    private String QRCode;
    private ArrayList<Contact> contacts;
    private Location location;
    private Boolean enable;

    /**
     * Instantiates a new User settings.
     *
     * @param QRCode   the qr code
     * @param contacts the contacts
     * @param location the location
     * @param enable   the enable
     */
    public UserSettings(String QRCode, ArrayList<Contact> contacts, Location location, Boolean enable) {
        this.QRCode = QRCode;
        this.contacts = contacts;
        this.location = location;
        this.enable = enable;
    }

    /**
     * Gets qr code.
     *
     * @return the qr code
     */
    public String getQRCode() {
        return QRCode;
    }

    /**
     * Sets qr code.
     *
     * @param QRCode the qr code
     */
    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    /**
     * Gets contacts.
     *
     * @return the contacts
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts.
     *
     * @param contacts the contacts
     */
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets enable.
     *
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * Sets enable.
     *
     * @param enable the enable
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
