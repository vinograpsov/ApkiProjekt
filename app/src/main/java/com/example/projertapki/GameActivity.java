package com.example.projertapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity{
    private Button answer1, answer2, answer3 , answer4;

    TextView lifesTextView,timerView,pointsView, mul,question;

    private CountDownTimer myTimer;

    private int lifes,points,current_strick,point_mult = 1;
    private int level = 1;

    private String expression;
    private double right_answer;
    private ArrayList<Double> answers = new ArrayList<>();

    private FirebaseAuth auth;


    String username;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();

        setContentView(R.layout.activity_game);


        timerView = findViewById(R.id.timer);
        lifesTextView  = findViewById(R.id.lifes);
        pointsView = findViewById(R.id.points);

        mul = findViewById(R.id.mult);

        question = findViewById(R.id.question);

        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);


        auth = FirebaseAuth.getInstance();

        points = 0;
        lifes = 3;

        pointsView.setText(Integer.toString(points));
        lifesTextView.setText(Integer.toString(lifes));

        newQuestion();

        setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
        mul.setText("x" +point_mult);
//      get current user name from base to farther work
        FirebaseUser currentUser = auth.getCurrentUser();
        username = getNameFromEmail(currentUser.getEmail());




        myTimer =  new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerView.setText("" + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
            }
            public void onFinish() {
                lifes -= 1;
                level = 1;
                myTimer.cancel();
                myTimer.start();
                point_mult = 1;
                mul.setText("x" +point_mult);
                current_strick = 0;
                newQuestion();
                setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
                lifesTextView.setText(Integer.toString(lifes));
                if(lifes == 0){
                    onEndOfGame();
                }
            }
        }.start();





    }

    @Override
    public void onBackPressed()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(GameActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    private ArrayList<Double> randomButtonPos(ArrayList<Double> buttonNums){
        Collections.shuffle(buttonNums);
        return buttonNums;
    }

    private void setAnswersOnButtons(Button answer1, Button answer2, Button answer3, Button answer4, ArrayList<Double> answers){
        randomButtonPos(answers);
        answer1.setText("" + answers.get(0));
        answer2.setText("" + answers.get(1));
        answer3.setText("" + answers.get(2));
        answer4.setText("" + answers.get(3));
    }

    public void shuffleButtons(View view){
        Button currentButton;
        currentButton = findViewById(view.getId());
        if(currentButton.getText().equals("" + right_answer)){
            myTimer.cancel();
            myTimer.start();

            if (current_strick == 3){
                current_strick = 0;
                point_mult += 1;
                mul.setText("x" +point_mult);
                level += 1;
            }
            points += (1 * point_mult) ;
            current_strick +=1;
            newQuestion();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            pointsView.setText(Integer.toString(points));
        }
        else{
            myTimer.cancel();
            myTimer.start();
            level = 1;
            newQuestion();
            setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
            current_strick = 0;
            point_mult = 1;
            mul.setText("x" +point_mult);
            lifes -= 1;
            lifesTextView.setText(Integer.toString(lifes));
        }
        if(lifes == 0){
            onEndOfGame();
        }
    }

    private void onEndOfGame(){
        firebaseLeaderboard();
        Intent intent = new Intent(GameActivity.this, LeaderboardActivity.class);
        startActivity(intent);
        finish();

    }

    private void firebaseLeaderboard(){
        


    }

    private String getNameFromEmail(String email){
        String username = "";
        for(int i = 0; i < email.length();i++){
            if (email.charAt(i) == '@') break;
            username += email.charAt(i);
        }
        return username;
    }

    private void newQuestion(){
        expression = Expression.getExpression(level);
        right_answer = Expression.solveExpression(expression);
        answers = Expression.valuesToButtons(right_answer);
        question.setText(expression);
    }
}