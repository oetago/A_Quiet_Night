package com.milesstudios.aquietnight;

/**
 * Forest
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Forest extends ActivityGroup  {
    //Declare Statements
    int wood_counter, leaves_counter, stone_counter, w,l,s, stone_axeb, stone_pickb, hard_wood_counter;
    Button wood, stone, leaves, cave_button;
    TextView log, storage;
    ProgressBar wood_bar, leaves_bar, stone_bar;
    CountDownTimer wood_timer, leaves_timer, stone_timer;


    //For setting up back button
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
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data",Context.MODE_PRIVATE);
        //Acitivates Action Bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Decalres xml layout and id's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest);

        //Declare Textviews
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);

        //Declare Buttons
        wood = (Button) findViewById(R.id.wood);
        cave_button = (Button) findViewById(R.id.cave_button);
        stone = (Button) findViewById(R.id.stone);
        leaves = (Button) findViewById(R.id.leaves);

        //Declare and hide progress bars
        wood_bar = (ProgressBar)findViewById(R.id.wood_bar);
        wood_bar.setVisibility(View.INVISIBLE);
        leaves_bar = (ProgressBar)findViewById(R.id.leaves_bar);
        leaves_bar.setVisibility(View.INVISIBLE);
        stone_bar = (ProgressBar)findViewById(R.id.stone_bar);
        stone_bar.setVisibility(View.INVISIBLE);

        //Setup TextSize and display Storage
        log.setTextSize(12);
        storage.setTextSize(15);
        UpdateText();

        //Gets crafting and buildings
       final int stone_axeb = sharedPref.getInt("stone_axe", 0);
       final int stone_pickb = sharedPref.getInt("wood", 0);
       final int wood_counter = sharedPref.getInt("wood", 0);
       final int leaves_counter = sharedPref.getInt("leaves", 0);
       final int stone_counter = sharedPref.getInt("stone", 0);
       final int hard_wood_counter = sharedPref.getInt("hard_wood", 0);


        //"Tab Button"
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
                if(stone_axeb == 0) {
                    int wood_counter = sharedPref.getInt("wood", 0);
                    log.append("\n You gathered 1 wood from the ground");
                    wood_counter += 1;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("wood", wood_counter);
                    editor.apply();
                    UpdateText();

                    wood_bar.setVisibility(View.VISIBLE);
                    wood.setEnabled(false);
                    wood_timer = new CountDownTimer(15000, 150) {

                        @Override
                        public void onTick(long millisUntilFinishedw) {
                            Log.w("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedw);
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
                }else{
                    int wood_counter = sharedPref.getInt("wood", 0);
                    log.append("\n You gathered 2 wood from the ground");
                    wood_counter += 2;

                    //Save counter
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("wood", wood_counter);
                    editor.apply();
                    UpdateText();

                    wood_bar.setVisibility(View.VISIBLE);
                    wood.setEnabled(false);
                    wood_timer = new CountDownTimer(13000, 130) {

                        @Override
                        public void onTick(long millisUntilFinishedw) {
                            Log.w("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedw);
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


            }



        });

        leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leaves_counter = sharedPref.getInt("leaves", 0);
                log.append("\n You gathered 1 leaf from the ground");
                leaves_counter += 1;
                leaves_bar.setVisibility(View.VISIBLE);
                leaves.setEnabled(false);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("leaves", leaves_counter);
                editor.apply();
                UpdateText();
                leaves_timer=new CountDownTimer(9000,90) {


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
              if(stone_pickb == 0) {
                  int stone_counter = sharedPref.getInt("stone", 0);
                  log.append("\n You gathered 1 stone from the ground");
                  stone_counter += 1;

                  //Save counter
                  SharedPreferences.Editor editor = sharedPref.edit();
                  editor.putInt("stone", stone_counter);
                  editor.apply();
                  stone_bar.setVisibility(View.VISIBLE);
                  stone.setEnabled(false);
                  UpdateText();

                  stone_timer = new CountDownTimer(22000, 220) {

                      @Override
                      public void onTick(long millisUntilFinisheds) {
                          Log.v("Log_tag", "Tick of Progress" + s + " " + millisUntilFinisheds);
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

              }else{
                  int stone_counter = sharedPref.getInt("stone", 0);
                  log.append("\n You gathered 2 stone from the ground");
                  stone_counter += 2;

                  //Save counter
                  SharedPreferences.Editor editor = sharedPref.edit();
                  editor.putInt("stone", stone_counter);
                  editor.apply();
                  stone_bar.setVisibility(View.VISIBLE);
                  stone.setEnabled(false);
                  UpdateText();

                  stone_timer = new CountDownTimer(18000, 180) {

                      @Override
                      public void onTick(long millisUntilFinisheds) {
                          Log.v("Log_tag", "Tick of Progress" + s + " " + millisUntilFinisheds);
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
            }

            ;

        });

    }
    public void UpdateText(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data",Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter + "\n Hard Wood:" + hard_wood_counter);

    }
    @Override
    public void onBackPressed() {

    }

}
