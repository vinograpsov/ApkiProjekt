package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity{
    private Button answer1, answer2, answer3 , answer4;
    private FirebaseAuth auth;
    TextView lifesTextView;
    TextView timerView;
    private int lifes;
    ArrayList<String> answers = new ArrayList<>();
    public int timeCounter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        lifes = 3;
        lifesTextView  = findViewById(R.id.lifes);
        timerView = findViewById(R.id.timer);
        lifesTextView.setText(Integer.toString(lifes));
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);
        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("4");

        setAnsweersOnButtons(answer1,answer2,answer3,answer4,answers);

        startTimer();


    }

    private ArrayList<String> randomButtonPos(ArrayList<String> buttonNums){
        Collections.shuffle(buttonNums);
        return buttonNums;
    }

    private void setAnsweersOnButtons(Button answer1,Button answer2,Button answer3,Button answer4,ArrayList<String> answers){
        randomButtonPos(answers);
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
    }

    public void shuffleButtons1(View view){
        startTimer();
        if(answer1.getText() == "1"){
            setAnsweersOnButtons(answer1,answer2,answer3,answer4,answers);
            System.out.println("clicked 1 ");
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons2(View view){
        startTimer();
        if(answer2.getText() == "1"){
            setAnsweersOnButtons(answer1,answer2,answer3,answer4,answers);
            System.out.println("clicked 2 ");
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons3(View view){
        startTimer();
        if(answer3.getText() == "1"){
            setAnsweersOnButtons(answer1,answer2,answer3,answer4,answers);
            System.out.println("clicked 3 ");
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons4(View view){
        startTimer();
        if(answer4.getText() == "1"){
            setAnsweersOnButtons(answer1,answer2,answer3,answer4,answers);
            System.out.println("clicked 4 ");
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void startTimer(){
        new CountDownTimer(10000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timerView.setText("" + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
            }
            public void onFinish() {
                lifes -= 1;
                if(lifes > 0){
                    startTimer();
                }
                else{
                    timerView.setText(":(");
                }

            }
        }.start();
    }


}