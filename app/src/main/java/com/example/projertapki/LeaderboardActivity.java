package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LeaderboardActivity extends AppCompatActivity {

    private Button restart, singOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        restart = findViewById(R.id.restart);
        singOut = findViewById(R.id.singOut);



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LeaderboardActivity.this,GameActivity.class));
                finish();
            }
        });

        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(LeaderboardActivity.this,StartActivity.class));
                finish();
            }
        });

    }
}