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

import com.milesstudios.aquietnight.reference.SharedPref;
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/12/2014.
 */
public class Buildings extends ActivityGroup {
    Helper helper = new Helper(this);
    private Handler counterHandler = new Handler();
    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Buildings.this, Cave.class);
                openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        //smooth trans for crafting, fix layout


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
        Button tannery = (Button) findViewById(R.id.tannery);
        log.setTextSize(11);

        storage.setTextSize(15);

        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        helper.updateButtons("Buildings");
        helper.updateText();
        runTimer();
        tannery.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Buildings.this, Cave.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        finish();
        editor.apply();
        super.onPause();
    }

    //Buttons
    public void buttonFireplace(View v) {
        helper.build("Bonfire", "Wood: 50 \nStone: 45", "wood", 50, "stone", 45, "bonfire", this, "Buildings");
    }

    public void buttonWorkshop(View v) {
        helper.build("Workshop", "Wood: 30\nStone: 30", "wood", 30, "stone", 30, "workshop", this, "Buildings");
    }

    public void buttonVillage(View v) {
        helper.build("Village Foundation", "Stone: 135 \nLeaves: 90\nCooked Food: 10", SharedPref.STONE, 135, SharedPref.LEAVES, 90, "cooked_food", 10, SharedPref.VILLAGE, this, "Buildings");
        helper.updateWorkers();
    }


    public void buttonTannery(View v) {
        helper.build("Tannery", "Wood: 75 \nStone: 45\nLead:5", SharedPref.WOOD, 75, SharedPref.STONE, 45, SharedPref.LEAD, 5, SharedPref.TANNERY, this, "Buildings");
    }

    public void buttonSmeltery(View v) {
        helper.build("Smeltery", "Stone: 85\nLead: 10", SharedPref.STONE, 85, SharedPref.LEAD, 10, SharedPref.SMELTERY, this, "Buildings");
    }

    public void buttonStorageShed(View v) {
        helper.build("Storage Shed", "Wood: 150 \nStone: 100\nLeaves: 75", "wood", 150, "stone", 100, "leaves", 75, "storage_shed", this, "Buildings");
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 5000);
    }

}




