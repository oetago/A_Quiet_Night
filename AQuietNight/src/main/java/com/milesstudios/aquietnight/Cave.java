package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.milesstudios.aquietnight.util.Helper;

public class Cave extends Activity {
    Button buildings, trading, crafting, quests, village;
    TextView log, storage;
    SlidingMenu menu;
    Helper helper = new Helper(this);
    private android.os.Handler counterHandler = new android.os.Handler();
    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        buildings = (Button) findViewById(R.id.buildings);
        SharedPreferences.Editor editor = sharedPref.edit();
        runTimer();
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

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.log);

        final TextView cave_tab = (TextView) findViewById(R.id.cave_tab);
        final TextView forest_tab = (TextView) findViewById(R.id.forest_tab);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(5);
        menu.setFadeDegree(0.0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(600);
        menu.setMenu(R.layout.menu);
        cave_tab.setPaintFlags(cave_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        cave_tab.setTextSize(20);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);
        storage.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(11);
        storage.setTextSize(15);
        editor.putBoolean("intro", false);
        editor.apply();

        int village_int = sharedPref.getInt("village_int", 0);
        if (sharedPref.getBoolean("village", false)) {
            village_int++;
            editor.putInt("village_int", village_int);
            editor.apply();
        }
        if (village_int == 0) {
            village.setVisibility(View.INVISIBLE);
        } else if (village_int == 1) {
            anim.reset();
            village.clearAnimation();
            village.startAnimation(anim);

        }
        int quests_int = sharedPref.getInt("quests_int", 0);
        if (sharedPref.getInt("forest_text", 0) == 3) {
            quests_int++;
            editor.putInt("quests_int", quests_int);
            editor.apply();
        }
        if (quests_int == 0) {
            quests.setVisibility(View.INVISIBLE);
        } else if (quests_int == 1) {
            anim.reset();
            quests.clearAnimation();
            quests.startAnimation(anim);
        }

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
                openForest.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        crafting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrafting = new Intent(Cave.this, Crafting.class);
                openCrafting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openCrafting);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });


        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBuildings = new Intent(Cave.this, Buildings.class);
                openBuildings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openBuildings);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        quests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuest = new Intent(Cave.this, Quest.class);
                openQuest.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openQuest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openQuest = new Intent(Cave.this, Village.class);
                openQuest.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    // Delete Shared Prefernces

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // help action
                Clear_Data();
                return true;
            case R.id.action_share:
                sendEmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Clear_Data() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        Intent openMain = new Intent(Cave.this, splash.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }


    public void sendEmail() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Build: " + BuildConfig.VERSION_NAME);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 5000);
    }

    @Override
    public void onBackPressed() {

    }

}