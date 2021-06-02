package com.peregudova.multinote.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.peregudova.multinote.R;
import com.peregudova.multinote.requests.ListAccessAnswer;
import com.peregudova.multinote.requests.Note;
import com.peregudova.multinote.requests.NoteAnswer;

import org.jetbrains.annotations.NotNull;

public class NoteFragment extends Fragment implements View.OnClickListener {

    NoteFragmentViewModel noteFragmentViewModel;
    Button button;
    TextView note_text;
    TextView note_info;
    EditText text_add_access;
    TextView list_access;
    FragmentViewViewModel fragmentViewViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteFragmentViewModel = ViewModelProviders.of(this).get(NoteFragmentViewModel.class);
        noteFragmentViewModel.getNoteAnswerMutableLiveData().observe(this, new Observer<NoteAnswer>() {
            @Override
            public void onChanged(NoteAnswer noteAnswer) {
                //вызов функций отображения контента
                showContent(noteAnswer);

            }
        });
        noteFragmentViewModel.getListAccessAnswerMutableLiveData().observe(this, new Observer<ListAccessAnswer>() {
            @Override
            public void onChanged(ListAccessAnswer listAccessAnswer) {
                showListAccess(listAccessAnswer);
            }
        });
    }

    private void showListAccess(ListAccessAnswer listAccessAnswer) {
        StringBuffer list = new StringBuffer();
        for(String key:listAccessAnswer.getListaccess().keySet()){
            list.append(key).append(". ").append(listAccessAnswer.getListaccess().get(key).getAccess_login()).append("\n");
        }
        list_access.setText(list);
    }

    public void setFragmentViewViewModel(FragmentViewViewModel fragmentViewViewModel) {
        this.fragmentViewViewModel = fragmentViewViewModel;
        this.fragmentViewViewModel.getViewFragment().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    setVisible();
                } else{
                    setInvisible();
                }
            }
        });
    }

    public void setVisible(){
        getView().setVisibility(View.VISIBLE);
    }

    public void setInvisible(){
        getView().setVisibility(View.INVISIBLE);
    }

    private void showContent(NoteAnswer noteAnswer) {
        setVisible();
        note_text.setText(noteAnswer.getNote().getText());
        Note note = noteAnswer.getNote();
        String info = "Is modified: "+note.getIs_modified()+"\nLast modified: "+note.getLast_modified()+"\nLogin modified: "+note.getLogin_modified()+"\nOwner: "+note.getOwner();
        note_info.setText(info);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.note_fragment, container, false);
        button = inflate.findViewById(R.id.add_access_button);
        button.setOnClickListener(this);
        note_text = inflate.findViewById(R.id.note_text);
        note_info = inflate.findViewById(R.id.note_info);
        list_access = inflate.findViewById(R.id.list_access);
        inflate.setVisibility(View.GONE);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        //does smthg after button clicked button add_acces
    }

    public void setNote(Note note, String user, String token) {
        //команда от активности на загрузку data фрагмента
        noteFragmentViewModel.getNote(note, user, token);
        noteFragmentViewModel.getListAccess(user, token, note.getId());
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected();
    }
}
