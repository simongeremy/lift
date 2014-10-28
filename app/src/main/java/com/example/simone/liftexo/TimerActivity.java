package com.example.simone.liftexo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by simone on 27/10/14.
 */
public class TimerActivity extends Activity {
    private long lastPause;
    private boolean runningCron;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ImageButton buttonStart = (ImageButton) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("simone.liftexo", "Start Service");
                startService();

            }

        });
        ImageButton buttonStop = (ImageButton) findViewById(R.id.buttonStop);
           buttonStop.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   stopService();
               }
           });

        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
       // chronometer.setFormat("MM:SS");
        Log.i("TimerActivity", "Activity Created");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TimerActivity", "Activity Destroyed");
    }


    private void startService() {
        Toast.makeText(getApplicationContext(), "Starting chron", Toast.LENGTH_SHORT).show();
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        if(!runningCron) {
            if (lastPause == 0) {
                chronometer.setBase(SystemClock.elapsedRealtime());
            } else {
                chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);

            }
            chronometer.start();
            runningCron = true;
        }
        startService(new Intent(this, MyService.class));
    }

    private void stopService() {
        Toast.makeText(getApplicationContext(), "Stop chron", Toast.LENGTH_SHORT).show();
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        if (runningCron) {
            lastPause = SystemClock.elapsedRealtime();
            chronometer.stop();
            runningCron = false;
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime());
            lastPause = 0;
        }
        stopService(new Intent(this, MyService.class));
        
    }

}
