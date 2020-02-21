package ca.ualberta.dorsa.seccam.ui.gallery;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * The type Settings view model.
 * Executed UI tested yet to be unit tested
 * Executed UI tested yet to be unit tested
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    /**
     * Instantiates a new Settings view model.
     */
    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
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