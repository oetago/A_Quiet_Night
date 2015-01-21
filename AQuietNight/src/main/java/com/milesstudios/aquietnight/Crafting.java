package com.milesstudios.aquietnight;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.milesstudios.aquietnight.crafting.Food_Water;
import com.milesstudios.aquietnight.crafting.Tools;
import com.milesstudios.aquietnight.crafting.Weapons_Armor;
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/1/2014.
 */
public class Crafting extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, leaf_armorb, hard_wood_counter, workshop_b;
    Button stone_axe, leaf_armor, stone_pick, hard_wood;
    TextView log, storage;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Crafting.this, Cave.class);
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
                ArrayAdapter.createFromResource(this, R.array.crafting,
                        android.R.layout.simple_spinner_dropdown_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.crafting); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Crafting")) {

                }
                if (items[position].equals("Weapons and Armor")) {
                    Intent openWeapons_Armor = new Intent(Crafting.this, Weapons_Armor.class);
                    startActivity(openWeapons_Armor);
                }
                if (items[position].equals("Tools")) {
                    Intent openTools = new Intent(Crafting.this, Tools.class);
                    startActivity(openTools);
                }
                if (items[position].equals("Food and Water")) {
                    Intent openFood_Water = new Intent(Crafting.this, Food_Water.class);
                    startActivity(openFood_Water);
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
        setContentView(R.layout.crafting);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        boolean workshop_b = sharedPref.getBoolean("workshop", false);



        //Going back to crafting tab
        int weapons_armor = sharedPref.getInt("weapons_armor", 0);
        int food_water = sharedPref.getInt("food_water", 0);
        int tools = sharedPref.getInt("tools", 1);
        if (weapons_armor == 1) {
            Intent openWeapons_Armor = new Intent(Crafting.this, Weapons_Armor.class);
            startActivity(openWeapons_Armor);
        } else if (food_water == 1) {
            Intent openFood_Water = new Intent(Crafting.this, Food_Water.class);
            startActivity(openFood_Water);
        } else if (tools == 1) {
            Intent openTools = new Intent(Crafting.this, Tools.class);
            startActivity(openTools);
        }


    }

     private Handler counterHandler = new Handler();
    Helper helper = new Helper(this);
    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 250);
    }

    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Crafting.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

}
