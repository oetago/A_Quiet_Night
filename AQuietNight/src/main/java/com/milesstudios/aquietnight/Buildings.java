package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/12/2014.
 */
public class Buildings extends ActivityGroup {
    Helper helper = new Helper(this);
    private Handler counterHandler = new Handler();

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Buildings.this, Cave.class);
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
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings);
        TextView log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        runTimer();
        updateButtons();

    }


    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Buildings.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onPause() {
        TextView log = (TextView) findViewById(R.id.log);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        super.onPause();
    }

    //Buttons
    public void buttonFireplace(View v) {
        helper.build("Fireplace", "Wood: 12 \nStone: 7", "wood", 12, "stone", 7, "fireplace", this);
    }

    public void buttonWorkshop(View v) {
        helper.build("Workshop", "Wood: 3 \nStone: 2", "wood", 3, "stone", 2, "workshop", this);
    }

    public void buttonTradePost(View v) {
        helper.build("Trade Post", "Wood: 12 \nLeaves: 7", "wood", 12, "leaves", 7, "tradepost", this);
    }

    public void buttonRebuildMine(View v) {
        helper.build("Rebuild Mine", "Wood: 20 \nStone: 15", "wood", 20, "stone", 15, "rebuildmine", this);
    }

    public void buttonTannery(View v) {
        helper.buildBigger("Tannery", "Wood: 10 \nStone: 15\nLeaves:10", "wood", 20, "stone", 15, "leaves", 10, "tannery", this);
    }

    public void buttonSmithery(View v) {
        helper.build("Smithery", "Wood: 15 \nStone: 25", "wood", 15, "stone", 25, "smithery", this);
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 250);
    }

    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            updateButtons();
            runTimer();
        }
    };

    public void buttonStorageShed(View v) {
        helper.build("Storage Shed", "Wood: 150 \nStone: 100", "wood", 150, "stone", 100, "storage_shed", this);
    }

    public void updateButtons() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        boolean fireplace_b = sharedPref.getBoolean("fireplace", false);
        boolean workshop_b = sharedPref.getBoolean("workshop", false);
        boolean tradepost_b = sharedPref.getBoolean("tradepost", false);
        boolean smithery_b = sharedPref.getBoolean("smithery", false);
        boolean tannery_b = sharedPref.getBoolean("tannery", false);
        Boolean forest_temple_b = sharedPref.getBoolean("forest_temple", false);
        boolean mine_b = sharedPref.getBoolean("mine", false);
        Button fireplace = (Button) findViewById(R.id.fireplace);
        Button trade_post = (Button) findViewById(R.id.trade_post);
        Button workshop = (Button) findViewById(R.id.workshop);
        Button rebuild_mine = (Button) findViewById(R.id.rebuild_mine);
        Button smithery = (Button) findViewById(R.id.smithery);
        Button tannery = (Button) findViewById(R.id.tannery);

        if (!forest_temple_b) {
            rebuild_mine.setEnabled(false);
            rebuild_mine.setVisibility(View.INVISIBLE);
            smithery.setEnabled(false);
            smithery.setVisibility(View.INVISIBLE);
            tannery.setEnabled(false);
            tannery.setVisibility(View.INVISIBLE);
        }
        if (mine_b) {
            rebuild_mine.setVisibility(View.INVISIBLE);
        }
        if (tannery_b) {
            tannery.setVisibility(View.INVISIBLE);
        }
        if (smithery_b) {
            smithery.setVisibility(View.INVISIBLE);
        }

        if (fireplace_b) {
            fireplace.setVisibility(View.INVISIBLE);
        }
        if (tradepost_b) {
            trade_post.setVisibility(View.INVISIBLE);
        }
        if (!workshop_b) {
            trade_post.setEnabled(false);
            fireplace.setEnabled(false);
        } else {
            trade_post.setEnabled(true);
            fireplace.setEnabled(true);
            workshop.setEnabled(false);
        }


    }
}




