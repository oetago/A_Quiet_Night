package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.app.ActionBar;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.concurrent.locks.Condition;

public class main extends ActivityGroup {
    Button buildings, trading, crafting, quests, forest_button;
    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, leaf_armorb, hard_wood_counter, workshop_b, trade_post_b;
    TextView log, storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        buildings = (Button) findViewById(R.id.buildings);
        forest_button = (Button) findViewById(R.id.forest_button);
        crafting = (Button) findViewById(R.id.crafting);
        buildings = (Button) findViewById(R.id.buildings);
        trading = (Button) findViewById(R.id.trading);
        quests = (Button) findViewById(R.id.quests);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        log.setText("THIS APP IS FOR TESTING!");
        final SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        final SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        final SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        final SharedPreferences stone1_axe = getApplicationContext().getSharedPreferences("stone_axe", stone_axeb);
        final SharedPreferences stone1_pick = getApplicationContext().getSharedPreferences("stone_pick", stone_pickb);
        final SharedPreferences leaf1_armor = getApplicationContext().getSharedPreferences("leaf_armor", leaf_armorb);
        final SharedPreferences hard_wood_counter_shared = getApplicationContext().getSharedPreferences("hard_wood", hard_wood_counter);
        final SharedPreferences workshop_shared = getApplicationContext().getSharedPreferences("workshop", workshop_b);
        int hard_wood_counter = hard_wood_counter_shared.getInt("hard_wood", 0);
        final SharedPreferences trade_post_shared = getApplicationContext().getSharedPreferences("trade_post", trade_post_b);
        int wood_counter = wood1_counter.getInt("wood", 0);
        int leaves_counter = leaves1_counter.getInt("leaves", 0);
        int stone_counter = stone1_counter.getInt("stone",0);
        int trade_post_b = trade_post_shared.getInt("trade_post", 0);
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter + "\n Hard Wood: " + hard_wood_counter);

        if (trade_post_b == 1){
            trading.setEnabled(true);
        }else{
            trading.setEnabled(false);
        }

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
                Intent openForest = new Intent(main.this, Forest.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        crafting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrafting = new Intent(main.this, Crafting.class);
                startActivity(openCrafting);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuildings = new Intent(main.this, Buildings.class);
                startActivity(openBuildings);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        trading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTrade = new Intent(main.this, Trade.class);
                startActivity(openTrade);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });
        quests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuest = new Intent(main.this, Quest.class);
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
                Settings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Delete Shared Prefernces

    public void Settings() {
        //Temp Bandaid To lazy

        SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        SharedPreferences stone1_axe = getApplicationContext().getSharedPreferences("stone_axe", stone_axeb);
        SharedPreferences stone1_pick = getApplicationContext().getSharedPreferences("stone_pick", stone_pickb);
        SharedPreferences leaf1_armor = getApplicationContext().getSharedPreferences("leaf_armor", leaf_armorb);
        SharedPreferences hard_wood_counter_shared = getApplicationContext().getSharedPreferences("hard_wood", hard_wood_counter);
        SharedPreferences workshop_shared = getApplicationContext().getSharedPreferences("workshop", workshop_b);
        SharedPreferences trade_post_shared = getApplicationContext().getSharedPreferences("trade_post", trade_post_b);


        SharedPreferences.Editor editor = wood1_counter.edit().clear();
        editor.commit();
        SharedPreferences.Editor editor2 = stone1_counter.edit().clear();
        editor2.commit();
        SharedPreferences.Editor editor3 = leaves1_counter.edit().clear();
        editor3.commit();
        SharedPreferences.Editor editor4 = stone1_pick.edit().clear();
        editor4.commit();
        SharedPreferences.Editor editor5 = stone1_axe.edit().clear();
        editor5.commit();
        SharedPreferences.Editor editor6 = leaf1_armor.edit().clear();
        editor6.commit();
        SharedPreferences.Editor editor7 = hard_wood_counter_shared.edit().clear();
        editor7.commit();
        SharedPreferences.Editor editor8 = workshop_shared.edit().clear();
        editor8.commit();
        SharedPreferences.Editor editor9 = trade_post_shared.edit().clear();
        editor9.commit();

    }




    @Override
    public void onBackPressed() {

    }


}