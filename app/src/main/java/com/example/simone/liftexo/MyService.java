package com.example.simone.liftexo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by simone on 27/10/14.
 */
public class MyService extends Service{

        private Timer timer;



    @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }
        public void onCreate() {
               super.onCreate();
               Log.i("simone.liftexo", "Service started");
               TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Log.i("simone.liftexo", "Executing Service");
                }
            };
            timer = new Timer();
            timer.schedule(task, 5000);
        }
        public void onDestroy() {
               super.onDestroy();
               timer.cancel();
               timer = null;
               Log.i("simone.liftexo", "Service Stopped");

        }

}
