package com.example.chekers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signIn extends AppCompatActivity {
    public EditText personName;

    public EditText signInEmail;
    public  EditText signInPassword;
    public EditText confirmPassword;
    public Button signIn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        personName = findViewById(R.id.personName);
        signInEmail = findViewById(R.id.signInEmail);
        signInPassword = findViewById(R.id.signInPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(signIn.this, playGame.class);
                startActivity(in);
            }
        });
    }
}