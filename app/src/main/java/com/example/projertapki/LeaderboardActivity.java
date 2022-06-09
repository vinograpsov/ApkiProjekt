package com.example.projertapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    private Button restart, singOut;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_leaderboard);


        restart = findViewById(R.id.restart);
        singOut = findViewById(R.id.singOut);
        listView = findViewById(R.id.leaderboard);

        readFromDatabase();

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
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(LeaderboardActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }


    public void readFromDatabase() {
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item,list);
        adapter.clear();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Leaderboard");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LeaderboardUser info = snapshot.getValue(LeaderboardUser.class);
                    String txt = info.getName() + " : " + info.getPoints();
                    list.add(txt);
                    Log.d("doc",txt);

                }
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // do nothing
            }
        });
    }

}