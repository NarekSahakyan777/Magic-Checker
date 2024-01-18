package com.example.chekers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView magicCkechker;
    public Button signUP;
    public Button logIn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        magicCkechker = findViewById(R.id.textView);
        signUP = findViewById(R.id.SignUp);
        logIn = findViewById(R.id.Login);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, logIn.class);
                startActivity(in);
            }
        });
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, signIn.class);
                startActivity(in);
            }
        });
    }
}