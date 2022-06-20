package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button  register, login;

    private MediaPlayer start_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();

        setContentView(R.layout.activity_start);

        register = findViewById(R.id.registration);
        login = findViewById(R.id.login);

        start_music = new MediaPlayer().create(getApplicationContext(),R.raw.morshu_beatbox);
        start_music.start();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
                startActivity(intent);
                start_music.stop();
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                start_music.stop();
                finish();
            }
        });

    }
}