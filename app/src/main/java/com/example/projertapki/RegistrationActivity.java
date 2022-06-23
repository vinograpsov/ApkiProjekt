package com.example.projertapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email, password;
    private Button register;
    private TextView warning;
    private String username;
    private FirebaseAuth auth;
    private MediaPlayer whatsup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        register = findViewById(R.id.registrationAcc);
        warning = findViewById(R.id.textView6);

        auth = FirebaseAuth.getInstance();

        whatsup = new MediaPlayer().create(getApplicationContext(),R.raw.tmpznxaw7dq);
        whatsup.start();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(RegistrationActivity.this, "Empty email of password field", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Password to short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtEmail, txtPassword);
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegistrationActivity.this, StartActivity.class);
        startActivity(intent);
        whatsup.stop();
        finish();
    }


    private void registerUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    warning.setText("Succsessful");
                    warning.setTextColor(Color.GREEN);

                    username = getNameFromEmail(email);

                    firebaseLeaderboard();
                    startActivity(new Intent(RegistrationActivity.this,GameActivity.class));
                    whatsup.stop();
                    finish();
                }
                else{
                    warning.setText("FAILED");
                    warning.setTextColor(Color.RED);
                }
            }
        });

    }

    private void firebaseLeaderboard(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef= firebaseDatabase.getReference().child("Leaderboard");
        LeaderboardUser user = new LeaderboardUser(username,(long) 0 );
        Map<String, Object> userHashMap = new HashMap<>();
        userHashMap.put(username,user);
        databaseRef.updateChildren(userHashMap);
    }

    private String getNameFromEmail(String email){
        String username = "";
        for(int i = 0; i < email.length();i++){
            if (email.charAt(i) == '@') break;
            username += email.charAt(i);
        }
        return username;
    }

}