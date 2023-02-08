package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listViewScores;

    //a list to store all the artist from firebase database
    List<Score> scores;
    ArrayList<HashMap<String, String>> scoresList;

    //our database reference object
    DatabaseReference databaseScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history);

        AppCompatButton btn_return = (AppCompatButton)findViewById(R.id.btn_return);
        listViewScores = (ListView) findViewById(R.id.listViewAScores);

        databaseScores = FirebaseDatabase.getInstance().getReference("scores");


        scores = new ArrayList<>();
        scoresList = new ArrayList<HashMap<String, String>>();



        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseScores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scores.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Score scr = postSnapshot.getValue(Score.class);
                    scores.add(scr);
                }
                //creating adapter
                ScoreList scoreAdapter = new ScoreList(HistoryActivity.this, scores);
                //attaching adapter to the listview
                listViewScores.setAdapter(scoreAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}