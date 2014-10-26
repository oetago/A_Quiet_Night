package com.milesstudios.aquietnight.crafting;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

/**
 * Created by Ryan on 9/27/2014.
 */
public class Tools extends ActivityGroup {
    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, hard_wood_counter, workshop_b;
    Button stone_axe, stone_pick;
    TextView log, storage;
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Tools.this, Cave.class);
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
                ArrayAdapter.createFromResource(this, R.array.tools,
                        android.R.layout.simple_spinner_dropdown_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.tools); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if(items[position].equals("Tools")){
                }
                if(items[position].equals("Weapons and Armor")){
                    Intent openWeapons_Armor = new Intent(Tools.this, Weapons_Armor.class);
                    startActivity(openWeapons_Armor);
                }
                if(items[position].equals("Food and Water")){
                    setContentView(R.layout.crafting_tools);
                    Intent openFood_Water = new Intent(Tools.this, Food_Water.class);
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
        setContentView(R.layout.crafting_tools);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        stone_axe = (Button) findViewById(R.id.stone_axe);
        stone_pick = (Button) findViewById(R.id.stone_pick);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);

        if (stone_axeb == 1){
            stone_axe.setVisibility(View.INVISIBLE);
            stone_axe.setEnabled(false);

        }
        if (stone_pickb == 1){
            stone_pick.setVisibility(View.INVISIBLE);
            stone_pick.setEnabled(false);

        }
        UpdateText();
        //Saving Tab
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 0;
        int tools = 1;
        int weapons_armor = 0;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
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
        Intent openMain = new Intent(Tools.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

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
    public void buttonStoneAxe(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Tools.this);
        alertDialog.setTitle("Craft Stone Axe");
        alertDialog.setMessage("Wood: 3 \nStone: 2");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Craft" , new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int stone_axeb = sharedPref.getInt("stone_axe",0);
                if (stone_counter >= 3 && wood_counter >= 2){
                    log.append("\n You crafted a stone axe!");
                    stone_counter -= 3;
                    wood_counter -= 2;
                    stone_axe.setEnabled(false);
                    stone_axeb = 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("stone", stone_counter);
                editor.putInt("stone_axe", stone_axeb);
                editor.apply();
                UpdateText();
            }

            ;

        });
        AlertDialog alert = alertDialog.create();
        alert.show();


    }
    public void buttonStonePick(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Tools.this);
        alertDialog.setTitle("Craft: Stone Pick");
        alertDialog.setMessage("Wood: 3 \nStone: 4");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setPositiveButton("Craft" , new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int stone_pickb = sharedPref.getInt("stone_pick",0);
                if (stone_counter >= 3 && wood_counter >= 4){
                    log.append("\n You crafted a stone pick!");
                    stone_counter -= 3;
                    wood_counter -= 4;
                    stone_pick.setEnabled(false);
                    stone_pickb = 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("stone_pick", stone_pickb);
                editor.putInt("stone", stone_counter);
                editor.apply();
                UpdateText();
            }

            ;

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}






