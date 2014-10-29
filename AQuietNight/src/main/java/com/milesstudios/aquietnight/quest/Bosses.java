package com.milesstudios.aquietnight.quest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.milesstudios.aquietnight.Cave;
import com.milesstudios.aquietnight.R;

import java.util.Random;

/**
 * Created by Ryanm14 on 10/15/2014.
 */
public class Bosses extends Activity {

    int stone_pickb, stone_axeb, leaf_armorb;
    double percent_forest_temple;
    Button forest_temple;
    Boolean forest_temple_b;
    TextView log, storage, percent_forest_temple_view;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Bosses.this, Cave.class);
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
            // Adapter
            SpinnerAdapter adapter =
                    ArrayAdapter.createFromResource(this, R.array.quest,
                            android.R.layout.simple_spinner_dropdown_item);

// Callback
            ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

                String[] items = getResources().getStringArray(R.array.quest); // List items from res

                @Override
                public boolean onNavigationItemSelected(int position, long id) {

                    if (items[position].equals("Quests")) {
                        Intent openBosses = new Intent(Bosses.this, Quest_Main.class);
                        startActivity(openBosses);
                    }

                    Log.d("NavigationItemSelected", items[position]);
                    return true;

                }

            };

        //Saving


        super.onCreate(savedInstanceState);
        setContentView(R.layout.bosses);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        forest_temple = (Button) findViewById(R.id.forest_temple);
        percent_forest_temple_view = (TextView) findViewById(R.id.percent_forest_temple);
        log.setTextSize(11);
        storage.setTextSize(15);
        //True = completed
        forest_temple_b = sharedPref.getBoolean("forest_temple",false);
        //Saving
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int rusty_sword_b = sharedPref.getInt("rusty_sword_b", 0);
        int chain_armor_b = sharedPref.getInt("chain_armor_b", 0);
        UpdateText();

        if(quest_map_b == 0 || forest_temple_b == true){
            forest_temple.setVisibility(View.INVISIBLE);
            forest_temple.setEnabled(false);
        }

        //Calculations
        stone_axeb *= 5;
        stone_pickb *= 3;
        leaf_armorb *= 10;
        stone_sword_b *= 10;
        //TODO make it not add stone/leaf
        rusty_sword_b *= 20;
        chain_armor_b *= 20;
        cooked_food_counter *= 2;
        boiled_water_counter *= 2;
        percent_forest_temple = (stone_axeb+ chain_armor_b + rusty_sword_b + stone_pickb + leaf_armorb + stone_sword_b + cooked_food_counter + boiled_water_counter) / 108.0;
        percent_forest_temple = Math.round(percent_forest_temple * 100);


        forest_temple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int forest_temple_chance = rand.nextInt(100);

                if (forest_temple_chance < percent_forest_temple) {
                    log.append("You won!");
                    log.append("You found some wood, stone, and coins");
                    int wood_counter = sharedPref.getInt("wood",0);
                    int stone_counter = sharedPref.getInt("stone",0);
                    int coin_counter = sharedPref.getInt("coins",0);
                    wood_counter += 5;
                    stone_counter += 6;
                    coin_counter += 5;
                    forest_temple.setEnabled(false);
                    forest_temple.setVisibility(View.INVISIBLE);
                    forest_temple_b = true;
                    SharedPreferences.Editor wood = sharedPref.edit();
                    wood.putBoolean("forest_temple", forest_temple_b);
                    wood.putInt("stone", stone_counter);
                    wood.putInt("wood", wood_counter);
                    wood.putInt("coins",coin_counter);
                    wood.apply();
                } else {
                    log.append("You died!");
                }

            }

            ;

        });

        percent_forest_temple_view.setText(percent_forest_temple + "% Chance to win");


    }
    @Override
    public void onPause(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text",log_text);
        editor.apply();
        super.onPause();
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

    }
}
