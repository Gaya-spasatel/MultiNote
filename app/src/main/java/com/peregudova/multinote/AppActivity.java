package com.peregudova.multinote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {
    private TextView textView;
    AllNotesViewModel allNotesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        textView = findViewById(R.id.textView2);
        String user = "Error";
        String token = "-";
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