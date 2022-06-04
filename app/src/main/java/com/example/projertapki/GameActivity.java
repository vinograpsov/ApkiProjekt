package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity{
    private Button answer1, answer2, answer3 , answer4;
    ArrayList<String> answers = new ArrayList<>();
    TextView lifesTextView,timerView,pointsView;
    private CountDownTimer myTimer;

    private int lifes,points,rightAnswer;


    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timerView = findViewById(R.id.timer);
        lifesTextView  = findViewById(R.id.lifes);
        pointsView = findViewById(R.id.points);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);


        points = 0;
        lifes = 3;
        rightAnswer = 3;

        pointsView.setText(Integer.toString(points));
        lifesTextView.setText(Integer.toString(lifes));


        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("4");

        setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);



        myTimer =  new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerView.setText("" + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
            }
            public void onFinish() {
                lifes -= 1;
                myTimer.cancel();
                myTimer.start();
                lifesTextView.setText(Integer.toString(lifes));
                if(lifes <= 0){
                    timerView.setText(":(");
                }
            }
        }.start();


        System.out.println("3" == Integer.toString(rightAnswer));


    }

    private ArrayList<String> randomButtonPos(ArrayList<String> buttonNums){
        Collections.shuffle(buttonNums);
        return buttonNums;
    }

    private void setAnswersOnButtons(Button answer1, Button answer2, Button answer3, Button answer4, ArrayList<String> answers){
        randomButtonPos(answers);
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
    }

    public void shuffleButtons1(View view){
//        if(answer1.getText() == Integer.toString(rightAnswer)){
        if(answer1.getText() == "" + rightAnswer){
            points += 1;
            myTimer.cancel();
            myTimer.start();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            pointsView.setText(Integer.toString(points));
        }
        else{
            myTimer.cancel();
            myTimer.start();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons2(View view){
        if(answer2.getText() == "" + rightAnswer){
            myTimer.cancel();
            myTimer.start();
            points +=1;
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            pointsView.setText(Integer.toString(points));
        }
        else{
            myTimer.cancel();
            myTimer.start();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons3(View view){
        if(answer3.getText() == "" + rightAnswer){
            myTimer.cancel();
            myTimer.start();
            points +=1;
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            pointsView.setText(Integer.toString(points));
        }
        else{
            myTimer.cancel();
            myTimer.start();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }

    public void shuffleButtons4(View view){
        if(answer4.getText() == "" + rightAnswer){
            myTimer.cancel();
            myTimer.start();
            points +=1;
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            pointsView.setText(Integer.toString(points));
        }
        else{
            myTimer.cancel();
            myTimer.start();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
    }
}