package com.example.chekers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class logIn extends AppCompatActivity {
    public EditText email;
    public EditText password;
    public TextView forgetPassword;
    public Button logInNpage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        forgetPassword = findViewById(R.id.forgPassword);
        logInNpage = findViewById(R.id.Login);
        logInNpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(logIn.this, playGame.class);
                startActivity(in);
            }
        });

    }
}