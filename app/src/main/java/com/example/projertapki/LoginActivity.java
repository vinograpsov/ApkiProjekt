package com.example.projertapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private TextView warning;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLog);
        password = findViewById(R.id.passwordLog);
        login = findViewById(R.id.singUpAcc);
        warning = findViewById(R.id.textView10);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(LoginActivity.this, "Empty email of password pole", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Password to short", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txtEmail, txtPassword);
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUser(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    warning.setText("Succsessful");
                    warning.setTextColor(Color.GREEN);
                    startActivity(new Intent(LoginActivity.this,GameActivity.class));
                    finish();
                }
                else{
                    warning.setText("FAILED");
                    warning.setTextColor(Color.RED);
                }
            }
        });
    }



}