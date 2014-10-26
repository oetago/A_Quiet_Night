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
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.quest.Quest_Main;
import com.milesstudios.aquietnight.util.ChangeLog;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class Cave extends Activity{
    Button buildings, trading, crafting, quests, forest_button, all_data;
    int wood_counter, leaves_counter, stone_counter, start_counter, stone_axeb, stone_pickb, leaf_armorb, hard_wood_counter, workshop_b, trade_post_b;
    TextView log, storage;
    boolean storage_slide_b = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        buildings = (Button) findViewById(R.id.buildings);
        //TODO Fix version names
        ChangeLog cl = new ChangeLog(this);
        if (cl.firstRun())
            cl.getLogDialog().show();
        //cl.getFullLogDialog().show();
        //Tab
        int mine_b = sharedPref.getInt("mine", 0);
        crafting = (Button) findViewById(R.id.crafting);
        buildings = (Button) findViewById(R.id.buildings);
        trading = (Button) findViewById(R.id.trading);
        quests = (Button) findViewById(R.id.quests);
        all_data = (Button) findViewById(R.id.all_data);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        int trade_post_b = sharedPref.getInt("trade_post", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int start_counter = sharedPref.getInt("start_counter", 0);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.log);
        final Animation storage_anim_slide = AnimationUtils.loadAnimation(this, R.anim.storage_slide);
        final Animation storage_anim_slide2 = AnimationUtils.loadAnimation(this, R.anim.storage_slide2);
        SharedPreferences.Editor editor = sharedPref.edit();
        final TextView cave_tab  = (TextView) findViewById(R.id.cave_tab);
        final TextView forest_tab  = (TextView) findViewById(R.id.forest_tab);
        final Button storage_slide = (Button) findViewById(R.id.storage_slide);




        UpdateText();
        cave_tab.setPaintFlags(cave_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        cave_tab.setTextSize(20);
        //forest_tab.setPaintFlags(forest_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);
        log.setTypeface(tf);
        storage.setTypeface(tf);
        storage.setMovementMethod(new ScrollingMovementMethod());
        // log.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(12);
        storage.setTextSize(15);

        boolean intro = false;
        editor.putBoolean("intro", intro);
        editor.apply();

        if(trade_post_b ==0){
            trading.setVisibility(View.INVISIBLE);
            trading.setEnabled(false);
        }else{
            anim.reset();
            trading.clearAnimation();
            trading.startAnimation(anim);
        }
        all_data.setVisibility(View.INVISIBLE);
        all_data.setEnabled(false);
        if (start_counter == 0) {
            quests.setEnabled(false);
            quests.setVisibility(View.INVISIBLE);
        }



        if (workshop_b == 0) {
            crafting.setEnabled(false);
            crafting.setVisibility(View.INVISIBLE);
        } else if (workshop_b == 1) {
            workshop_b += 1;
            editor.putInt("workshop", workshop_b);
            editor.apply();
            anim.reset();
            crafting.clearAnimation();
            crafting.startAnimation(anim);

        }

        if (start_counter == 0) {
            buildings.setEnabled(false);
            buildings.setVisibility(View.INVISIBLE);
            anim.reset();
            log.setVisibility(View.INVISIBLE);
            storage.setVisibility(View.INVISIBLE);
        }
       // if(mine == 0)
        start_counter += 1;
        editor.putInt("start_counter", start_counter);
        editor.apply();
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);


        //TODO chnage animation for the 3 of them?
        // Look up the AdView as a resource and load a request.


        //AdView adView = (AdView) this.findViewById(R.id.ad);
        // AdRequest adRequest = new AdRequest.Builder().build();
        //adView.loadAd(adRequest);


        // all_data.setVisibility(View.INVISIBLE);
        all_data.setEnabled(true);
        //TODO Check other buttons and scaling

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


        if(!storage_slide_b){
            storage.setVisibility(View.INVISIBLE);
        }
        storage_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // new PathAnimation(storage).animate();
                if(storage_slide_b){
                    storage.clearAnimation();
                    storage.startAnimation(storage_anim_slide2);
                    storage.setVisibility(View.INVISIBLE);
                    storage_slide_b = false;
                }else {
                    storage.setVisibility(View.INVISIBLE);
                    storage.clearAnimation();
                    storage.startAnimation(storage_anim_slide);
                    storage_slide_b = true;
                        storage.setVisibility(View.VISIBLE);

                }


            }

        });


        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuildings = new Intent(Cave.this, Buildings.class);
                startActivity(openBuildings);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        trading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTrade = new Intent(Cave.this, Trade.class);
                startActivity(openTrade);
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
        final WebView webview = (WebView) findViewById(R.id.rain);
        webview.setVisibility(View.INVISIBLE);
        quests.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                webview.setVisibility(View.VISIBLE);
                //next line explained below
                //webview.setWebViewClient(new MyWebViewClient(this));
                //webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl("http://i.imgur.com/mq4pjzo.gif");
                return true;

            }
        });


        all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllData();

            }

        });


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
        UpdateText();
        Intent openMain = new Intent(Cave.this, Cave.class);
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
        UpdateText();
    }

    public void UpdateText(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        int food_counter = sharedPref.getInt("food", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int apple_counter = sharedPref.getInt("apples", 0);
        int coin_counter = sharedPref.getInt("coins",0);

        storage.setText("\t Storage:");
        if(wood_counter >= 1){
            storage.append("\n Wood: " + wood_counter);
        }
        if(leaves_counter >= 1){
            storage.append("\n Leaves: " + leaves_counter);
        }
        if(stone_counter >= 1){
            storage.append("\n Stone: " + stone_counter);
        }
        if(hard_wood_counter >= 1){
            storage.append("\n Hard Wood: " + hard_wood_counter);
        }
        if(dirty_water_counter >= 1){
            storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if(food_counter >= 1){
            storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if(cooked_food_counter >= 1){
            storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if(boiled_water_counter >= 1){
            storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        if(apple_counter >=1){
            storage.append("\n Apples: " + apple_counter);
        }


        if(coin_counter >=1){
            storage.append("\n \n \n Coins: " + coin_counter);
        }


    }


    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Cave.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }


}