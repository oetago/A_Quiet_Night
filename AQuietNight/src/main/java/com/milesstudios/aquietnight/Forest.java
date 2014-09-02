package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Forest extends ActivityGroup {
    int wood_counter, leaves_counter, stone_counter, w,l,s;
    Button wood, stone, leaves, cave_button;
    TextView log, storage;
    ProgressBar wood_bar, leaves_bar, stone_bar;
    CountDownTimer wood_timer, leaves_timer, stone_timer;





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
        stone = (Button) findViewById(R.id.stone);
        leaves = (Button) findViewById(R.id.leaves);
        log.setTextSize(9);
        storage.setTextSize(15);
        final  SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        final SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        final SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        wood_bar = (ProgressBar)findViewById(R.id.wood_bar);
        wood_bar.setVisibility(View.INVISIBLE);
        leaves_bar = (ProgressBar)findViewById(R.id.leaves_bar);
        leaves_bar.setVisibility(View.INVISIBLE);
        stone_bar = (ProgressBar)findViewById(R.id.stone_bar);
        stone_bar.setVisibility(View.INVISIBLE);
        int wood_counter = wood1_counter.getInt("wood", 0);
        int leaves_counter = leaves1_counter.getInt("leaves", 0);
        int stone_counter = stone1_counter.getInt("stone",0);
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);


        View c_b = findViewById(R.id.cave_button);
        c_b.setVisibility(View.VISIBLE);




        //TODO Check other buttons and scaling

        cave_button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cave_button.setBackgroundColor(Color.BLACK);
                cave_button.getBackground().setAlpha(64);
                Intent openmain = new Intent(Forest.this, main.class);
                startActivity(openmain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int wood_counter = wood1_counter.getInt("wood", 0);
                 int leaves_counter = leaves1_counter.getInt("leaves", 0);
                 int stone_counter = stone1_counter.getInt("stone",0);
                 log.append("\n You gathered 1 wood from the ground");
                 wood_counter += 1;
                 storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);

                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();

                wood_bar.setVisibility(View.VISIBLE);
                wood.setEnabled(false);
                wood_timer=new CountDownTimer(15000,150) {

                    @Override
                    public void onTick(long millisUntilFinishedw) {
                        Log.w("Log_tag", "Tick of Progress" + w + " "+millisUntilFinishedw);
                        w++;
                        wood_bar.setProgress(w);

                    }

                    @Override
                    public void onFinish() {
                        wood.setEnabled(true);
                        wood_bar.setVisibility(View.INVISIBLE);

                        //Do what you want
                        w = 0;
                        wood_bar.setProgress(w);

                    }
                };
                wood_timer.start();


            }

            ;

        });

        leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0 );
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone", 0);
                log.append("\n You gathered 1 leaf from the ground");
                leaves_counter += 1;
                storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                leaves_bar.setVisibility(View.VISIBLE);
                leaves.setEnabled(false);

                              //Save counter
                SharedPreferences.Editor editor = leaves1_counter.edit();
                editor.putInt("leaves", leaves_counter);
                editor.apply();
                leaves_timer=new CountDownTimer(9000,90) {
                    //TODO Fix times

                    @Override
                    public void onTick(long millisUntilFinishedl) {
                        Log.v("Log_tag", "Tick of Progress" + l + " "+millisUntilFinishedl);
                        l++;
                        leaves_bar.setProgress(l);

                    }

                    @Override
                    public void onFinish() {
                        leaves.setEnabled(true);
                        leaves_bar.setVisibility(View.INVISIBLE);

                        //Do what you want
                        l = 0;
                        leaves_bar.setProgress(l);

                    }
                };
                leaves_timer.start();


            }

            ;

        });
        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
              int wood_counter = wood1_counter.getInt("wood", 0);
              int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone", 0);
                log.append("\n You gathered 1 stone from the ground");
                stone_counter += 1;
                storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);

                //Save counter
                SharedPreferences.Editor editor = stone1_counter.edit();
                editor.putInt("stone", stone_counter);
                editor.apply();
                stone_bar.setVisibility(View.VISIBLE);
                stone.setEnabled(false);

                stone_timer=new CountDownTimer(22000,220) {
                    //TODO Fix times

                    @Override
                    public void onTick(long millisUntilFinisheds) {
                        Log.v("Log_tag", "Tick of Progress" + s + " "+millisUntilFinisheds);
                        s++;
                        stone_bar.setProgress(s);

                    }

                    @Override
                    public void onFinish() {
                        stone.setEnabled(true);
                        stone_bar.setVisibility(View.INVISIBLE);

                        //Do what you want
                        s = 0;
                        stone_bar.setProgress(s);

                    }
                };
                stone_timer.start();


            }

            ;

        });

    }
}
