package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.milesstudios.aquietnight.crafting.Food_Water;
import com.milesstudios.aquietnight.crafting.Tools;
import com.milesstudios.aquietnight.crafting.Weapons_Armor;

/**
 * Created by Ryanm14 on 9/1/2014.
 */
public class Crafting extends ActivityGroup {
    TextView log, storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        int weapons_armor = sharedPref.getInt("weapons_armor", 0);
        int food_water = sharedPref.getInt("food_water", 0);
        int tools = sharedPref.getInt("tools", 0);
        if (weapons_armor == 1) {
            Intent openWeapons_Armor = new Intent(Crafting.this, Weapons_Armor.class);
            startActivity(openWeapons_Armor);
        } else if (food_water == 1) {
            Intent openFood_Water = new Intent(Crafting.this, Food_Water.class);
            startActivity(openFood_Water);
        } else if (tools == 1) {
            Intent openTools = new Intent(Crafting.this, Tools.class);
            startActivity(openTools);
        } else {
            Intent openFood_Water = new Intent(Crafting.this, Food_Water.class);
            startActivity(openFood_Water);
        }


    }


}
