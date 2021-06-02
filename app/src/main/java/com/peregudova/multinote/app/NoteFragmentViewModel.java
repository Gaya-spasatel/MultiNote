package com.peregudova.multinote.app;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.requests.GetAllNotesCommand;
import com.peregudova.multinote.requests.GetNoteCommand;
import com.peregudova.multinote.requests.ListAccessAnswer;
import com.peregudova.multinote.requests.ListAccessCommand;
import com.peregudova.multinote.requests.Note;
import com.peregudova.multinote.requests.NoteAnswer;
import com.peregudova.multinote.requests.NotesAnswer;
import com.peregudova.multinote.requests.Requester;

import java.io.IOException;

public class NoteFragmentViewModel extends ViewModel {
    private MutableLiveData<NoteAnswer> noteAnswerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListAccessAnswer> listAccessAnswerMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NoteAnswer> getNoteAnswerMutableLiveData() {
        return noteAnswerMutableLiveData;
    }

    public MutableLiveData<ListAccessAnswer> getListAccessAnswerMutableLiveData() {
        return listAccessAnswerMutableLiveData;
    }

    class NoteAsync extends AsyncTask<GetNoteCommand, Void, NoteAnswer>{

        @Override
        protected NoteAnswer doInBackground(GetNoteCommand... getNoteCommands) {
            NoteAnswer notesAnswer = new NoteAnswer("Error", null);
            for(GetNoteCommand getNoteCommand:getNoteCommands){
                try {
                    notesAnswer = new Requester().getnote(getNoteCommand);
                } catch (IOException ignored) {

                }
            }
            return notesAnswer;
        }

        @Override
        protected void onPostExecute(NoteAnswer noteAnswer) {
            noteAnswerMutableLiveData.postValue(noteAnswer);
        }
    }

    class ListAccessAsync extends AsyncTask<ListAccessCommand, Void, ListAccessAnswer>{

        @Override
        protected ListAccessAnswer doInBackground(ListAccessCommand... listAccessCommands) {
            ListAccessAnswer listAccessAnswer = new ListAccessAnswer("Error in app", null);
            for(ListAccessCommand listAccessCommand:listAccessCommands){
                try {
                    listAccessAnswer = new Requester().listAccess(listAccessCommand);
                } catch (IOException ignored) {

                }
            }
            return listAccessAnswer;
        }

        @Override
        protected void onPostExecute(ListAccessAnswer listAccessAnswer) {
            listAccessAnswerMutableLiveData.postValue(listAccessAnswer);
        }
    }

    public void getNote(Note note, String user, String token){
        NoteAsync noteAsync = new NoteAsync();
        noteAsync.execute(new GetNoteCommand(token, user, note.getId()));
    }

    public void getListAccess(String user, String token, String id_note){
        ListAccessAsync listAccessAsync = new ListAccessAsync();
        listAccessAsync.execute(new ListAccessCommand(token, user, id_note));
    }

}
