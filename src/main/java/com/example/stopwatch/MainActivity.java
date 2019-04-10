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
    private String stringMilliseconds = "000";
    private String stringSeconds = "00";
    private String stringMinutes = "00";
    private String stringHours = "00";
    private String timeString;
    private TextView display;
    private TextView startButton;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.timeDisplay);
        startButton = findViewById(R.id.startButton);

        startButton.setText("START");
    }

    public void onStartButton(View view){
        // get operation
        // get current time
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        currentTime += 1;
                        milliseconds = currentTime % 1000;
                        seconds = (currentTime / 1000) % 60;
                        minutes = currentTime / 1000 / 60;
                        hours = currentTime / 1000 / 360;

                        stringMilliseconds = Integer.toString(milliseconds);
                        if (milliseconds < 100) {
                            if (milliseconds < 10) {
                                stringMilliseconds = "0" + stringMilliseconds;
                            }
                            stringMilliseconds = "0" + stringMilliseconds;
                        }
                        stringSeconds = Integer.toString(seconds);
                        if (seconds < 10) {
                            stringSeconds = "0" + Integer.toString(seconds);
                        }
                        stringMinutes = Integer.toString(minutes);
                        if (minutes < 10) {
                            stringMinutes = "0" + stringMinutes;
                        }
                        stringHours = Integer.toString(hours);
                        if (hours < 10) {
                            stringHours = "0" + stringHours;
                        }


                        timeString = stringHours + ":" + stringMinutes + ":" + stringSeconds + ":" + stringMilliseconds;
                        display.setText(timeString);
                    }
                });
            }
        };
        String buttonText = startButton.getText().toString();
        if (buttonText == "START") {
            timer = new Timer();
            startButton.setText("PAUSE");
            timer.scheduleAtFixedRate(timerTask, 0, 1); // every millisecond
        } else {
            startButton.setText("START");
//            timer.cancel();
            onStopTimer();
        }
    }
    public void onStopTimer(){
        timer.cancel();
    }

    public void onResetButton(View view){
        timer.cancel();
        currentTime = 0;
    }
}
