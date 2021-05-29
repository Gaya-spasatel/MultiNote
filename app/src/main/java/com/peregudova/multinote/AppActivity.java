package com.peregudova.multinote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;



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
        allNotesViewModel = ViewModelProviders.of(this).get(AllNotesViewModel.class);
        allNotesViewModel.getProgressState().observe(this, aBoolean -> {
            if (aBoolean) {
                showProgress();
            } else {
                hideProgress();
            }
        });
        allNotesViewModel.getNotesAnswerMutableLiveData().observe(this, notesAnswer -> {
            //логика обновления списка заметок
            if(notesAnswer.getNotes()!=null) {
                RVAdapter adapter = new RVAdapter(this, notesAnswer.getNotes());
                rv.setAdapter(adapter);
            }
        });
        allNotesViewModel.getallnotes(user, token);

    }
    private void showProgress() {
        ProgressBar progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }
    private void hideProgress(){
        ProgressBar progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

}