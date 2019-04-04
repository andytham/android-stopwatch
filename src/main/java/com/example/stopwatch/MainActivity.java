package com.example.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.apache.commons.lang3.time.StopWatch;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartButton(View view){
        // get operation
        // get current time


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread t = new Thread(){
            @Override
            public void run(){
                Boolean paused = false;
                try {
                    while(!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView timer = findViewById(R.id.timer);
                                String currentTimeString = timer.getText().toString();
                                long currentTime = Long.parseLong(currentTimeString);
                                long time = System.nanoTime();
                                timer.setText(Long.toString(time));

                            }
                        });

                    }
                } catch (Exception e){

                }
            }
        };
        t.start();
    }
}
