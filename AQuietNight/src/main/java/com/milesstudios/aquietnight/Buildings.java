package com.milesstudios.aquietnight;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by Ryanm14 on 9/12/2014.
 */
public class Buildings extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, fireplace_b, workshop_b, trade_post_b;
    Button fireplace, workshop, trade_post;
    TextView log, storage;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Buildings.this, Cave.class);
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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        fireplace = (Button) findViewById(R.id.fireplace);
        trade_post = (Button) findViewById(R.id.trade_post);
        workshop = (Button) findViewById(R.id.workshop);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int fireplace_b = sharedPref.getInt("fireplace", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int trade_post_b = sharedPref.getInt("trade_post", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);

        if (fireplace_b == 1) {
            fireplace.setEnabled(false);
            fireplace.setVisibility(View.INVISIBLE);
        }
        //Fix later
        if (workshop_b >= 1) {
            workshop.setEnabled(false);
            workshop.setVisibility(View.INVISIBLE);
        }
        if (trade_post_b == 1) {
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
        }
        if (workshop_b == 0) {
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
            fireplace.setEnabled(false);
            fireplace.setVisibility(View.INVISIBLE);
        }
        if (stone_sword_b == 0) {
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
        }
        UpdateText();


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
        Intent openMain = new Intent(Buildings.this, Cave.class);
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
    //Buttons
    public void buttonFireplace(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Buildings.this);
        alertDialog.setTitle("Build: FirePlace");
        alertDialog.setMessage("Wood: 12 \n Stone: 7");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone", 0);
                int fireplace_b = sharedPref.getInt("fireplace", 0);
                if (stone_counter >= 12 && wood_counter >= 7) {
                    log.append("\n You lit a Fireplace!");
                    stone_counter -= 12;
                    wood_counter -= 7;
                    fireplace_b = 1;
                    fireplace.setEnabled(false);
                    fireplace.setVisibility(View.INVISIBLE);
                } else {
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("stone", stone_counter);
                editor.putInt("fireplace", fireplace_b);
                editor.apply();
                UpdateText();
                dialog.dismiss();
            }

        });
        AlertDialog alert = alertDialog.create();
        alert.show();


    }

    public void buttonWorkshop(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Buildings.this);
        alertDialog.setTitle("Build: Workshop");
        alertDialog.setMessage("2 Wood and 3 Stone");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone", 0);
                int workshop_b = sharedPref.getInt("workshop", 0);
                if (stone_counter >= 2 && wood_counter >= 3) {
                    log.append("\n You built a workshop!");
                    stone_counter -= 2;
                    wood_counter -= 3;
                    workshop.setEnabled(false);
                    workshop_b = 1;
                } else {
                    log.append("\n You don't have enough resources!");
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("stone", stone_counter);
                editor.putInt("workshop", workshop_b);
                editor.apply();
                UpdateText();
                dialog.dismiss();
            }

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buttonTradePost(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Buildings.this);
        alertDialog.setTitle("Build: Trade Post");
        alertDialog.setMessage("Wood: 12 \n Leaves: 7");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                int trade_post_b = sharedPref.getInt("trade_post", 0);
                if (leaves_counter >= 10 && wood_counter >= 12) {
                    //TODO Change later items required
                    log.append("\n You built a trading post!");
                    leaves_counter -= 10;
                    wood_counter -= 12;
                    trade_post_b = 1;
                    trade_post.setEnabled(false);
                    trade_post.setVisibility(View.INVISIBLE);
                } else {
                    log.append("\n You don't have enough resources!");
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("trade_post", trade_post_b);
                editor.putInt("leaves", leaves_counter);
                editor.apply();
                UpdateText();
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


}


