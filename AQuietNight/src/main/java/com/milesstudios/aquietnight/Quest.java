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

import java.util.Random;

/**
 * Created by Ryanm14 on 9/12/2014.
 */
public class Quest extends ActivityGroup {

    int stone_pickb, stone_axeb, leaf_armorb;
    double percent_forest_temple;
    Button forest_temple;
    TextView log, storage, percent_forest_temple_view ;


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                Intent openMain = new Intent(Quest.this, main.class);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        forest_temple = (Button) findViewById(R.id.forest_temple);
        percent_forest_temple_view = (TextView) findViewById(R.id.percent_forest_temple);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        final SharedPreferences leaf1_armor = getApplicationContext().getSharedPreferences("leaf_armor", leaf_armorb);
        final SharedPreferences stone1_axe = getApplicationContext().getSharedPreferences("stone_axe", stone_axeb);
        final SharedPreferences stone1_pick = getApplicationContext().getSharedPreferences("stone_pick", stone_pickb);
        int stone_axeb = stone1_axe.getInt("stone_axe", 0);
        int stone_pickb = stone1_pick.getInt("stone_pick", 0);
        int leaf_armorb = leaf1_armor.getInt("leaf_armor", 0);
        storage.setText("\t Gear: \n Leaf Armor: " + leaf_armorb + "\n Stone Axe: " + stone_axeb + "\n Stone Pick: " + stone_pickb);

        //Calculations
        stone_axeb *= 5;
        stone_pickb *= 3;
        leaf_armorb *= 10;
        percent_forest_temple = (stone_axeb + stone_pickb + leaf_armorb) / 20.0;
        percent_forest_temple = Math.round(percent_forest_temple * 100);



        forest_temple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int forest_temple_chance = rand.nextInt(100);

                if(forest_temple_chance < percent_forest_temple){
                    log.setText("You won!");
                }else{
                    log.setText("You died!");
                }

            }

            ;

        });

        percent_forest_temple_view.setText(percent_forest_temple + "%");


    }
    @Override
    public void onBackPressed() {

    }

}
