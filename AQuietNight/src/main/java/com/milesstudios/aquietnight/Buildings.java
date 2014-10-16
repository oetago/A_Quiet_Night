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
import android.widget.ProgressBar;
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

        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int fireplace_b = sharedPref.getInt("fireplace", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int trade_post_b = sharedPref.getInt("trade_post", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);

        if (fireplace_b == 1){
            fireplace.setEnabled(false);
            fireplace.setVisibility(View.INVISIBLE);
        }
        //Fix later
        if (workshop_b >= 1){
            workshop.setEnabled(false);
            workshop.setVisibility(View.INVISIBLE);
        }
        if (trade_post_b == 1){
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
        }
        if(workshop_b == 0){
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
            fireplace.setEnabled(false);
            fireplace.setVisibility(View.INVISIBLE);
        }
        if(stone_sword_b == 0){
            trade_post.setEnabled(false);
            trade_post.setVisibility(View.INVISIBLE);
        }
        UpdateText();

        fireplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int fireplace_b = sharedPref.getInt("fireplace",0);
                if (stone_counter >= 12 && wood_counter >= 7){
                    log.append("\n You lit a Fireplace!");
                    stone_counter -= 12;
                    wood_counter -= 7;
                    fireplace.setEnabled(false);
                    fireplace_b = 1;
                    fireplace.setEnabled(false);
                    fireplace.setVisibility(View.INVISIBLE);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor editor3 = sharedPref.edit();
                editor3.putInt("fireplace", fireplace_b);
                editor3.apply();
                UpdateText();






            }

            ;

        });

        fireplace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                fireplace.setText("Stone: 12 \n Wood: 7");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        fireplace.setText("Fireplace");
                    }
                }, 3000L);
                return true;

            }
        });

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Buildings.this); //Read Update
                alertDialog.setTitle("Workshop");
                alertDialog.setMessage("2 Wood and 3 Stone");


                alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
                alertDialog.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int wood_counter = sharedPref.getInt("wood", 0);
                        int stone_counter = sharedPref.getInt("stone",0);
                        int workshop_b = sharedPref.getInt("workshop",0);
                        if (stone_counter >= 2 && wood_counter >= 3){
                            log.append("\n You built a workshop!");
                            stone_counter -= 2;
                            wood_counter -= 3;
                            workshop.setEnabled(false);
                            workshop_b = 1;
                            SharedPreferences.Editor stone3 = sharedPref.edit();
                            stone3.putInt("workshop", workshop_b);
                            stone3.apply();
                        }else{
                            log.append("\n You don't have enough resources!");
                        }
                        //Save counter
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("wood", wood_counter);
                        editor.apply();
                        SharedPreferences.Editor editor2 = sharedPref.edit();
                        editor2.putInt("stone", stone_counter);
                        editor2.apply();
                        UpdateText();
                        dialog.dismiss();
                    }

                });AlertDialog alert = alertDialog.create();
                alert.show();





            }


        });
        workshop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                workshop.setText("Stone: 3 \n Wood: 2");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        workshop.setText("Workshop");
                    }
                }, 3000L);
                return true;

            }
        });

        trade_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int trade_post_b = sharedPref.getInt("trade_post",0);
                if (leaves_counter >= 10 && wood_counter >= 12){
                    //TODO Change later items required
                    log.append("\n You built a trading post!");
                    leaves_counter -= 10;
                    wood_counter -= 12;
                    trade_post.setEnabled(false);
                    trade_post_b = 1;
                    trade_post.setEnabled(false);
                    trade_post.setVisibility(View.INVISIBLE);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor leaf3 = sharedPref.edit();
                leaf3.putInt("trade_post", trade_post_b);
                leaf3.apply();
                UpdateText();






            }

            ;

        });
        trade_post.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                trade_post.setText("Leaves: 10 \n Wood: 12");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        trade_post.setText("Trading Post");
                    }
                }, 3000L);
                return true;

            }
        });


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


    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Buildings.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

}
