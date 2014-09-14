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
 * Created by Ryanm14 on 9/12/2014.
 */
public class Buildings extends ActivityGroup {

    int wood_counter, leaves_counter, stone_counter, mine_b, workshop_b, trade_post_b;
    Button mine, workshop, trade_post;
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
        mine = (Button) findViewById(R.id.mine);
        trade_post = (Button) findViewById(R.id.trade_post);
        workshop = (Button) findViewById(R.id.workshop);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving

        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int mine_b = sharedPref.getInt("mine", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int trade_post_b = sharedPref.getInt("trade_post", 0);

        if (mine_b == 1){
            mine.setEnabled(false);
        }
        if (workshop_b == 1){
            workshop.setEnabled(false);
        }
        if (trade_post_b == 1){
            trade_post.setEnabled(false);
        }
        UpdateText();

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int mine_b = sharedPref.getInt("mine",0);
                if (stone_counter >= 12 && wood_counter >= 7){
                    log.append("\n You dug a mine!");
                    stone_counter -= 12;
                    wood_counter -= 7;
                    mine.setEnabled(false);
                    mine_b = 1;
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
                editor3.putInt("mine", mine_b);
                editor3.apply();
                UpdateText();






            }

            ;

        });

        mine.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                mine.setText("Stone: 12 \n Wood: 7");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mine.setText("Mine");
                    }
                }, 3000L);
                return true;

            }
        });

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int stone_counter = sharedPref.getInt("stone",0);
                int workshop_b = sharedPref.getInt("workshop",0);
                if (stone_counter >= 5 && wood_counter >= 9){
                    log.append("\n You built a workshop!");
                    stone_counter -= 5;
                    wood_counter -= 9;
                    workshop.setEnabled(false);
                    workshop_b = 1;
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
                SharedPreferences.Editor stone3 = sharedPref.edit();
                stone3.putInt("workshop", workshop_b);
                stone3.apply();
                UpdateText();






            }

            ;

        });
        workshop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                workshop.setText("Stone: 5 \n Wood: 9");
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
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter + "\n Hard Wood:" + hard_wood_counter);

    }
    @Override
    public void onBackPressed() {

    }

}
