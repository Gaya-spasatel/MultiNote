package com.peregudova.multinote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.peregudova.multinote.requests.LoginAnswer;

public class MainActivity extends AppCompatActivity {
    private Button enter;
    private Button register;
    private EditText login;
    private EditText password;
    LoginViewModel viewModel;

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
                String log = login.getText().toString();
                String pas = password.getText().toString();
                boolean answer = viewModel.check(log, pas);
                if(answer){
                    viewModel.logIn(log, pas);
                }else{
                    Toast.makeText(getApplicationContext(), "Error in login or password", Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(getApplicationContext(), loginAnswer.getConnection()+loginAnswer.getToken(), Toast.LENGTH_LONG).show();
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