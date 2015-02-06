package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/13/2014.
 */
public class Trade extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, hard_wood_counter;
    Button quest_map, rusty_sword, chain_armor;
    TextView log, storage;

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Trade.this, Cave.class);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        quest_map = (Button) findViewById(R.id.quest_map);
        rusty_sword = (Button) findViewById(R.id.rusty_sword);
        chain_armor = (Button) findViewById(R.id.chain_armor);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving

        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        int wood_counter = sharedPref.getInt("wood", 0);
        int quest_map_b = sharedPref.getInt("quest_map", 0);
        int rusty_sword_b = sharedPref.getInt("rusty_sword", 0);
        int chain_armor_b = sharedPref.getInt("chain_armor", 0);


        if (quest_map_b == 1) {
            quest_map.setVisibility(View.INVISIBLE);
            quest_map.setEnabled(false);
        }
        if (rusty_sword_b == 1) {
            rusty_sword.setVisibility(View.INVISIBLE);
        }
        if (chain_armor_b == 1) {
            chain_armor.setVisibility(View.INVISIBLE);
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

    //Buttons
    public void buttonQuestMap(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Trade.this);
        alertDialog.setTitle("Trade: Quest Map");
        alertDialog.setMessage("Golden Coins: 3");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Craft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int coins_counter = sharedPref.getInt("coins", 0);
                int quest_map_b = sharedPref.getInt("quest_map", 0);
                if (coins_counter >= 3) {
                    log.append("\n You traded for a quest map!");
                    coins_counter -= 3;
                    quest_map_b = 1;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("coins", coins_counter);
                    editor.putInt("quest_map", quest_map_b);
                    editor.apply();

                    quest_map.setVisibility(View.INVISIBLE);
                    quest_map.setEnabled(false);
                } else {
                    log.append("\n You don't have enough resources!");
                }

            }

            ;

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buttonRustySword(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Trade.this);
        alertDialog.setTitle("Trade: Rusty Sword");
        alertDialog.setMessage("Golden Coins: 7");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Craft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int coins_counter = sharedPref.getInt("coins", 0);
                int rusty_sword_b = sharedPref.getInt("rusty_sword", 0);
                if (coins_counter >= 7) {
                    log.append("\n You traded for a Rusty Sword!");
                    coins_counter -= 7;
                    rusty_sword_b = 1;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("coins", coins_counter);
                    editor.putInt("rusty_sword", rusty_sword_b);
                    editor.apply();

                    rusty_sword.setVisibility(View.INVISIBLE);
                    rusty_sword.setEnabled(false);
                } else {
                    log.append("\n You don't have enough resources!");
                }

            }

            ;

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buttonChainArmor(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Trade.this);
        alertDialog.setTitle("Trade: Chain Armor");
        alertDialog.setMessage("Golden Coins: 5");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Craft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int coins_counter = sharedPref.getInt("coins", 0);
                int chain_armor_b = sharedPref.getInt("chain_armor", 0);
                if (coins_counter >= 5) {
                    log.append("\n You traded for Chain Armor!");
                    coins_counter -= 5;
                    chain_armor_b = 1;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("coins", coins_counter);
                    editor.putInt("chain_armor", chain_armor_b);
                    editor.apply();

                    chain_armor.setVisibility(View.INVISIBLE);
                    chain_armor.setEnabled(false);
                } else {
                    log.append("\n You don't have enough resources!");
                }

            }

            ;

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}


