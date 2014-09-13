package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
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

    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, leaf_armorb;
    Button stone_axe, leaf_armor, stone_pick;
    TextView log, storage;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Crafting.this, main.class);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.crafting);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        stone_axe = (Button) findViewById(R.id.stone_axe);
        stone_pick = (Button) findViewById(R.id.stone_pick);
        leaf_armor = (Button) findViewById(R.id.leaf_armor);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        final SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        final SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        final SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        final SharedPreferences stone1_axe = getApplicationContext().getSharedPreferences("stone_axe", stone_axeb);
        final SharedPreferences stone1_pick = getApplicationContext().getSharedPreferences("stone_pick", stone_pickb);
        final SharedPreferences leaf1_armor = getApplicationContext().getSharedPreferences("leaf_armor", leaf_armorb);
        int wood_counter = wood1_counter.getInt("wood", 0);
        int leaves_counter = leaves1_counter.getInt("leaves", 0);
        int stone_counter = stone1_counter.getInt("stone", 0);
        int stone_axeb = stone1_axe.getInt("stone_axe", 0);
        int stone_pickb = stone1_pick.getInt("stone_pick", 0);
        int leaf_armorb = leaf1_armor.getInt("leaf_armor", 0);

        if (stone_axeb == 1){
            stone_axe.setEnabled(false);
        }else{

        }
        if (stone_pickb == 1){
            stone_pick.setEnabled(false);
        }else{

        }
        if (leaf_armorb == 1){
            leaf_armor.setEnabled(false);
        }else{

        }
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);


        stone_axe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int stone_axeb = stone1_axe.getInt("stone_axe",0);
                if (stone_counter >= 3 && wood_counter >= 2){
                    log.append("\n You crafted a stone axe!");
                    wood_counter -= 3;
                    stone_counter -= 2;
                    stone_axe.setEnabled(false);
                    stone_axeb = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor editor3 = stone1_axe.edit();
                editor3.putInt("stone_axe", stone_axeb);
                editor3.apply();






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
                        stone_axe.setText("Leaf Armor");
                    }
                }, 3000L);
                return true;

            }
        });

        stone_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int stone_pickb = stone1_pick.getInt("stone_pick",0);
                if (stone_counter >= 3 && wood_counter >= 4){
                    log.append("\n You crafted a stone pick!");
                    wood_counter -= 2;
                    stone_counter -= 4;
                    stone_pick.setEnabled(false);
                    stone_pickb = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor stone3 = stone1_pick.edit();
                stone3.putInt("stone_pick", stone_pickb);
                stone3.apply();






            }

            ;

        });
        stone_pick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                stone_pick.setText("Stone: 3 \n Wood: 2");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        stone_pick.setText("Leaf Armor");
                    }
                }, 3000L);
                return true;

            }
        });

        leaf_armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int leaf_armorb = leaf1_armor.getInt("leaf_armor",0);
                if (leaves_counter >= 7){
                    log.append("\n You crafted a stone axe!");
                    leaves_counter -= 7;
                    leaf_armor.setEnabled(false);
                    leaf_armorb = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor leaf3 = leaf1_armor.edit();
                leaf3.putInt("leaf_armor", leaf_armorb);
                leaf3.apply();






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


    }
    @Override
    public void onBackPressed() {

    }

}
