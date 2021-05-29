package com.peregudova.multinote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peregudova.multinote.requests.Note;
import com.peregudova.multinote.requests.NotesAnswer;

import java.util.List;
import java.util.Map;

public class AppActivity extends AppCompatActivity {
    private TextView textView;
    AllNotesViewModel allNotesViewModel;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        textView = findViewById(R.id.textView2);
        rv = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        String user;
        String token;
        user = getIntent().getExtras().getString("user");
        token = getIntent().getExtras().getString("token");
        String text = user+" "+token;
        textView.setText(text);
        allNotesViewModel = ViewModelProviders.of(this).get(AllNotesViewModel.class);
        allNotesViewModel.getProgressState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });
        allNotesViewModel.getNotesAnswerMutableLiveData().observe(this, new Observer<NotesAnswer>() {
            @Override
            public void onChanged(NotesAnswer notesAnswer) {
                //логика обновления списка заметок
            }
        });
        allNotesViewModel.getallnotes(user, token);
    }
    private void showProgress() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }
    private void hideProgress(){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

}