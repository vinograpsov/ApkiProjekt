package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private Button answer1, answer2, answer3 , answer4;
    private FirebaseAuth auth;
    List<Button> buttons = new ArrayList<>();
//    private TextView lifes, timer;
//    private int lifesInt = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        lifes = findViewById(R.id.lifes);
//        timer = findViewById(R.id.timer);

//        lifes.setText(lifesInt);
//        Button[] buttons = new Button[4];
//        buttons[0] = answer1;
//        buttons[1] = answer2;
//        buttons[2] = answer3;
//        buttons[3] = answer4;


        answer1 = findViewById(R.id.button1);
        answer1.setText("1");

        answer2 = findViewById(R.id.button2);
        answer2.setText("2");

        answer3 = findViewById(R.id.button3);
        answer3.setText("3");

        answer4 = findViewById(R.id.button4);
        answer4.setText("4");



        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomPos2(buttons);
            }
        });

//        randomPos1(buttons);
//        timer.setText(answer3.getText());


   }




   private List<Integer> randomButtonsNum(){
        List<Integer> randoms = new ArrayList<>();
        while (randoms.size()!= 4){
            int randomNum = (int)(Math.random() * 4);
            if (!randoms.contains(randomNum)){
                randoms.add(randomNum);
            }
        }
        return randoms;
   }

    public void randomPos2(List<Button> buttons){
        Collections.shuffle(buttons);
    }



    public void randomPos1(Button[] buttons){
       List<Integer> randoms = randomButtonsNum();
       buttons[randoms.get(0)] = findViewById(R.id.button1);
       buttons[randoms.get(0)].setText("1");
       buttons[randoms.get(0)].setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               randomPos1(buttons);
               System.out.println("click");
               System.out.println(randoms);
           }
       });
       buttons[randoms.get(1)] = findViewById(R.id.button2);
       buttons[randoms.get(1)].setText("2");

       buttons[randoms.get(2)] = findViewById(R.id.button3);
       buttons[randoms.get(2)].setText("3");

       buttons[randoms.get(3)] = findViewById(R.id.button4);
       buttons[randoms.get(3)].setText("4");
   }


   private void randomPos(Button[] buttons){
       List<Integer> randoms = randomButtonsNum();
       for (int i  = 0 ; i < randoms.size(); i++){
           int temp = randoms.get(i);
           switch (temp){
               case 1:
                   buttons[i] = findViewById(R.id.button1);
                   buttons[i].setText("1");
                   break;
               case 2:
                   buttons[i] = findViewById(R.id.button2);
                   buttons[i].setText("2");
                   break;
               case 3:
                   buttons[i] = findViewById(R.id.button3);
                   buttons[i].setText("3");
                   break;
               case 4:
                   buttons[i] = findViewById(R.id.button4);
                   buttons[i].setText("4");
                   break;
           }
       }
    }
//    answer1 = findViewById(R.id.button1);
//    answer2 = findViewById(R.id.button2);
//    answer3 = findViewById(R.id.button3);
//    answer4 = findViewById(R.id.button4);
}