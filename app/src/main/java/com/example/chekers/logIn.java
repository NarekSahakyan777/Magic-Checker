package com.example.chekers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class logIn extends AppCompatActivity {
    public EditText loginEmail, loginPassword;
    public TextView forgetPassword;
    public Button loginButton;
    private FirebaseAuth auth;
    private TextView signInRedirectText, forgotPasswordText;
    private CheckBox remember;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginEmail = findViewById(R.id.editEmail);
        auth = FirebaseAuth.getInstance();
        loginPassword = findViewById(R.id.editPassword);
        forgetPassword = findViewById(R.id.forgPassword);
        loginButton = findViewById(R.id.Login);
        signInRedirectText = findViewById(R.id.signInRedirect);
        remember = findViewById(R.id.remember);
        forgotPasswordText = findViewById(R.id.forgetpassword);
        SharedPreferences sharedPref = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        boolean rememberMe = sharedPref.getBoolean("rememberMe", false);

        if (rememberMe) {
            String savedEmail = sharedPref.getString("username", "");
            String savedPassword = sharedPref.getString("password", "");

            if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
                auth.signInWithEmailAndPassword(savedEmail, savedPassword)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (auth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(logIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(logIn.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(logIn.this, "Email not verified", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(logIn.this, "Auto Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String pass = loginPassword.getText().toString();

                if (email.isEmpty()) {
                    loginEmail.setError("Email cannot be empty");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.toLowerCase()).matches()) {
                    loginEmail.setError("Please enter a valid email");
                } else {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        if (auth.getCurrentUser().isEmailVerified()) {
                                            Toast.makeText(logIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            if (remember.isChecked()) {
                                                saveCredentials(email, pass);
                                            }
                                            startActivity(new Intent(logIn.this, MainActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(logIn.this, "Email not verified", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    private void saveCredentials(String email, String pass) {
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(logIn.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Password cannot be empty");
                    }
                }
            }
        });

        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPasswordReset();
            }

            private void openPasswordReset() {
                
            }
        });

        signInRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(logIn.this, signIn.class));
            }
        });
    }
}