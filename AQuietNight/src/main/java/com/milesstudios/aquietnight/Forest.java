package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Forest extends ActivityGroup {
    int wood_counter, leaves_counter;
    Button wood, trap, leaves, cave_button;
    TextView log, storage;





    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Forest.this, main.class);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        wood = (Button) findViewById(R.id.wood);
        cave_button = (Button) findViewById(R.id.cave_button);
        leaves = (Button) findViewById(R.id.leaves);
        log.setTextSize(25);
        storage.setWidth(50);
        log.setText("THIS APP IS FOR TESTING HI!");
        View c_b = findViewById(R.id.cave_button);
        c_b.setVisibility(View.VISIBLE);


        Intent openmain = new Intent(Forest.this, main.class);
;


        //TODO Check other buttons and scaling

        cave_button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent openmain = new Intent(Forest.this, main.class);
                startActivity(openmain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences(null, 0);
                //Saving
                SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", 0);
                int wood_counter = wood1_counter.getInt(null, 0);
                int leaves_counter = leaves1_counter.getInt(null, 0);
                wood_counter += 1;
                storage.setText("Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter);

                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt(null, wood_counter);
                editor.apply();


                // set the color red first.
                wood.setEnabled(false);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        wood.setEnabled(true);
                    }
                }, 6000);

            }

            ;

        });

        leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", 0);
                int wood_counter = wood1_counter.getInt(null, 0);
                SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences(null, 0);
                int leaves_counter = leaves1_counter.getInt(null, 0);
                leaves_counter += 1;
                storage.setText("Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter);

                //Save counter
                SharedPreferences.Editor editor = leaves1_counter.edit();
                editor.putInt(null, leaves_counter);
                editor.apply();
                // set the color red first.
                leaves.setEnabled(false);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        leaves.setEnabled(true);
                    }
                }, 8000);

            }

            ;

        });
    }
}
