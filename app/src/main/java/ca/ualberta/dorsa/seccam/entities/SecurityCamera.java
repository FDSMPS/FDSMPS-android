package ca.ualberta.dorsa.seccam.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Security camera.
 * UI and unit tested
 * @author Jessica D'Cunha and Dorsa Nahid
 *
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class SecurityCamera {
    private String cameraCode;
    private Boolean cameraEnabled;
    private String liveFeedImage;
    private Boolean registered;
    private String registeredUId;
    private double servoXPosition;
    private double servoYPosition;
    private List<String> users;

    public SecurityCamera(String cameraCode, Boolean cameraEnabled, String liveFeedImage, Boolean registered, String registeredUId) {
        this.cameraCode = cameraCode;
        this.cameraEnabled = cameraEnabled;
        this.liveFeedImage = liveFeedImage;
        this.registered = registered;
        this.registeredUId = registeredUId;
        users = new ArrayList<>();
        users.add(registeredUId);
    }

    public SecurityCamera(){ }

    /**
     * Instantiates a new Security camera.
     *
     * @param liveFeedImage the current image string
     * @param cameraCode             the qr code
     * @param registered         the registered
     * @param users      the registered uid
     */


    /**
     * Gets current image string.
     *
     * @return the current image string
     */
    public String getLiveFeedImage() {
        return liveFeedImage;
    }

    /**
     * Sets current image string.
     *
     * @param liveFeedImage the current image string
     */
    public void setLiveFeedImage(String liveFeedImage) {
        this.liveFeedImage = liveFeedImage;
    }

    /**
     * Gets qr code.
     *
     * @return the qr code
     */
    public String getCameraCode() {
        return cameraCode;
    }

    /**
     * Sets qr code.
     *
     * @param cameraCode the qr code
     */
    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
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
    public List<String> getUsers() {
        return users;
    }

    /**
     * Sets registered uid.
     *
     * @param users the registered uid
     */
    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Boolean getCameraEnabled() {
        return cameraEnabled;
    }

    public void setCameraEnabled(Boolean cameraEnabled) {
        this.cameraEnabled = cameraEnabled;
    }

    public double getServoXPosition() {
        return servoXPosition;
    }

    public void setServoXPosition(double servoXPosition) {
        this.servoXPosition = servoXPosition;
    }

    public double getServoYPosition() {
        return servoYPosition;
    }

    public void setServoYPosition(double servoYPosition) {
        this.servoYPosition = servoYPosition;
    }

    //decode base64 string to image for livefeed
    public Bitmap getDecodedLiveFeedImage() {

        Bitmap decodedImageBitMap = null;
        byte[] imageBytes;

        try {
            imageBytes = Base64.decode(this.getLiveFeedImage(), Base64.DEFAULT);
            decodedImageBitMap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decodedImageBitMap;
    }

}
