package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int TIME = 3000;
        new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
              Intent i = new Intent(MainActivity.this, StartActivity.class);
              startActivity(i);
              finish();
              }}
                , TIME);
    }}