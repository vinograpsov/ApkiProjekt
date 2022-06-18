package com.example.projertapki;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.concurrent.TimeUnit;

public class GameFragment extends Fragment {
    MainActivity activity = (MainActivity) getActivity();

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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game,container,false);

        timerView = view.findViewById(R.id.timer);
        lifesTextView  = view.findViewById(R.id.lifes);
        pointsView = view.findViewById(R.id.points);

        mul = view.findViewById(R.id.mult);

        question = view.findViewById(R.id.question);

        answer1 = view.findViewById(R.id.button1);
        answer2 = view.findViewById(R.id.button2);
        answer3 = view.findViewById(R.id.button3);
        answer4 = view.findViewById(R.id.button4);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shuffleButtons(answer1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shuffleButtons(answer2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shuffleButtons(answer3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    shuffleButtons(answer4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
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
                findAndSetRight();
                new CountDownTimer(1000,1000) {

                    @Override
                    public void onTick(long arg0) {


                    }

                    @Override
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
                        resetColors();
                        if(lifes == 0){
                            onEndOfGame();
                        }
                    }
                }.start();
            }
        }.start();

        return view;
    }

    /*
    @Override
    public void onBackPressed()
    {
        FirebaseAuth.getInstance().signOut();
        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new StartFragment(),null).addToBackStack(null).commit();
        myTimer.cancel();

    }
*/
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

    public void shuffleButtons(Button answer) throws InterruptedException {
        Button currentButton = answer;
        if(currentButton.getText().equals("" + right_answer)){
            myTimer.cancel();

            currentButton.setBackgroundResource(R.drawable.right_answer);
            new CountDownTimer(1000,1000) {

                @Override
                public void onTick(long arg0) {


                }

                @Override
                public void onFinish() {
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

                    resetColors();
                    if(lifes == 0){
                        onEndOfGame();
                    }
                }
            }.start();

        }
        else{
            myTimer.cancel();

            currentButton.setBackgroundResource(R.drawable.wrong_answer);
            findAndSetRight();


            new CountDownTimer(1000,1000) {

                @Override
                public void onTick(long arg0) {


                }

                @Override
                public void onFinish() {
                    myTimer.start();
                    level = 1;
                    newQuestion();
                    setAnswersOnButtons(answer1,answer2,answer3,answer4,answers);
                    current_strick = 0;
                    point_mult = 1;
                    mul.setText("x" +point_mult);
                    lifes -= 1;

                    lifesTextView.setText(Integer.toString(lifes));

                    resetColors();

                    if(lifes == 0){
                        onEndOfGame();
                    }
                }
            }.start();


        }

    }

    private void onEndOfGame(){
        myTimer.cancel();
        firebaseLeaderboard();

        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new LeaderboardFragment(),null).addToBackStack(null).commit();

    }

    private void firebaseLeaderboard(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef= firebaseDatabase.getReference().child("Leaderboard").child(username);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LeaderboardUser info = dataSnapshot.getValue(LeaderboardUser.class);
                if(info.getPoints() < points){
                    info.setPoints((long) points);
                    databaseRef.setValue(info);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("doc", String.valueOf(points));
            }
        });
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


    private void resetColors(){
        answer1.setBackgroundResource(R.drawable.custom_answer_buttons);
        answer2.setBackgroundResource(R.drawable.custom_answer_buttons);
        answer3.setBackgroundResource(R.drawable.custom_answer_buttons);
        answer4.setBackgroundResource(R.drawable.custom_answer_buttons);
    }

    private void findAndSetRight(){
        if (answer1.getText().equals("" + right_answer)){
            answer1.setBackgroundResource(R.drawable.right_answer);
        }
        else if (answer2.getText().equals("" + right_answer)){
            answer2.setBackgroundResource(R.drawable.right_answer);
        }
        else if (answer3.getText().equals("" + right_answer)){
            answer3.setBackgroundResource(R.drawable.right_answer);
        }
        else if (answer4.getText().equals("" + right_answer)){
            answer4.setBackgroundResource(R.drawable.right_answer);
        }
    }

}