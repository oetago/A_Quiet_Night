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
public class Weapons_Armor extends ActivityGroup {
    int wood_counter, leaves_counter, stone_counter, stone_swordb, hard_wood_counter, workshop_b, boiled_water_counter, cooked_food_counter;
    Button stone_sword, leaf_armor;
    TextView log, storage;
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Weapons_Armor.this, Cave.class);
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
                ArrayAdapter.createFromResource(this, R.array.actions,
                        android.R.layout.simple_spinner_dropdown_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.actions); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if(items[position].equals("Weapons and Armor")){
                }
                if(items[position].equals("Food and Water")){
                    Intent openFood_Water = new Intent(Weapons_Armor.this, Food_Water.class);
                    startActivity(openFood_Water);
                }
                if(items[position].equals("Tools")){
                    setContentView(R.layout.crafting_tools);
                    Intent openTools = new Intent(Weapons_Armor.this, Tools.class);
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
        setContentView(R.layout.crafting_weapons_armor);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        stone_sword = (Button) findViewById(R.id.stone_sword);
        leaf_armor = (Button) findViewById(R.id.leaf_armor);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int leaf_armor_b = sharedPref.getInt("leaf_armor", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);

        if (stone_sword_b == 1){
            stone_sword.setEnabled(false);
            stone_sword.setVisibility(View.INVISIBLE);
        }
        if (leaf_armor_b == 1 || stone_sword_b == 0){
            leaf_armor.setEnabled(false);
            leaf_armor.setVisibility(View.INVISIBLE);
        }



        UpdateText();

        stone_sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_sword_b = sharedPref.getInt("stone_sword", 0);
                int stone_counter = sharedPref.getInt("stone", 0);
                if (wood_counter >= 3 && stone_counter >= 4){
                    log.append("\n You crafted a Stone Sword!");
                    wood_counter -= 3;
                    stone_counter -= 4;
                    stone_sword_b = 1;
                    stone_sword.setEnabled(false);
                    stone_sword.setVisibility(View.INVISIBLE);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                editor.putInt("stone", stone_counter);
                editor.apply();
                editor.putInt("stone_sword", stone_sword_b);
                editor.apply();
                UpdateText();

            }

        });

        stone_sword.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                stone_sword.setText("Wood: 3 \n Stone: 4");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        stone_sword.setText("Stone Sword");
                    }
                }, 3000L);
                return true;

            }
        });

        leaf_armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leaf_counter = sharedPref.getInt("leaves", 0);
                int leaf_armor_b = sharedPref.getInt("hard_wood", 0);
                if (leaf_counter >= 8) {
                    log.append("\n You crafted Leaf Armor!");
                    leaf_counter -= 8;
                    leaf_armor_b = 1;
                    leaf_armor.setEnabled(false);
                    leaf_armor.setVisibility(View.INVISIBLE);
                } else {
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("leaves", leaf_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("leaf_armor", leaf_armor_b);
                editor2.apply();
                UpdateText();
            }

        });
        leaf_armor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                leaf_armor.setText("Leaves: 8");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        leaf_armor.setText("Leaf Armor");
                    }
                }, 3000L);
                return true;

            }
        });



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







