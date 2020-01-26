package ca.ualberta.dorsa.seccam.entities;

public class SecurityCamera {
    private String currentImageString;
    private String QRCode;
    private Boolean registered;
    private String registeredUID;

    public SecurityCamera(String currentImageString, String QRCode, Boolean registered, String registeredUID) {
        this.currentImageString = currentImageString;
        this.QRCode = QRCode;
        this.registered = registered;
        this.registeredUID = registeredUID;
    }

    public String getCurrentImageString() {
        return currentImageString;
    }

    public void setCurrentImageString(String currentImageString) {
        this.currentImageString = currentImageString;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getRegisteredUID() {
        return registeredUID;
    }

    public void setRegisteredUID(String registeredUID) {
        this.registeredUID = registeredUID;
    }
}
