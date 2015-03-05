package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.milesstudios.aquietnight.reference.SharedPref;
import com.milesstudios.aquietnight.util.Helper;

public class splash extends ActionBarActivity {

    MediaPlayer SplashSong;

    @Override
    protected void onCreate(Bundle MilesStudios) {
        // TODO Auto-generated method stub
        super.onCreate(MilesStudios);
        setContentView(R.layout.splash2);
        SplashSong = MediaPlayer.create(splash.this, R.raw.splash_sound);
        SplashSong.start();
        Helper helper = new Helper(this);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getBoolean(SharedPref.VILLAGE, false)) {
            helper.updateWorkers();
        }
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
                    Boolean intro = sharedPref.getBoolean("intro", true);
                    if (intro) {
                        Intent openMain_Screen = new Intent(splash.this, Intro.class);
                        startActivity(openMain_Screen);
                    } else {
                        Intent openMain_Screen = new Intent(splash.this, Cave.class);
                        startActivity(openMain_Screen);
                    }
                    finish();
                }
            }
        };
        timer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        SplashSong.release();
        finish();
    }


}