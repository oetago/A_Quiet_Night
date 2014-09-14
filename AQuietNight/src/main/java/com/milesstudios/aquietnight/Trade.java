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
 * Created by Ryanm14 on 9/13/2014.
 */
public class Trade extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, hard_wood_counter;
    Button wood_for_leaves, leaves_for_wood, stone_for_wood;
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
        stone_for_wood = (Button) findViewById(R.id.stone_for_wood);
        leaves_for_wood = (Button) findViewById(R.id.leaves_for_wood);
        wood_for_leaves = (Button) findViewById(R.id.wood_for_leaves);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving

        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
        int wood_counter = sharedPref.getInt("wood", 0);
        UpdateText();

        stone_for_wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                if (wood_counter >= 15){
                log.append("\n You traded for some stone!");
                    stone_counter += 10;
                    wood_counter -= 15;
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





            }

            ;

        });

        stone_for_wood.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                stone_for_wood.setText("Wood: 15");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        stone_for_wood.setText("Stone: 10");
                    }
                }, 3000L);
                return true;

            }
        });

        wood_for_leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                if (leaves_counter >= 7){
                    log.append("\n You traded for some wood!");
                    leaves_counter -= 7;
                    wood_counter += 5;
                    }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("leaves", leaves_counter);
                editor2.apply();
                UpdateText();


            }

            ;

        });
        wood_for_leaves.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                wood_for_leaves.setText("Leaves: 7");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        wood_for_leaves.setText("Wood: 5");
                    }
                }, 3000L);
                return true;

            }
        });

        leaves_for_wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                if (wood_counter >= 5){
                    //TODO Change later items required
                    log.append("\n You traded for some leaves!");
                    leaves_counter += 7;
                    wood_counter -=5;
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPref.edit();
                editor2.putInt("leaves", leaves_counter);
                editor2.apply();
                UpdateText();
            }

            ;

        });
        leaves_for_wood.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                leaves_for_wood.setText("Wood: 5");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        leaves_for_wood.setText("Leaves: 7");
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
