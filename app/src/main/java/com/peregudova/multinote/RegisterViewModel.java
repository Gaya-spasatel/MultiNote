package com.peregudova.multinote;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.requests.RegisterAnswer;
import com.peregudova.multinote.requests.Requester;
import com.peregudova.multinote.requests.User;

import java.io.IOException;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterAnswer> answerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public MutableLiveData<RegisterAnswer> getAnswerMutableLiveData() {
        return answerMutableLiveData;
    }

    public MutableLiveData<Boolean> getShowProgress() {
        return showProgress;
    }

    class RegisterAsync extends AsyncTask<User, Void, RegisterAnswer>{

        @Override
        protected RegisterAnswer doInBackground(User... users) {
            RegisterAnswer registerAnswer = new RegisterAnswer("Error", "false");
            for(User user:users){
                try {
                    registerAnswer = new Requester().registerUser(user);
                } catch (IOException ignored) {

                }
            }
            return registerAnswer;
        }

        @Override
        protected void onPostExecute(RegisterAnswer registerAnswer) {
            answerMutableLiveData.postValue(registerAnswer);
            showProgress.postValue(false);
        }
    }

    public void registerUser(String login, String password, String email){
        showProgress.postValue(true);
        RegisterAsync registerAsync = new RegisterAsync();
        registerAsync.execute(new User(login, password, email));
    }

}
