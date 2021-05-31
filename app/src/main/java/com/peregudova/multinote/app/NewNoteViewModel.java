package com.peregudova.multinote.app;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.requests.NewAnswer;
import com.peregudova.multinote.requests.NewNoteCommand;

public class NewNoteViewModel extends ViewModel {
    private MutableLiveData<NewAnswer> newAnswerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public MutableLiveData<NewAnswer> getNewAnswerMutableLiveData() {
        return newAnswerMutableLiveData;
    }

    public MutableLiveData<Boolean> getShowProgress() {
        return showProgress;
    }

    class NewNoteAsync extends AsyncTask<NewNoteCommand, Void, NewAnswer>{

        @Override
        protected NewAnswer doInBackground(NewNoteCommand... newNoteCommands) {
            return null;
        }

        @Override
        protected void onPostExecute(NewAnswer newAnswer) {
            newAnswerMutableLiveData.postValue(newAnswer);
        }
    }
}
