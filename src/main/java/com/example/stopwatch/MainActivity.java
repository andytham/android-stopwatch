package com.example.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartButton(View view){
        // get operation
        // get current time
        TextView timer = findViewById(R.id.timer);
        String currentTimeString = timer.getText().toString();
        long currentTime = Long.parseLong(currentTimeString);

        long startTime =  System.nanoTime();
        //begin counting
        while(true){
            currentTime = currentTime + (System.nanoTime() - startTime);
            timer.setText(Long.toString(currentTime));
            startTime =  System.nanoTime();
        }

    }
}
