package com.example.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int currentTime = 0;
//    private int milliseconds = 0;
//    private int seconds = 0;
//    private int minutes = 0;
//    private int hours = 0;
//    private String stringMilliseconds = "000";
//    private String stringSeconds = "00";
//    private String stringMinutes = "00";
//    private String stringHours = "00";
    private String timeString;
    private TextView display;
    private TextView startButton;
    private Timer timer;
    private boolean isStopped = false;
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
                        timeString = formatTime(currentTime);
                        display.setText(timeString);
                        if (isStopped){
                            timer.cancel();
                        }
                    }
                });
            }
        };
        String buttonText = startButton.getText().toString();
        if (buttonText == "START") {
            isStopped = false;
            timer = new Timer();
            startButton.setText("PAUSE");
            timer.scheduleAtFixedRate(timerTask, 0, 1); // every millisecond
        } else {
            isStopped = true;
            startButton.setText("START");
            timer.cancel();
        }
    }
    public void onStopTimer(){
        timer.cancel();
    }
    public String formatTime(int currentTime){
        int milliseconds = currentTime % 1000;
        int seconds = (currentTime / 1000) % 60;
        int minutes = currentTime / 1000 / 60;
        int hours = currentTime / 1000 / 360;

        String stringMilliseconds = Integer.toString(milliseconds);
        if (milliseconds < 100) {
            if (milliseconds < 10) {
                stringMilliseconds = "0" + stringMilliseconds;
            }
            stringMilliseconds = "0" + stringMilliseconds;
        }
        String stringSeconds = Integer.toString(seconds);
        if (seconds < 10) {
            stringSeconds = "0" + Integer.toString(seconds);
        }
        String stringMinutes = Integer.toString(minutes);
        if (minutes < 10) {
            stringMinutes = "0" + stringMinutes;
        }
        String stringHours = Integer.toString(hours);
        if (hours < 10) {
            stringHours = "0" + stringHours;
        }

        return stringHours + ":" + stringMinutes + ":" + stringSeconds + ":" + stringMilliseconds;
    }
    public void onResetButton(View view){
//        timer.cancel();
        display.setText("00:00:00:000");
        isStopped = true;
        currentTime = 0;
        startButton.setText("START");
    }
}
