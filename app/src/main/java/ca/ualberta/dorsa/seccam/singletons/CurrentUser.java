package ca.ualberta.dorsa.seccam.singletons;
import ca.ualberta.dorsa.seccam.entities.User;

public class CurrentUser {
    private static final User userInstance = new User();

    /**
     * Gets the current user instance
     * @return the current user
     */
    public static User getInstance() {
        return userInstance;
    }

    /**
     * Sets the current user
     * @param user: the user to set to the current user
     */
    public static void setUser(User user) {
        getInstance().setuId(user.getuId());
        getInstance().setEmail(user.getEmail());
        getInstance().setName(user.getName());
        getInstance().setPhone(user.getPhone());
        getInstance().setNotifications(user.getNotifications());
        getInstance().setCameraCode(user.getCameraCode());
        getInstance().setUserSettings(user.getUserSettings());
    }
}
