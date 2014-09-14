package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Context;
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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.crafting);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        stone_axe = (Button) findViewById(R.id.stone_axe);
        stone_pick = (Button) findViewById(R.id.stone_pick);
        leaf_armor = (Button) findViewById(R.id.leaf_armor);
        hard_wood = (Button) findViewById(R.id.hard_wood);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        int leaf_armorb = sharedPref.getInt("leaf_armor", 0);
        int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);

        if (stone_axeb == 1){
            stone_axe.setEnabled(false);
        }
        if (stone_pickb == 1){
            stone_pick.setEnabled(false);
        }
        if (leaf_armorb == 1){
            leaf_armor.setEnabled(false);
        }
        if (workshop_b == 0){
            hard_wood.setEnabled(false);
            hard_wood.setVisibility(View.INVISIBLE);
        }else{
            hard_wood.setEnabled(true);
            hard_wood.setVisibility(View.VISIBLE);
        }
        UpdateText();

        stone_axe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int stone_axeb = sharedPref.getInt("stone_axe",0);
                if (stone_counter >= 3 && wood_counter >= 2){
                    log.append("\n You crafted a stone axe!");
                    stone_counter -= 3;
                    wood_counter -= 2;
                    stone_axe.setEnabled(false);
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
                editor3.putInt("stone_axe", stone_axeb);
                editor3.apply();
                UpdateText();






            }

            ;

        });

        stone_axe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                stone_axe.setText("Stone: 3 \n Wood: 2");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        stone_axe.setText("Stone Axe");
                    }
                }, 3000L);
                return true;

            }
        });

        stone_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int stone_pickb = sharedPref.getInt("stone_pick",0);
                int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
                if (stone_counter >= 3 && hard_wood_counter >= 4){
                    log.append("\n You crafted a stone pick!");
                    stone_counter -= 3;
                    hard_wood_counter -= 4;
                    stone_pick.setEnabled(false);
                    stone_pickb = 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("hard_wood", hard_wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor stone3 = sharedPref.edit();
                stone3.putInt("stone_pick", stone_pickb);
                stone3.apply();
                UpdateText();






            }

            ;

        });
        stone_pick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                stone_pick.setText("Stone: 3 \n Hard Wood: 4");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        stone_pick.setText("Stone Pick");
                    }
                }, 3000L);
                return true;

            }
        });

        leaf_armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leaves_counter = sharedPref.getInt("leaves", 0);
                int leaf_armorb = sharedPref.getInt("leaf_armor",0);
                if (leaves_counter >= 7){
                    log.append("\n You crafted a stone axe!");
                    leaves_counter -= 7;
                    leaf_armor.setEnabled(false);
                    leaf_armorb = 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("leaves", leaves_counter);
                editor2.apply();
                SharedPreferences.Editor leaf3 = sharedPref.edit();
                leaf3.putInt("leaf_armor", leaf_armorb);
                leaf3.apply();
                UpdateText();





            }

            ;

        });
        leaf_armor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                leaf_armor.setText("Leaves: 7");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        leaf_armor.setText("Leaf Armor");
                    }
                }, 3000L);
                return true;

            }
        });

        hard_wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int hard_wood_counter = sharedPref.getInt("hard_wood",0);
                if (wood_counter >= 3){
                    log.append("\n You crafted a piece of hard wood!");
                    wood_counter -= 3;
                    hard_wood_counter += 1;
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor hard_wood3 = sharedPref.edit();
                hard_wood3.putInt("hard_wood", hard_wood_counter);
                hard_wood3.apply();
                UpdateText();






            }

            ;

        });
        hard_wood.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                hard_wood.setText("Wood: 3");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        leaf_armor.setText("Hard Wood");
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
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter + "\n Hard Wood:" + hard_wood_counter);

    }
    @Override
    public void onBackPressed() {

    }

}
