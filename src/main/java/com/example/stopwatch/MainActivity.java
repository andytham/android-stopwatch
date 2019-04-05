package com.example.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int currentTime = 0;
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
                        display.setText(Integer.toString(currentTime));
                    }
                });
            }
        }, 0, 1); // every millisecond
    }
}
