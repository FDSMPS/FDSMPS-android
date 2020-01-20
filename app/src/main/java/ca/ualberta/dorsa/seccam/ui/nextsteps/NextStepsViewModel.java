package ca.ualberta.dorsa.seccam.ui.nextsteps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NextStepsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NextStepsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is next steps fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}