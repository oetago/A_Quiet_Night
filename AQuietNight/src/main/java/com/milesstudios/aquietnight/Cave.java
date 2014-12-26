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

public class Cave extends Activity {
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
        //Boolean mine_b = sharedPref.getBoolean("mine", false);
        crafting = (Button) findViewById(R.id.crafting);
        buildings = (Button) findViewById(R.id.buildings);
        trading = (Button) findViewById(R.id.trading);
        quests = (Button) findViewById(R.id.quests);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        int trade_post_b = sharedPref.getInt("trade_post", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int start_counter = sharedPref.getInt("start_counter", 0);
        Boolean forest_temple_b = sharedPref.getBoolean("forest_temple", false);
        Boolean rebuild_mine_b = sharedPref.getBoolean("mine", false);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.log);
        final Animation storage_anim_slide = AnimationUtils.loadAnimation(this, R.anim.storage_slide);
        final Animation storage_anim_slide2 = AnimationUtils.loadAnimation(this, R.anim.storage_slide2);
        SharedPreferences.Editor editor = sharedPref.edit();
        final TextView cave_tab = (TextView) findViewById(R.id.cave_tab);
        final TextView forest_tab = (TextView) findViewById(R.id.forest_tab);
        final TextView cave_tab_wmine = (TextView) findViewById(R.id.cave_tab_wmine);
        final TextView forest_tab_wmine = (TextView) findViewById(R.id.forest_tab_wmine);
        final TextView mine_tab = (TextView) findViewById(R.id.mine_tab);

        //fullStorage();
        SlidingMenu menu;
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(5);
        menu.setFadeDegree(0.0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(900);
        menu.setMenu(R.layout.menu);


        updateText();
        cave_tab.setPaintFlags(cave_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        cave_tab.setTextSize(20);
        //forest_tab.setPaintFlags(forest_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);
        storage.setMovementMethod(new ScrollingMovementMethod());
        // log.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(12);
        storage.setTextSize(15);

        if (!rebuild_mine_b) {
            cave_tab_wmine.setEnabled(false);
            cave_tab_wmine.setVisibility(View.INVISIBLE);
            forest_tab_wmine.setEnabled(false);
            forest_tab_wmine.setVisibility(View.INVISIBLE);
            mine_tab.setEnabled(false);
            mine_tab.setVisibility(View.INVISIBLE);
        } else {
            cave_tab.setEnabled(false);
            cave_tab.setVisibility(View.INVISIBLE);
            forest_tab.setEnabled(false);
            forest_tab.setVisibility(View.INVISIBLE);
        }

        boolean intro = false;
        editor.putBoolean("intro", intro);
        editor.apply();

        if (trade_post_b == 0) {
            trading.setVisibility(View.INVISIBLE);
            trading.setEnabled(false);
        } else if (trade_post_b == 1) {
            trade_post_b += 1;
            editor.putInt("trade_post", trade_post_b);
            editor.apply();
            anim.reset();
            trading.clearAnimation();
            trading.startAnimation(anim);

        }

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
        mine_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForest = new Intent(Cave.this, Mine.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });
        forest_tab_wmine.setOnClickListener(new View.OnClickListener() {
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


    }

    private void fullStorage() {
        final TextView full_storage = (TextView) findViewById(R.id.full_storage);
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
        int coin_counter = sharedPref.getInt("coins", 0);
        int copper_counter = sharedPref.getInt("copper", 0);
        int r_copper_counter = sharedPref.getInt("r_copper", 0);
        int coal_counter = sharedPref.getInt("coal", 0);

        full_storage.setText("\t Full Storage:");
        full_storage.append("\n Materials:");
        if (wood_counter >= 1) {
            full_storage.append("\n Wood: " + wood_counter);
        }
        if (leaves_counter >= 1) {
            full_storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            full_storage.append("\n Stone: " + stone_counter);
        }
        if (copper_counter >= 1) {
            full_storage.append("\n Raw Copper: " + copper_counter);
        }
        if (r_copper_counter >= 1) {
            full_storage.append("\n Refined Copper: " + r_copper_counter);
        }
        if (coal_counter >= 1) {
            full_storage.append("\n Coal: " + coal_counter);
        }

        if (apple_counter >= 1) {
            full_storage.append("\n Apples: " + apple_counter);
        }

        if (coin_counter >= 1) {
            full_storage.append("\n Coins: " + coin_counter);
        }

        full_storage.append("\n \n Food & Water:");

        if (dirty_water_counter >= 1) {
            full_storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if (food_counter >= 1) {
            full_storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if (cooked_food_counter >= 1) {
            full_storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if (boiled_water_counter >= 1) {
            full_storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        full_storage.append("\n\n Weapons & Armor");
        if (dirty_water_counter >= 1) {
            full_storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }

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
        updateText();
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
        updateText();
    }

    public void updateText() {
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
        int coin_counter = sharedPref.getInt("coins", 0);
        int copper_counter = sharedPref.getInt("copper", 0);
        int r_copper_counter = sharedPref.getInt("r_copper", 0);
        int coal_counter = sharedPref.getInt("coal", 0);

        storage.setText("\t Storage:");
        if (wood_counter >= 1) {
            storage.append("\n Wood: " + wood_counter);
        }
        if (leaves_counter >= 1) {
            storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            storage.append("\n Stone: " + stone_counter);
        }
        if (copper_counter >= 1) {
            storage.append("\n Raw Copper: " + copper_counter);
        }
        if (r_copper_counter >= 1) {
            storage.append("\n Refined Copper: " + r_copper_counter);
        }
        if (coal_counter >= 1) {
            storage.append("\n Coal: " + coal_counter);
        }
        if (dirty_water_counter >= 1) {
            storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if (food_counter >= 1) {
            storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if (cooked_food_counter >= 1) {
            storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if (boiled_water_counter >= 1) {
            storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        if (apple_counter >= 1) {
            storage.append("\n Apples: " + apple_counter);
        }


        if (coin_counter >= 1) {
            storage.append("\n \n \n Coins: " + coin_counter);
        }


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