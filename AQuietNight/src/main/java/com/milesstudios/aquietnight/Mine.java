package com.milesstudios.aquietnight;

/**
 * Forest
 * Created by Ryanm14 on 10/28/2014.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.milesstudios.aquietnight.util.Helper;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;


public class Mine extends FragmentActivity {

    //Declare Statements
    long copper_update, wood_remaining, sys_time, wood_remaining2;
    Button copper, coal;
    int w, s;
    TextView log, storage;
    ProgressBar copper_bar, coal_bar;
    CountDownTimer copper_timer, coal_timer;


    //For setting up back button
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Mine.this, Cave.class);
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

        //Acitivates Action Bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Decalres xml layout and id's and saving
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);


        //Declare Textviews
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);


        copper = (Button) findViewById(R.id.copper);
        coal = (Button) findViewById(R.id.coal);

        //Declare and hide progress bars
        copper_bar = (ProgressBar) findViewById(R.id.wood_bar);
        coal_bar = (ProgressBar) findViewById(R.id.leaves_bar);

        //Setup TextSize and display Storage
        storage.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(12);
        storage.setTextSize(15);

        //TODO UNDERLINELING
        final TextView cave_tab_wmine = (TextView) findViewById(R.id.cave_tab_wmine);
        final TextView forest_tab_wmine = (TextView) findViewById(R.id.forest_tab_wmine);
        final TextView mine_tab = (TextView) findViewById(R.id.mine_tab);

        mine_tab.setPaintFlags(mine_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        //Gets crafting and buildings
        final int stone_axeb = sharedPref.getInt("stone_axe", 0);
        final int stone_pickb = sharedPref.getInt("stone_pick", 0);
        final int wood_counter = sharedPref.getInt("wood", 0);
        final int leaves_counter = sharedPref.getInt("leaves", 0);
        final int stone_counter = sharedPref.getInt("stone", 0);
        final int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        final int stone_sword_b = sharedPref.getInt("stone_sword", 0);
        final int leaf_canteen_b = sharedPref.getInt("leaf_canteen", 0);
        final int apple_counter = sharedPref.getInt("apples", 0);
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);


        //"Tab Button"
        cave_tab_wmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCave = new Intent(Mine.this, Cave.class);
                startActivity(openCave);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });
        forest_tab_wmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCave = new Intent(Mine.this, Forest.class);
                startActivity(openCave);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });


    }


    @Override
    public void onPause() {
        if (w > 0) {
            copper_timer.cancel();
        }
        if (s > 0) {
            coal_timer.cancel();
        }

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Mine.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        updateBars();
        super.onStart();
    }

    //Update Bars
     private Handler counterHandler = new Handler();
    Helper helper = new Helper(this);
    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 250);
    }

    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    public void updateBars() {
        updateCopper();
        updateCoal();
    }

    public void updateCoal() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long coal_systemtime = sharedPref.getLong("coal_systemtime", 0);
        long coal_update = coal_systemtime + 13000;
        if (coal_update >= System.currentTimeMillis()) {
            coal.setEnabled(false);
            coal_bar.setVisibility(View.VISIBLE);
            long coal_remaining = coal_update - System.currentTimeMillis();
            s = (((int) coal_remaining * -1 + 13000) / 130) - 1;
            long coal_remaining2 = coal_remaining / (100 - s);
            coal_timer = new CountDownTimer(coal_remaining, coal_remaining2) {

                @Override
                public void onTick(long millisUntilFinishedl) {
                    s++;
                    coal_bar.setProgress(s);
                }

                @Override
                public void onFinish() {
                    coal.setEnabled(true);
                    coal_bar.setVisibility(View.INVISIBLE);
                    s = 0;
                    coal_bar.setProgress(s);
                }
            };
            coal_timer.start();

        } else {
            coal.setEnabled(true);
            coal_bar.setVisibility(View.INVISIBLE);

        }
    }

    public void updateCopper() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long copper_systemtime = sharedPref.getLong("copper_systemtime", 0);
        long copper_update = copper_systemtime + 18000;
        if (copper_update >= System.currentTimeMillis()) {
            copper.setEnabled(false);
            copper_bar.setVisibility(View.VISIBLE);
            long copper_remaining = copper_update - System.currentTimeMillis();
            w = (((int) copper_remaining * -1 + 18000) / 180) - 1;
            long copper_remaining2 = copper_remaining / (100 - w);
            copper_timer = new CountDownTimer(copper_remaining, copper_remaining2) {

                @Override
                public void onTick(long millisUntilFinishedd) {
                    w++;
                    copper_bar.setProgress(w);

                }

                @Override
                public void onFinish() {
                    copper.setEnabled(true);
                    copper_bar.setVisibility(View.INVISIBLE);
                    w = 0;
                    copper_bar.setProgress(s);

                }
            };
            copper_timer.start();

        } else {
            copper.setEnabled(true);
            copper_bar.setVisibility(View.INVISIBLE);

        }
    }

    //Buttons
    public void buttonCopper(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int copper_counter = sharedPref.getInt("copper", 0);
        log.append("\n You mined 1 piece of copper");
        // StartAnimations();
        copper_counter += 1;
        copper_bar.setVisibility(View.VISIBLE);
        copper.setEnabled(false);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("copper", copper_counter);
        editor.putLong("copper_systemtime", System.currentTimeMillis());
        editor.apply();

        copper_timer = new CountDownTimer(18000, 180) {


            @Override
            public void onTick(long millisUntilFinishedl) {
                //.v("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedl);
                w++;
                copper_bar.setProgress(w);
            }

            @Override
            public void onFinish() {
                copper.setEnabled(true);
                copper_bar.setVisibility(View.INVISIBLE);
                w = 0;
                copper_bar.setProgress(w);

            }
        };
        copper_timer.start();


    }

    public void buttonCoal(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int coal_counter = sharedPref.getInt("coal", 0);
        log.append("\n You mined 1 piece of Coal");
        coal_counter += 1;
        coal_bar.setVisibility(View.VISIBLE);
        coal.setEnabled(false);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("coal", coal_counter);
        editor.apply();
        editor.putLong("coal_systemtime", System.currentTimeMillis());
        editor.apply();

        coal_timer = new CountDownTimer(13000, 130) {


            @Override
            public void onTick(long millisUntilFinishedu) {

                s++;
                coal_bar.setProgress(s);
            }

            @Override
            public void onFinish() {
                coal.setEnabled(true);
                coal_bar.setVisibility(View.INVISIBLE);

                //Do what you want
                s = 0;
                coal_bar.setProgress(s);

            }
        };
        coal_timer.start();

    }
}




