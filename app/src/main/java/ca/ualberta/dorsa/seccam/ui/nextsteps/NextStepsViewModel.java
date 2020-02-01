package ca.ualberta.dorsa.seccam.ui.nextsteps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Next steps view model.
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class NextStepsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    /**
     * Instantiates a new Next steps view model.
     */
    public NextStepsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is next steps fragment");
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