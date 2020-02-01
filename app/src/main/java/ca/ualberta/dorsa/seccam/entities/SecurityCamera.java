package ca.ualberta.dorsa.seccam.entities;

/**
 * The type Security camera.
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class SecurityCamera {
    private String currentImageString;
    private String QRCode;
    private Boolean registered;
    private String registeredUID;

    /**
     * Instantiates a new Security camera.
     *
     * @param currentImageString the current image string
     * @param QRCode             the qr code
     * @param registered         the registered
     * @param registeredUID      the registered uid
     */
    public SecurityCamera(String currentImageString, String QRCode, Boolean registered, String registeredUID) {
        this.currentImageString = currentImageString;
        this.QRCode = QRCode;
        this.registered = registered;
        this.registeredUID = registeredUID;
    }

    /**
     * Gets current image string.
     *
     * @return the current image string
     */
    public String getCurrentImageString() {
        return currentImageString;
    }

    /**
     * Sets current image string.
     *
     * @param currentImageString the current image string
     */
    public void setCurrentImageString(String currentImageString) {
        this.currentImageString = currentImageString;
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
     * Gets registered.
     *
     * @return the registered
     */
    public Boolean getRegistered() {
        return registered;
    }

    /**
     * Sets registered.
     *
     * @param registered the registered
     */
    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    /**
     * Gets registered uid.
     *
     * @return the registered uid
     */
    public String getRegisteredUID() {
        return registeredUID;
    }

    /**
     * Sets registered uid.
     *
     * @param registeredUID the registered uid
     */
    public void setRegisteredUID(String registeredUID) {
        this.registeredUID = registeredUID;
    }
}
