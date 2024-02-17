package com.example.chekers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class signIn extends AppCompatActivity {
    public TextView GoBack;

    public EditText signInEmail, signInPassword, confirmPassword;
    private Button signInButton;
    private TextView signInRedirectText;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.signIn_email);
        signInRedirectText = findViewById(R.id.signInRedirect);
        GoBack = findViewById(R.id.Goback);
        signInPassword = findViewById(R.id.signIn_password);
        signInButton = findViewById(R.id.signIn_button);
        confirmPassword = findViewById(R.id.signIn_confirmpassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signInEmail.getText().toString().trim();
                String pass = signInPassword.getText().toString().trim();
                String confirmpass = confirmPassword.getText().toString().trim();

                if (user.isEmpty()) {
                    signInEmail.setError("Email can't be empty");
                } else if (pass.isEmpty()) {
                    signInPassword.setError("Password can't be empty");
                } else if (confirmpass.isEmpty()) {
                    confirmPassword.setError("Please confirm the password");
                } else if (!confirmpass.equals(pass)) {
                    confirmPassword.setError("Password must match");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(signIn.this, "SignIn Successful", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(signIn.this, EmailVerify.class);
                                            intent.putExtra("Password", pass);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(signIn.this, "Email Verification Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(signIn.this, "SignIn Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signInRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signIn.this, logIn.class));
            }
        });
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signIn.this, MainActivity.class));
            }
        });

    }
}