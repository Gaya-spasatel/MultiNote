package com.peregudova.multinote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.peregudova.multinote.requests.LoginAnswer;

public class LoginActivity extends AppCompatActivity {
    private Button enter;
    private Button register;
    private EditText login;
    private EditText password;
    LoginViewModel viewModel;
    private String log;
    private String pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = (Button) findViewById(R.id.button);
        register = (Button)findViewById(R.id.button2);
        login = (EditText)findViewById(R.id.editTextTextPersonName);
        password=(EditText)findViewById(R.id.editTextTextPassword);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //запуск проверки и после ее прохождения отправка данных на авторизацию

                Log.d("Click", "click");
                log = login.getText().toString();
                pas = password.getText().toString();
                boolean answer = viewModel.check(log, pas);
                if(answer){
                    viewModel.logIn(log, pas);
                }else{
                    Toast.makeText(getApplicationContext(), "Error in login or password", Toast.LENGTH_SHORT).show();
                }
                enter.setClickable(false);
            }
        });
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getProgressState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });
        viewModel.getAnswer().observe(this, new Observer<LoginAnswer>() {
            @Override
            public void onChanged(LoginAnswer loginAnswer) {

                enter.setClickable(true);
                if(loginAnswer.getConnection().equals("true") && loginAnswer.getAnswer().equals("Authorized") && !loginAnswer.getToken().equals("-")){
                    Intent intent = new Intent(LoginActivity.this, AppActivity.class);
                    intent.putExtra("user", log);
                    intent.putExtra("token", loginAnswer.getToken());
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "Authorization Answer:"+loginAnswer.getAnswer(), Toast.LENGTH_LONG).show();
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