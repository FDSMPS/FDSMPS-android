package ca.ualberta.dorsa.seccam.ui.alerts;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Notification. this is to create the notification Adapter
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class AlertsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    /**
     * Instantiates a new Alerts view model.
     */
    public AlertsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is alerts fragment");
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public LiveData<String> getText() {
        return mText;
    }
}