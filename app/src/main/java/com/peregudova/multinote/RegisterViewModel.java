package com.peregudova.multinote;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.requests.RegisterAnswer;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterAnswer> answerMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<RegisterAnswer> getAnswerMutableLiveData() {
        return answerMutableLiveData;
    }
}
