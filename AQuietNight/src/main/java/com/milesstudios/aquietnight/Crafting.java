package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    int wood_counter, leaves_counter, stone_counter;
    Button stone_axe, leaf_armor, stone_pick;
    TextView log, storage;
    Boolean stone_axet;

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
        log.setTextSize(9);
        storage.setTextSize(15);
        final SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        final SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        final SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        int wood_counter = wood1_counter.getInt("wood", 0);
        int leaves_counter = leaves1_counter.getInt("leaves", 0);
        int stone_counter = stone1_counter.getInt("stone", 0);
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);


        stone_axe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                if (stone_counter > 3 && wood_counter > 2){
                    log.append("\n You crafted a stone axe!");
                    wood_counter = wood_counter - 3;
                    stone_counter = stone_counter - 2;
                    stone_axe.setEnabled(false);

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("stone", stone_counter);
                editor.apply();





            }

            ;

        });

    }
}
