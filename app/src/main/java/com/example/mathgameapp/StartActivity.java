package com.example.mathgameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    public static String TAG_OPERATION = "operation";
    AppCompatButton btn_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);

        ImageView imgAdd, imgSubtract, imgMultiply, imgDivide;
        //AppCompatButton btn_history;

        imgAdd = (ImageView)findViewById(R.id.imgAdd);
        imgSubtract = (ImageView)findViewById(R.id.imgSubtract);
        imgMultiply = (ImageView)findViewById(R.id.imgMultiply);
        imgDivide = (ImageView)findViewById(R.id.imgDivide);
        btn_history = (AppCompatButton)findViewById(R.id.btn_history);

        btn_history.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent in = new Intent(getApplicationContext(), HistoryActivity.class);
                                          startActivityForResult(in, 100);
                                      }
                                  }
        );
        imgAdd.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent in = new Intent(getApplicationContext(), GameActivity.class);
                                           in.putExtra(TAG_OPERATION, "1");
                                           startActivityForResult(in, 100);
                                       }
                                   }
        );
        imgSubtract.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent in = new Intent(getApplicationContext(), GameActivity.class);
                                          in.putExtra(TAG_OPERATION, "2");
                                          startActivityForResult(in, 100);
                                      }
                                  }
        );
        imgMultiply.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent in = new Intent(getApplicationContext(), GameActivity.class);
                                               in.putExtra(TAG_OPERATION, "3");
                                               startActivityForResult(in, 100);
                                           }
                                       }
        );
        imgDivide.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent in = new Intent(getApplicationContext(), GameActivity.class);
                                               in.putExtra(TAG_OPERATION, "4");
                                               startActivityForResult(in, 100);
                                           }
                                       }
        );

    }
}