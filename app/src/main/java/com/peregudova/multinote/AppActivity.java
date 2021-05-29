package com.peregudova.multinote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {
    private TextView textView;

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
    }
}