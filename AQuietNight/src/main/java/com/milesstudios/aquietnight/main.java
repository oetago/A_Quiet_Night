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
    Button fix_walls, buildings, trading, crafting, quests, wood, trap, leaves;
    TextView log, storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        wood_counter = 0;
        berries_counter = 0;
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        wood = (Button) findViewById(R.id.wood);
        leaves = (Button) findViewById(R.id.leaves);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        log.setTextSize(25);
        storage.setWidth(50);
        log.setText("THIS APP IS FOR TESTING HI!");
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

        leaves.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                berries_counter += 1;
                storage.append("Leaves:" + berries_counter);
                // set the color red first.
                leaves.setEnabled(false);
                leaves.startAnimation(animAlpha);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        leaves.setEnabled(true);
                    }
                }, 8000);

            };

        });
    }
}


