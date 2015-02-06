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
public class Food_Water extends ActivityGroup {
    Helper helper = new Helper(this);
    TextView log;
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
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.food_water, R.layout.spinner_item);
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {
            String[] items = getResources().getStringArray(R.array.food_water);

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Food and Water")) {
                }
                if (items[position].equals("Weapons and Armor")) {
                    Intent openWepaons_Armor = new Intent(Food_Water.this, Weapons_Armor.class);
                    startActivity(openWepaons_Armor);
                }
                if (items[position].equals("Tools")) {
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
        TextView storage = (TextView) findViewById(R.id.storage);
        TextView boil_water = (Button) findViewById(R.id.boil_water);
        Button cook_food = (Button) findViewById(R.id.cook_food);
        final Button leaf_canteen = (Button) findViewById(R.id.leaf_canteen);
        log.setTextSize(11);
        storage.setTextSize(15);
        boolean fireplace_b = sharedPref.getBoolean("fireplace", false);
        boolean leaf_canteen_b = sharedPref.getBoolean("leaf_canteen", false);
        boolean stone_sword_b = sharedPref.getBoolean("stone_sword", false);
        if (fireplace_b && stone_sword_b) {
            cook_food.setEnabled(true);
            cook_food.setVisibility(View.VISIBLE);
        } else {
            cook_food.setVisibility(View.INVISIBLE);
        }
        if (!fireplace_b || leaf_canteen_b) {
            boil_water.setVisibility(View.INVISIBLE);
        }
        if (stone_sword_b) {
            leaf_canteen.setVisibility(View.INVISIBLE);
        }
        if (leaf_canteen_b) {
            leaf_canteen.setVisibility(View.INVISIBLE);
        }
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        saveChoice();
        runTimer();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Food_Water.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
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

    //TODO Add long click for all

    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        super.onPause();
    }

    public void buttonBoilWater(View v) {
        helper.fandW("dirty_water", 1, "boiled_water", 2);
    }

    public void buttonCookFood(View v) {
        helper.fandW("raw_food", 1, "cooked_food", 2);
    }

    public void buttonLeafCanteen(View v) {
        helper.build("Leaf Canteen", "Leaves: 10", "leaves", 10, "leaf_canteen", this);
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 250);
    }

}







