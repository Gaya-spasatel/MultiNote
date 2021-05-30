package com.peregudova.multinote;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.peregudova.multinote.requests.GetAllNotesCommand;
import com.peregudova.multinote.requests.GetNoteCommand;
import com.peregudova.multinote.requests.Note;
import com.peregudova.multinote.requests.NoteAnswer;
import com.peregudova.multinote.requests.NotesAnswer;
import com.peregudova.multinote.requests.Requester;

import java.io.IOException;

public class NoteFragmentViewModel extends ViewModel {
    private MutableLiveData<NoteAnswer> noteAnswerMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NoteAnswer> getNoteAnswerMutableLiveData() {
        return noteAnswerMutableLiveData;
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

    public void getNote(Note note, String user, String token){
        NoteAsync noteAsync = (NoteAsync) new NoteAsync().execute(new GetNoteCommand(token, user, note.getId()));
    }

}
