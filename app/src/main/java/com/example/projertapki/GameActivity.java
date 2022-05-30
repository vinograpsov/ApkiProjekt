package com.example.projertapki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private Button answer1, answer2, answer3 , answer4;
    private FirebaseAuth auth;
//    private TextView lifes, timer;
//    private int lifesInt = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        lifes = findViewById(R.id.lifes);
//        timer = findViewById(R.id.timer);

//        lifes.setText(lifesInt);
        Button[] buttons = new Button[4];
        buttons[0] = answer1;
        buttons[1] = answer2;
        buttons[2] = answer3;
        buttons[3] = answer4;
        randomPos(buttons);


   }


   private List<Integer> randomButtons(){
        List<Integer> randoms = new ArrayList<>();
        while (randoms.size()!= 4){
            int randomNum = 1 + (int)(Math.random() * ((5 - 1) + 1));
            if (!randoms.contains(randomNum)){
                randoms.add(randomNum);
            }
        }
        return randoms;
   }

   private void randomPos(Button[] buttons){
       List<Integer> randoms = randomButtons();
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