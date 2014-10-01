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
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Ryanm14 on 9/13/2014.
 */
public class Trade extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, hard_wood_counter;
    Button quest_map;
    TextView log, storage;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Trade.this, Cave.class);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
        setContentView(R.layout.trade);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        quest_map = (Button) findViewById(R.id.quest_map);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving

        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
        int wood_counter = sharedPref.getInt("wood", 0);
        int quest_map_b = sharedPref.getInt("quest_map",0);
        updateText();

        if(quest_map_b == 1){
            quest_map.setVisibility(View.INVISIBLE);
            quest_map.setEnabled(false);
        }

        quest_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
                int apple_counter = sharedPref.getInt("apples",0);
                int quest_map_b = sharedPref.getInt("quest_map",0);
                if (cooked_food_counter >= 10 && apple_counter >= 2){
                log.append("\n You traded for a quest map!");
                    cooked_food_counter -= 10;
                    apple_counter -= 2;
                    quest_map_b = 1;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("cooked_meat", cooked_food_counter);
                    editor.putInt("quest_map", quest_map_b);
                    editor.putInt("apples", apple_counter);
                    editor.apply();
                    updateText();
                    quest_map.setVisibility(View.INVISIBLE);
                    quest_map.setEnabled(false);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter






            }

            ;

        });
        quest_map.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                quest_map.setText("Cooked Food: 10 \n Apples: 2");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        quest_map.setText("Quest Map");
                    }
                }, 3000L);
                return true;

            }
        });




    }

    public void updateText(){
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


    }

    @Override
    public void onBackPressed() {

    }

}
