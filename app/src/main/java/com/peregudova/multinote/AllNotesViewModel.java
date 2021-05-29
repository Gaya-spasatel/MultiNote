package com.peregudova.multinote;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllNotesViewModel extends ViewModel {
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public MutableLiveData<Boolean> getProgressState(){
        return showProgress;
    }
}
