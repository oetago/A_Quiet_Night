package com.milesstudios.aquietnight;

/**
 * Forest
 * Created by Ryanm14 on 7/14/2014.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.util.Helper;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;


public class Forest extends FragmentActivity {
    TextView log, storage;
    private Handler counterHandler = new Handler();
    Helper helper = new Helper(this);


    //For setting up back button
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Forest.this, Cave.class);
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
        setContentView(R.layout.forest);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);


        //Declare Textviews
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        Button dirty_water = (Button) findViewById(R.id.dirty_water);
        Button hunt = (Button) findViewById(R.id.hunt);
        //Setup TextSize and display Storage
        storage.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(12);
        storage.setTextSize(15);
         TextView cave_tab = (TextView) findViewById(R.id.cave_tab);
         TextView forest_tab = (TextView) findViewById(R.id.forest_tab);
        cave_tab.setTextSize(20);
        forest_tab.setPaintFlags(forest_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);
        helper.updateText();

        //Gets crafting and buildings
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);

        //"Tab Button"
        cave_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCave = new Intent(Forest.this, Cave.class);
                startActivity(openCave);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        if (!sharedPref.getBoolean("leaf_canteen", false)) {
            dirty_water.setVisibility(View.GONE);
        }
        if (!sharedPref.getBoolean("stone_sword", false)) {
            hunt.setVisibility(View.GONE);

        }
        helper.updateText();
    }


    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        finish();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Forest.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    //Buttons
    public void buttonWood(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        if (sharedPref.getBoolean("tin_axe", false)) {
            helper.collect("wood", 1, "apple", 1, 200, "leaves", 10, 200, "amber", 1, 10);
        } else if (sharedPref.getBoolean("stone_axe", false)) {
            helper.collect("wood", 1, "apple", 1, 150, "leaves", 2, 200);
        } else {
            helper.collect("wood", 1, "apple", 1, 10);
        }
        helper.updateText();
    }

    public void buttonStone(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        if (sharedPref.getBoolean("tin_pick", false)) {
            helper.collect("stone", 1, "amethyst", 1, 3, "bismuth", 1, 25, "tin", 3, 35);
        } else if (sharedPref.getBoolean("stone_pick", false)) {
            helper.collect("stone", 1, "tin", 1, 20, "bismuth", 1, 10);
        } else {
            helper.collect("stone", 1);
        }
        helper.updateText();
    }

    public void buttonDirtyWater(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        helper.collectL("dirty water", "dirty_water", 1);
    }

    public void buttonHunt(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        helper.collectL("Raw Food", "raw_food", 1);
        helper.updateText();
    }


}





