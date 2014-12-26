package com.milesstudios.aquietnight.quest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
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
import com.milesstudios.aquietnight.Crafting;
import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.quest.forest_temple.Acorn;
import com.milesstudios.aquietnight.quest.forest_temple.Branch;
import com.milesstudios.aquietnight.quest.forest_temple.Mother_Tree;
import com.milesstudios.aquietnight.quest.forest_temple.Sapling;

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
                        R.layout.spinner_item);

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
        forest_temple_b = sharedPref.getBoolean("forest_temple", false);
        //Saving
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int rusty_sword_b = sharedPref.getInt("rusty_sword", 0);
        int chain_armor_b = sharedPref.getInt("chain_armor", 0);
        UpdateText();

        //   if(quest_map_b == 0 || forest_temple_b == true){
        //      forest_temple.setVisibility(View.INVISIBLE);
        //      forest_temple.setEnabled(false);
        //  }




    }

    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        Intent openCrafting = new Intent(Bosses.this, Crafting.class);
        startActivity(openCrafting);
        super.onPause();
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
        int coin_counter = sharedPref.getInt("coins", 0);

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


        if (coin_counter >= 1) {
            storage.append("\n \n \n Coins: " + coin_counter);
        }


    }

    @Override
    public void onBackPressed() {

    }

    public void buttonForestTemple(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(Bosses.this);
        dialog.setContentView(R.layout.bosses_dialog);
        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView desc = (TextView) dialog.findViewById(R.id.desc);
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button decline = (Button) dialog.findViewById(R.id.decline);
        title.setText("Quest: Forest Temple");
        desc.setText("I need to get some respect around here in order to build an army. Beating this temple will show my strength.");
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                RandomEnemy();


            }

        });
        dialog.show();


    }

    private void RandomEnemy() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int enemycount_ft = sharedPref.getInt("enemycount_ft", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int chain_armor_b = sharedPref.getInt("chain_armor", 0);
        int health = 8;
        if (chain_armor_b == 1) {
            health = 20;
        } else if (leaf_armorb == 1) {
            health = 15;
        }

        enemycount_ft=0;
        if (enemycount_ft <= 4) {
            Random rng = new Random();
            int enemy = rng.nextInt(3) + 1;
            if (enemy == 1) {
                Intent i = new Intent(Bosses.this, Acorn.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else if (enemy == 2) {
                Intent i = new Intent(Bosses.this, Sapling.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            } else if (enemy == 3) {
                Intent i = new Intent(Bosses.this, Branch.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        } else if (enemycount_ft == 4) {
            Intent i = new Intent(Bosses.this, Mother_Tree.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        SharedPreferences.Editor wood = sharedPref.edit();
        wood.putInt("enemycount_ft", enemycount_ft);
        wood.putInt("player_health", health);
        wood.apply();


    }
}

