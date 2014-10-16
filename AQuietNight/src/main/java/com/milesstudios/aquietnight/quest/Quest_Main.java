package com.milesstudios.aquietnight.quest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.milesstudios.aquietnight.Cave;
import com.milesstudios.aquietnight.Crafting;
import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.crafting.Weapons_Armor;

/**
 * Created by Ryanm14 on 10/15/2014.
 */
public class Quest_Main extends Activity {
Button storage;
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Quest_Main.this, Cave.class);
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

                if (items[position].equals("Bosses")) {
                    Intent openBosses = new Intent(Quest_Main.this, Bosses.class);
                    startActivity(openBosses);
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
        setContentView(R.layout.quest_main);
        final TextView log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        final Button apple_collector = (Button) findViewById(R.id.quest_apple_collector);
        final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chooper);
        //Saving
        final int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        //UpdateText();


        wood_chopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Quest_Main.this); //Read Update
                alertDialog.setTitle("Quest: Wood Chopper");
                alertDialog.setMessage("Hello! I seem to have misplaced my wood can you go chop some for me? I will glady pay you\n Needs: 7 Wood");
                alertDialog.setCancelable(true);
                alertDialog.setInverseBackgroundForced(true);
                LayoutInflater factory = LayoutInflater.from(Quest_Main.this);
                final View view = factory.inflate(R.layout.image, null);
                alertDialog.setView(view);
                alertDialog.setNegativeButton("Be back soon", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
                alertDialog.setPositiveButton("Give him the items", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int wood_counter = sharedPref.getInt("wood", 0);
                        if (wood_counter >= 7) {
                            log.append("\n You built a workshop!");
                            wood_counter -= 7;
                            wood_chopper.setEnabled(false);
                            wood_chopper.setVisibility(View.INVISIBLE);
                            SharedPreferences.Editor wood = sharedPref.edit();
                            wood.putInt("wood", wood_counter);
                            wood.apply();
                        } else {
                            log.append("\n You don't have enough resources!");
                        }
                        //UpdateText();
                        dialog.dismiss();
                    }

                });
                AlertDialog alert = alertDialog.create();
                alert.show();


            }
        });
    }



            @Override
            public void onBackPressed() {
                Intent openMain = new Intent(Quest_Main.this, Cave.class);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }


    public void UpdateText() {
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
        if (wood_counter >= 1) {
            storage.append("\n Wood: " + wood_counter);
        }
        if (leaves_counter >= 1) {
            storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            storage.append("\n Stone: " + stone_counter);
        }
        if (hard_wood_counter >= 1) {
            storage.append("\n Hard Wood: " + hard_wood_counter);
        }
        if (dirty_water_counter >= 1) {
            storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if (food_counter >= 1) {
            storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if (cooked_food_counter >= 1) {
            storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if (boiled_water_counter >= 1) {
            storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        if (apple_counter >= 1) {
            storage.append("\n Apples: " + apple_counter);
        }


    }
}

