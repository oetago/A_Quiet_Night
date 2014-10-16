package com.milesstudios.aquietnight.crafting;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

/**
 * Created by Ryan on 9/27/2014.
 */
public class Food_Water extends ActivityGroup {
    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, hard_wood_counter, workshop_b, boiled_water_counter, cooked_food_counter, water_sac_b;
    Button boil_water, cook_food, water_sac;
    TextView log, storage;
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Food_Water.this, Cave.class);
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
                ArrayAdapter.createFromResource(this, R.array.food_water,
                        android.R.layout.simple_spinner_dropdown_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.food_water); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if(items[position].equals("Food and Water")){
                }
                if(items[position].equals("Weapons and Armor")){
                    Intent openWepaons_Armor = new Intent(Food_Water.this, Weapons_Armor.class);
                    startActivity(openWepaons_Armor);
                }
                if(items[position].equals("Tools")){
                    setContentView(R.layout.crafting_tools);
                    Intent openTools = new Intent(Food_Water.this, Tools.class);
                    startActivity(openTools);
                }
                Log.d("NavigationItemSelected", items[position]);
                return true;

            }

        };

// Action Bar
        ActionBar actions = getActionBar();
        actions.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actions.setDisplayShowTitleEnabled(false);
        actions.setListNavigationCallbacks(adapter, callback);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.crafting_food_water);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        boil_water = (Button) findViewById(R.id.boil_water);
        cook_food = (Button) findViewById(R.id.cook_food);
        water_sac = (Button) findViewById(R.id.water_sac);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int fireplace_b = sharedPref.getInt("fireplace", 0);
        int water_sac_b = sharedPref.getInt("water_sac", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);

        if (fireplace_b == 1 && stone_sword_b == 1){
            cook_food.setEnabled(true);
            cook_food.setVisibility(View.VISIBLE);
        }else{
            cook_food.setEnabled(false);
            cook_food.setVisibility(View.INVISIBLE);
        }
        if(fireplace_b == 0 && water_sac_b == 0){
            boil_water.setEnabled(false);
            boil_water.setVisibility(View.INVISIBLE);
        }
        if(stone_sword_b == 0){
            water_sac.setEnabled(false);
            water_sac.setVisibility(View.INVISIBLE);
        }
        if(water_sac_b == 1){
            water_sac.setEnabled(false);
            water_sac.setVisibility(View.INVISIBLE);
        }

        updateText();

        boil_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
                int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
                if (wood_counter >= 1 && dirty_water_counter >= 1){
                    log.append("\n You boiled 1L of water!");
                    wood_counter -= 1;
                    boiled_water_counter += 1;
                    dirty_water_counter -= 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("boiled_water", boiled_water_counter);
                editor.putInt("dirty_water", dirty_water_counter);
                editor.apply();
                updateText();






            }

        });

        boil_water.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
                boil_water.setText("Wood: 1");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        boil_water.setText("Boil Water");
                    }
                }, 3000L);
                return true;

            }
        });

        cook_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int food_counter = sharedPref.getInt("food", 0);
                int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
                if (wood_counter >= 1 && food_counter >= 1) {
                    log.append("\n You cooked 1Lb of food!");
                    wood_counter -= 1;
                    cooked_food_counter += 1;
                    food_counter -= 1;
                } else {
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                editor.putInt("food", food_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("cooked_food", cooked_food_counter);
                editor2.apply();
                updateText();
            }

        });
        cook_food.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int food_counter = sharedPref.getInt("food", 0);
                cook_food.setText("Wood: 1");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        cook_food.setText("Cook Food");
                    }
                }, 3000L);
                return true;
//TODO Add a cook all
            }
        });
        water_sac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int water_sac_b = sharedPref.getInt("water_sac", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                if (leaves_counter >= 6) {
                    log.append("\n You crafted one Water Sack");
                    leaves_counter -= 6;
                    water_sac_b = 1;
                    water_sac.setEnabled(false);
                    water_sac.setVisibility(View.INVISIBLE);
                } else {
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("water_sac", water_sac_b);
                editor.apply();
                editor.putInt("leaves", leaves_counter);
                editor.apply();
                updateText();
            }

        });
        water_sac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                water_sac.setText("Leaves: 6");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        water_sac.setText("Water Sack");
                    }
                }, 3000L);
                return true;

            }
        });


    //Saving Tab
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 1;
        int tools = 0;
        int weapons_armor = 0;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
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
        Intent openMain = new Intent(Food_Water.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}






