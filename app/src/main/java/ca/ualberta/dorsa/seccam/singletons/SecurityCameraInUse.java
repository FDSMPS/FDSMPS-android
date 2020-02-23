package ca.ualberta.dorsa.seccam.singletons;

import java.security.Security;
import java.util.ArrayList;

import ca.ualberta.dorsa.seccam.entities.SecurityCamera;

public class SecurityCameraInUse {

    private static final SecurityCamera secInstance = new SecurityCamera();

    /**
     * Gets the user's security camera
     * @return the security camera being used
     */
    public static SecurityCamera getInstance() {
        return secInstance;
    }

    /**
     * Sets the security camera
     * @param securityCamera: the security camera in use
     */
    public static void setSecurityCameraInUse(SecurityCamera securityCamera) {
        getInstance().setCameraCode(securityCamera.getCameraCode());
        getInstance().setCameraEnabled(securityCamera.getCameraEnabled());
        getInstance().setLiveFeedImage(securityCamera.getLiveFeedImage());
        getInstance().setRegistered(securityCamera.getRegistered());
        getInstance().setRegisteredUId(securityCamera.getRegisteredUId());
        getInstance().setUsers(securityCamera.getUsers());
    }
}
