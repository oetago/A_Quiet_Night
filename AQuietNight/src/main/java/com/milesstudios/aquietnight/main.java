package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class main extends Activity {
    int wood_counter, berries_counter;
    Button wood, berries;
    TextView maindisplay, storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        wood_counter = 0;
        berries_counter = 0;
        maindisplay = (TextView) findViewById(R.id.maindisplay);
        storage = (TextView) findViewById(R.id.storage);
        wood = (Button) findViewById(R.id.wood);
        berries = (Button) findViewById(R.id.berries);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        maindisplay.setTextSize(25);
        storage.setWidth(50);
        maindisplay.setText(" ...W..x.. " +
                "Darkness fills the land " +
                "and you shiver ");
        //TODO Check other buttons and scaling



        wood.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                wood_counter += 1;
                storage.append("Wood:" + wood_counter);
                storage.setLines(20);
                // set the color red first.
                wood.setEnabled(false);
                wood.startAnimation(animAlpha);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        wood.setEnabled(true);
                    }
                }, 6000);

            };

        });

        berries.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                berries_counter += 1;
                storage.append("Berries:" + berries_counter);
                // set the color red first.
                berries.setEnabled(false);
                berries.startAnimation(animAlpha);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        berries.setEnabled(true);
                    }
                }, 8000);

            };

        });
    }
}


