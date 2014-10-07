package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.app.ActionBar;
import android.view.MenuItem;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import java.util.concurrent.locks.Condition;

public class Cave extends ActivityGroup {
    Button buildings, trading, crafting, quests, forest_button;
    int wood_counter, leaves_counter, stone_counter,start_counter, stone_axeb, stone_pickb, leaf_armorb, hard_wood_counter, workshop_b, trade_post_b;
    TextView log, storage;
    ImageView top_tab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        buildings = (Button) findViewById(R.id.buildings);
        forest_button = (Button) findViewById(R.id.forest_button);
        crafting = (Button) findViewById(R.id.crafting);
        buildings = (Button) findViewById(R.id.buildings);
        trading = (Button) findViewById(R.id.trading);
        quests = (Button) findViewById(R.id.quests);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        top_tab = (ImageView) findViewById(R.id.top_tab);
        log.setText("THIS APP IS FOR TESTING!");
        int trade_post_b = sharedPref.getInt("trade_post", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int start_counter = sharedPref.getInt("start_counter", 0);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.log);
        SharedPreferences.Editor editor = sharedPref.edit();
        UpdateText();
        if (trade_post_b == 0){
            trading.setEnabled(false);
            trading.setVisibility(View.INVISIBLE);
        }else if(trade_post_b == 1){
            trade_post_b += 1;
            editor.putInt("trade_post", trade_post_b);
            editor.apply();
            anim.reset();
            trading.clearAnimation();
            trading.startAnimation(anim);

        }

        if (workshop_b == 0){
            crafting.setEnabled(false);
            crafting.setVisibility(View.INVISIBLE);
        }else if(workshop_b == 1){
            workshop_b += 1;
            editor.putInt("workshop", workshop_b);
            editor.apply();
            anim.reset();
            crafting.clearAnimation();
            crafting.startAnimation(anim);

        }
        if (quest_map_b == 0){
            quests.setEnabled(false);
            quests.setVisibility(View.INVISIBLE);
        }else if(quest_map_b == 1){
            quest_map_b += 1;
            editor.putInt("quest_map", quest_map_b);
            editor.apply();
            anim.reset();
            quests.clearAnimation();
            quests.startAnimation(anim);

        }
        if (start_counter == 0){
            buildings.setEnabled(false);
            buildings.setVisibility(View.INVISIBLE);
            anim.reset();
            top_tab.clearAnimation();
            top_tab.startAnimation(anim);
            log.setVisibility(View.INVISIBLE);
            storage.setVisibility(View.INVISIBLE);
        }

        start_counter +=1;
        editor.putInt("start_counter", start_counter);
        editor.apply();


        //TODO chnage animation for the 3 of them?
        // Look up the AdView as a resource and load a request.


        //AdView adView = (AdView) this.findViewById(R.id.ad);
        // AdRequest adRequest = new AdRequest.Builder().build();
        //adView.loadAd(adRequest);




        //TODO Check other buttons and scaling



        forest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forest_button.setBackgroundColor(Color.BLACK);
                forest_button.getBackground().setAlpha(64);
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
                Intent openQuest = new Intent(Cave.this, Quest.class);
                startActivity(openQuest);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

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


    }



    @Override
    public void onBackPressed() {

    }


}