package com.peregudova.multinote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;



public class AppActivity extends AppCompatActivity implements RecyclerViewClickListener{
    private TextView textView;
    AllNotesViewModel allNotesViewModel;
    RecyclerView rv;
    RVAdapter adapter;
    String user;
    String token;
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
        Log.d("Catch click", "Click cathed. Position"+position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        NoteFragment fragment = (NoteFragment) fragmentManager.findFragmentById(R.id.note_fragment);
        if(fragment!=null){
            //TextView id = v.findViewById(R.id.id_note);
            //String note_id = id.getText().toString();
            Log.d("", "Say fragment setNote");
            fragment.setNote(adapter.getNoteByPosition(position), user, token);

        }
    }
}