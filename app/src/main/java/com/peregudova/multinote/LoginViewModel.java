package com.peregudova.multinote;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.checkers.LoginChecker;
import com.peregudova.multinote.checkers.PasswordChecker;
import com.peregudova.multinote.requests.LoginAnswer;
import com.peregudova.multinote.requests.Requester;
import com.peregudova.multinote.requests.User;

import java.io.IOException;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    public LoginAnswer logIn(String login, String password){
        showProgress.postValue(true);
        //логика авторизации
        try {
            LoginAnswer loginAnswer = new Requester().loginUser(new User(login,password));
            showProgress.postValue(false);
            return loginAnswer;
        } catch (IOException e) {
            showProgress.postValue(false);
            return new LoginAnswer("Error", null, null);
        }
    }

    public MutableLiveData<Boolean> getProgressState(){
        return showProgress;
    }

    public boolean check(String login, String password){
        return new LoginChecker().check(login) && new PasswordChecker().check(password);
    }
}
