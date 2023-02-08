package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer1, mediaPlayer2;
    public static String TAG_SCORE = "score";
    public static String TAG_OPERATION = "operation";
    String strScore, strOperation="";

    DatabaseReference databaseScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_score);

        Intent in = getIntent();
        strScore = in.getStringExtra(TAG_SCORE);
        strOperation = in.getStringExtra(TAG_OPERATION);

        if(strOperation.equals("1")) strOperation = "Add";
        if(strOperation.equals("2")) strOperation = "Subtract";
        if(strOperation.equals("3")) strOperation = "Multiply";
        if(strOperation.equals("4")) strOperation = "Divide";

        TextView txtScore = findViewById(R.id.txtScore);
        AppCompatButton btnAgain = findViewById(R.id.btn_submit);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.success);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.fail);

        databaseScores = FirebaseDatabase.getInstance().getReference("scores");
        String id = databaseScores.push().getKey();
        //creating an Journal Object
        Score degree = new Score(id, strOperation, strScore);
        //Saving the Journal
        databaseScores.child(id).setValue(degree);

        txtScore.setText("Score " + strScore + "/10");
        int score = Integer.parseInt(strScore);
        if(score > 6){
            mediaPlayer1.start();
        }else{
            mediaPlayer2.start();
        }

        btnAgain.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      mediaPlayer1.stop();
                      mediaPlayer2.stop();
                      Intent in = new Intent(getApplicationContext(), StartActivity.class);
                      startActivityForResult(in, 100);
                  }
              }
        );

    }
}