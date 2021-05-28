package com.peregudova.multinote;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.checkers.LoginChecker;
import com.peregudova.multinote.checkers.PasswordChecker;
import com.peregudova.multinote.requests.LoginAnswer;
import com.peregudova.multinote.requests.Requester;
import com.peregudova.multinote.requests.User;

import java.io.IOException;

public class LoginViewModel extends ViewModel {
    private LoginAnswer answer;
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    class LoginTask extends AsyncTask<User, Void, LoginAnswer>{

        @Override
        protected LoginAnswer doInBackground(User... users) {
            LoginAnswer loginAnswer = null;
            for(User user:users){
                try {
                    loginAnswer = new Requester().loginUser(user);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
            return loginAnswer;
        }

        @Override
        protected void onPostExecute(LoginAnswer loginAnswer) {
            answer = loginAnswer;
        }
    }

    public LoginAnswer logIn(String login, String password){
        showProgress.postValue(true);
        //логика авторизации
        //LoginAnswer loginAnswer =
        LoginTask loginTask = (LoginTask) new LoginTask().execute(new User(login, password));
        showProgress.postValue(false);
        return answer;
    }

    public MutableLiveData<Boolean> getProgressState(){
        return showProgress;
    }

    public boolean check(String login, String password){
        return new LoginChecker().check(login) && new PasswordChecker().check(password);
    }
}
