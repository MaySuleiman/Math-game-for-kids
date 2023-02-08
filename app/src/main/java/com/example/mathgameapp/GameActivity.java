package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static String TAG_OPERATION = "operation";
    String strOperation;
    MediaPlayer mediaPlayer1, mediaPlayer2;

    ProgressBar timerProgress;
    TextView txtTime;
    Timer t;
    int seconds = 0;

    TextView txtNum, txtQty;
    Button btn1, btn2, btn3, btn4;
    AppCompatButton btn_submit;

    int score = 0;
    public static String TAG_SCORE = "score";
    int totalQty = Questions.add_questions.length;
    int currentQtyIndex = 0;
    String selectedAns ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        strOperation = intent.getStringExtra(TAG_OPERATION);

        timerProgress = findViewById(R.id.progressBar);
        txtTime = findViewById(R.id.txtSecond);
        txtNum = findViewById(R.id.txtNum);
        txtQty = findViewById(R.id.txtQty);
        btn1 = findViewById(R.id.btnOption1);
        btn2 = findViewById(R.id.btnOption2);
        btn3 = findViewById(R.id.btnOption3);
        btn4 = findViewById(R.id.btnOption4);
        btn_submit = findViewById(R.id.btn_submit);

        t = new Timer();
        startTimer();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        txtNum.setText("Total Questions: " + totalQty);
        LoadNewQty();
    }

    public void startTimer(){
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(seconds == 31){
                            t.cancel();
                            Intent in = new Intent(getApplicationContext(), ScoreActivity.class);
                            in.putExtra(TAG_SCORE, score + "");
                            in.putExtra(TAG_OPERATION, strOperation);
                            startActivityForResult(in, 100);
                        }
                        txtTime.setText((30 - seconds) + " sec.");
                        double prog = (seconds/30.0) * 100.0;
                        timerProgress.setProgress((int) prog);
                        seconds = seconds + 1;
                    }
                });

            }
        }, 0, 1000);
    }

    @Override
    public void onClick(View view) {
        btn1.setBackgroundColor(Color.BLUE);
        btn2.setBackgroundColor(Color.BLUE);
        btn3.setBackgroundColor(Color.BLUE);
        btn4.setBackgroundColor(Color.BLUE);

        Button clickedButton = (Button) view;
        if(view.getId() == R.id.btn_submit){
            if(strOperation.equals("1")){
                if(selectedAns.equals(Questions.add_correct[currentQtyIndex])) {
                    score++;
                }
            }else if(strOperation.equals("2")){
                if(selectedAns.equals(Questions.subtract_correct[currentQtyIndex])) {
                    score++;
                }
            }else if(strOperation.equals("3")){
                if(selectedAns.equals(Questions.multiply_correct[currentQtyIndex])) {
                    score++;
                }
            }else if(strOperation.equals("4")){
                if(selectedAns.equals(Questions.divide_correct[currentQtyIndex])) {
                    score++;
                }
            }
            currentQtyIndex++;
            LoadNewQty();
            txtNum.setText("Question " + (currentQtyIndex+1) + "/" + totalQty);
        }else{
            ///// choices buttons
            selectedAns = clickedButton.getText().toString();
            if(strOperation.equals("1")){
                if(selectedAns.equals(Questions.add_correct[currentQtyIndex])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
            }else if(strOperation.equals("2")){
                if(selectedAns.equals(Questions.subtract_correct[currentQtyIndex])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
            }else if(strOperation.equals("3")){
                if(selectedAns.equals(Questions.multiply_correct[currentQtyIndex])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
            }else if(strOperation.equals("4")){
                if(selectedAns.equals(Questions.divide_correct[currentQtyIndex])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
            }

        }
    }

    public void LoadNewQty(){
        if(currentQtyIndex == totalQty){
            Intent in = new Intent(getApplicationContext(), ScoreActivity.class);
            in.putExtra(TAG_SCORE, score + "");
            in.putExtra(TAG_OPERATION, strOperation);
            startActivityForResult(in, 100);
        }
        switch (strOperation){
            case "1":
                txtQty.setText(Questions.add_questions[currentQtyIndex]);
                btn1.setText(Questions.add_answers[currentQtyIndex][0]);
                btn2.setText(Questions.add_answers[currentQtyIndex][1]);
                btn3.setText(Questions.add_answers[currentQtyIndex][2]);
                btn4.setText(Questions.add_answers[currentQtyIndex][3]);
                break;
            case "2":
                txtQty.setText(Questions.subtract_questions[currentQtyIndex]);
                btn1.setText(Questions.subtract_answers[currentQtyIndex][0]);
                btn2.setText(Questions.subtract_answers[currentQtyIndex][1]);
                btn3.setText(Questions.subtract_answers[currentQtyIndex][2]);
                btn4.setText(Questions.subtract_answers[currentQtyIndex][3]);
                break;
            case "3":
                txtQty.setText(Questions.multiply_questions[currentQtyIndex]);
                btn1.setText(Questions.multiply_answers[currentQtyIndex][0]);
                btn2.setText(Questions.multiply_answers[currentQtyIndex][1]);
                btn3.setText(Questions.multiply_answers[currentQtyIndex][2]);
                btn4.setText(Questions.multiply_answers[currentQtyIndex][3]);
                break;
            case "4":
                txtQty.setText(Questions.divide_questions[currentQtyIndex]);
                btn1.setText(Questions.divide_answers[currentQtyIndex][0]);
                btn2.setText(Questions.divide_answers[currentQtyIndex][1]);
                btn3.setText(Questions.divide_answers[currentQtyIndex][2]);
                btn4.setText(Questions.divide_answers[currentQtyIndex][3]);
                break;
        }

    }
}