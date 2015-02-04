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
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryan on 9/27/2014.
 */
public class Tools extends ActivityGroup {
    Button stone_axe, stone_pick;
    Boolean copper_axe_b, copper_pick_b;
    TextView log, storage;
    Helper helper = new Helper(this);
    private Handler counterHandler = new Handler();

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
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tools_labels, R.layout.spinner_item);
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {
            String[] items = getResources().getStringArray(R.array.tools); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Tools")) {
                }
                if (items[position].equals("Weapons and Armor")) {
                    Intent openWeapons_Armor = new Intent(Tools.this, Weapons_Armor.class);
                    startActivity(openWeapons_Armor);
                }
                if (items[position].equals("Food and Water")) {
                    Intent openFood_Water = new Intent(Tools.this, Food_Water.class);
                    startActivity(openFood_Water);
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
        setContentView(R.layout.crafting_tools);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        stone_axe = (Button) findViewById(R.id.stone_axe);
        stone_pick = (Button) findViewById(R.id.stone_pick);
        Button tin_pick = (Button) findViewById(R.id.tin_pick);
        Button tin_axe = (Button) findViewById(R.id.tin_axe);
        log.setTextSize(11);
        storage.setTextSize(15);
        boolean stone_axeb = sharedPref.getBoolean("stone_axe", false);
        boolean stone_pickb = sharedPref.getBoolean("stone_pick", false);
        boolean tinaxe = sharedPref.getBoolean("tin_axe", false);
        boolean tinpick = sharedPref.getBoolean("tin_pick", false);
        final String log_text = sharedPref.getString("log_text", "");
        Boolean forest_temple_b = sharedPref.getBoolean("forest_temple", false);
        log.setText(log_text);

        if (tinaxe || !forest_temple_b) {
            tin_pick.setVisibility(View.GONE);
        }
        if (tinpick || !forest_temple_b) {
            tin_axe.setVisibility(View.GONE);
        }
        if (stone_axeb) {
            stone_axe.setVisibility(View.GONE);
        } else {
            tin_axe.setVisibility(View.GONE);
        }
        if (stone_pickb) {
            stone_pick.setVisibility(View.GONE);
        } else {
            tin_pick.setVisibility(View.GONE);
        }
        saveChoice();
        runTimer();
    }

    public void saveChoice() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 0;
        int tools = 1;
        int weapons_armor = 0;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Tools.this, Cave.class);
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
        super.onPause();
    }

    public void buttonStoneAxe(View v) {
        helper.build("Stone Axe", "Wood: 25 \nStone: 25", "wood", 25, "stone", 25, "stone_axe", this);
    }

    public void buttonStonePick(View v) {
        helper.build("Stone Pick", "Wood: 20 \nStone: 20", "wood", 20, "stone", 20, "stone_pick", this);
    }

    public void buttonTinPick(View v) {
        helper.build("Tin Pick", "Wood: 35 \nTin: 10", "wood", 35, "stone", 10, "tin_pick", this);
    }

    public void buttonTinAxe(View v) {
        helper.build("Tin Axe", "Wood: 25 \nTin: 5", "wood", 35, "stone", 10, "tin_axe", this);
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 250);
    }

    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };
}






