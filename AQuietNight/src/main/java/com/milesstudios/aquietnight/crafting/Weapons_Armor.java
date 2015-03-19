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
import com.milesstudios.aquietnight.reference.SharedPref;
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryan on 9/27/2014.
 */
public class Weapons_Armor extends ActivityGroup {
    Button stone_sword, leaf_armor;
    TextView log, storage;
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
                Intent openMain = new Intent(Weapons_Armor.this, Cave.class);
                openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.weapons_armor, R.layout.spinner_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.weapons_armor); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Weapons and Armor")) {
                }
                if (items[position].equals("Food and Water")) {
                    Intent openFood_Water = new Intent(Weapons_Armor.this, Food_Water.class);
                    openFood_Water.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(openFood_Water);
                    overridePendingTransition(0, 0);
                }
                if (items[position].equals("Tools")) {
                    Intent openTools = new Intent(Weapons_Armor.this, Tools.class);
                    openTools.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(openTools);
                    overridePendingTransition(0, 0);
                }
                Log.d("NavigationItemSelected", items[position]);
                return true;
            }
        };

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
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        saveChoice();
        helper.updateText();
        helper.updateButtons("Weapons_Armor");
        runTimer();
    }

    public void saveChoice() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 0;
        int tools = 0;
        int weapons_armor = 1;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Weapons_Armor.this, Cave.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
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

    public void buttonStoneSword(View v) {
        helper.build("Shortsword", "Wood: 30 \nStone: 40", "wood", 30, "stone", 40, SharedPref.STONE_SWORD, this, "Weapons_Armor");
    }

    public void buttonLeafArmor(View v) {
        helper.build("Leaf Tunic", "Leaves: 35", "leaves", 35, SharedPref.LEAF_ARMOR, this, "Weapons_Armor");
    }

    public void buttonLeatherTunic(View v) {
        helper.build("Leather Tunic", "Hide: 30", SharedPref.HIDE, 45, SharedPref.LEATHER_TUNIC, this, "Weapons_Armor");
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 5000);
    }

    public void leadChestPlate(View view) {
        helper.build("Lead Chest Plate", "Lead: 20", SharedPref.LEAD, 20, SharedPref.LEAD_CHEST_PLATE, this, "Weapons_Armor");
    }

    public void leadSword(View view) {
        helper.build("Lead Zweihänder", "Wood: 60 \nLead: 25", "wood", 60, SharedPref.LEAD, 25, SharedPref.LEAD_SWORD, this, "Weapons_Armor");
    }
}









