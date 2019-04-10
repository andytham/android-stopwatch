package com.example.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int currentTime = 0;
    private int milliseconds = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private String timeString;
    private TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.timeDisplay);
    }

    public void onStartButton(View view){
        // get operation
        // get current time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
            runOnUiThread(new Runnable(){
                public void run(){
                currentTime += 1;
                milliseconds = currentTime % 1000;
                seconds = (currentTime / 1000) % 60;
                minutes = currentTime / 1000 / 60;
                hours = currentTime / 1000 / 360;
                timeString = Integer.toString(hours) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds) + "." + Integer.toString(milliseconds);
                display.setText(timeString);
                }
            });
            }
        }, 0, 1); // every millisecond
    }

    public void onResetButton(View view){
        currentTime = 0;
    }
}
