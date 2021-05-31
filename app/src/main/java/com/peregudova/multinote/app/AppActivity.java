package com.peregudova.multinote.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peregudova.multinote.R;


public class AppActivity extends AppCompatActivity implements RecyclerViewClickListener{
    private TextView textView;
    AllNotesViewModel allNotesViewModel;
    RecyclerView rv;
    RVAdapter adapter;
    String user;
    String token;
    NoteFragment fragment;
    FragmentViewViewModel fragmentViewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_app);
        textView = findViewById(R.id.textView2);
        rv = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

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
                adapter = new RVAdapter(this, notesAnswer.getNotes(), this);
                rv.setAdapter(adapter);
            }
        });
        allNotesViewModel.getallnotes(user, token);

        fragmentViewViewModel = ViewModelProviders.of(this).get(FragmentViewViewModel.class);
        fragmentViewViewModel.getViewFragment().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    setInvisible();
                } else{
                    setVisible();
                }
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = (NoteFragment) fragmentManager.findFragmentById(R.id.note_fragment);
    }
    private void showProgress() {
        ProgressBar progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }
    private void hideProgress(){
        ProgressBar progressBar =  findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {

        if(fragment!=null){
            fragment.setNote(adapter.getNoteByPosition(position), user, token);
            fragmentViewViewModel.setViewFragment(true);
        }
    }

    public void setVisible(){
        rv.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
    }

    public void setInvisible(){
        rv.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }
}