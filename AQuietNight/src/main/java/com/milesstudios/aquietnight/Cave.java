package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.milesstudios.aquietnight.quest.Quest_Main;
import com.milesstudios.aquietnight.util.ChangeLog;
import com.milesstudios.aquietnight.util.Helper;

public class Cave extends Activity {
    Button buildings, trading, crafting, quests,village;
    TextView log, storage;
    SlidingMenu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        buildings = (Button) findViewById(R.id.buildings);
        SharedPreferences.Editor editor = sharedPref.edit();
        //TODO Fix version names
        ChangeLog cl = new ChangeLog(this);
        if (cl.firstRun()) {
            cl.getLogDialog().show();
        }
        crafting = (Button) findViewById(R.id.crafting);
        buildings = (Button) findViewById(R.id.buildings);
        quests = (Button) findViewById(R.id.quests);
        village = (Button) findViewById(R.id.village);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        int trading_post_counter = sharedPref.getInt("tradepostcounter", 0);
        if (sharedPref.getBoolean("tradepost", false)) {
            trading_post_counter++;
            editor.putInt("tradepostcounter", trading_post_counter);
            editor.apply();
        }
        int start_counter = sharedPref.getInt("start_counter", 0);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.log);

        final TextView cave_tab = (TextView) findViewById(R.id.cave_tab);
        final TextView forest_tab = (TextView) findViewById(R.id.forest_tab);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(5);
        menu.setFadeDegree(0.0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(900);
        menu.setMenu(R.layout.menu);
        cave_tab.setPaintFlags(cave_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        cave_tab.setTextSize(20);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);
        storage.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(14);
        storage.setTextSize(15);
        editor.putBoolean("intro", false);
        editor.apply();

       /* if (trading_post_counter == 0) {
            trading.setVisibility(View.INVISIBLE);
        } else if (trading_post_counter == 1) {
            anim.reset();
            trading.clearAnimation();
            trading.startAnimation(anim);
        } */
            quests.setVisibility(View.INVISIBLE);

        int workshop_int = sharedPref.getInt("workshop_int", 0);
        if (sharedPref.getBoolean("workshop", false)) {
            workshop_int++;
            editor.putInt("workshop_int", workshop_int);
            editor.apply();
        }
        if (workshop_int == 0) {
            crafting.setVisibility(View.INVISIBLE);
        } else if (workshop_int == 1) {
            anim.reset();
            crafting.clearAnimation();
            crafting.startAnimation(anim);
        }


        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);


        AdView adView = (AdView) this.findViewById(R.id.ad);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("50FD485A980BE577B48D1D26061025A1")
                .tagForChildDirectedTreatment(true)
                .build();

        adView.loadAd(adRequest);

        forest_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForest = new Intent(Cave.this, Forest.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        crafting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrafting = new Intent(Cave.this, Crafting.class);
                startActivity(openCrafting);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });


        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuildings = new Intent(Cave.this, Buildings.class);
                startActivity(openBuildings);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });


        quests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuest = new Intent(Cave.this, Quest_Main.class);
                startActivity(openQuest);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuest = new Intent(Cave.this, Village.class);
                startActivity(openQuest);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }

        });
        Helper helper = new Helper(this);
        helper.updateText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // help action
                Clear_Data();
                return true;
            case R.id.action_data:
                // help action
                AllData();
                return true;
            case R.id.action_share:
                sendEmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Delete Shared Prefernces

    public void Clear_Data() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        Intent openMain = new Intent(Cave.this, splash.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void AllData() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("workshop", 1);
        editor.putInt("furnace", 1);
        editor.putInt("quest_map", 1);
        editor.putInt("wood", 999999);
        editor.putInt("stone", 999999);
        editor.putInt("leaves", 999999);
        editor.putInt("boiled_water", 99999);
        editor.putInt("cooked_food", 99999);
        editor.putInt("dirty_water", 999999);
        editor.putInt("food", 99999);
        editor.putInt("trade_post", 1);
        editor.putInt("apples", 99999);
        editor.apply();

    }




    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Cave.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Bugs and Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, "I found bugs/feeback: \n *");
        intent.setData(Uri.parse("mailto:ryanm1114@gmail.com")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
    }


}