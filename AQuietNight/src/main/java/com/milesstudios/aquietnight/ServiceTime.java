package com.milesstudios.aquietnight;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Ryan on 9/13/2014.
 */
public class ServiceTime extends Service {
    int stone_timer, s;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}