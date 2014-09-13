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
                Intent openMain = new Intent(Buildings.this, main.class);
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
        setContentView(R.layout.buildings);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        mine = (Button) findViewById(R.id.mine);
        trade_post = (Button) findViewById(R.id.trade_post);
        workshop = (Button) findViewById(R.id.workshop);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        final SharedPreferences wood1_counter = getApplicationContext().getSharedPreferences("wood", wood_counter);
        final SharedPreferences leaves1_counter = getApplicationContext().getSharedPreferences("leaves", leaves_counter);
        final SharedPreferences stone1_counter = getApplicationContext().getSharedPreferences("stone", stone_counter);
        final SharedPreferences mine_shared = getApplicationContext().getSharedPreferences("mine", mine_b);
        final SharedPreferences workshop_shared = getApplicationContext().getSharedPreferences("workshop", workshop_b);
        final SharedPreferences trade_post_shared = getApplicationContext().getSharedPreferences("trade_post", trade_post_b);
        int wood_counter = wood1_counter.getInt("wood", 0);
        int leaves_counter = leaves1_counter.getInt("leaves", 0);
        int stone_counter = stone1_counter.getInt("stone", 0);
        int mine_b = mine_shared.getInt("mine", 0);
        int workshop_b = workshop_shared.getInt("workshop", 0);
        int trade_post_b = trade_post_shared.getInt("trade_post", 0);

        if (mine_b == 1){
            mine.setEnabled(false);
        }else{

        }
        if (workshop_b == 1){
            workshop.setEnabled(false);
        }else{

        }
        if (trade_post_b == 1){
            trade_post.setEnabled(false);
        }else{

        }
        storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);


        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wood_counter = wood1_counter.getInt("wood", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int leaves_counter = leaves1_counter.getInt("stone",0);
                int mine_b = mine_shared.getInt("mine",0);
                if (stone_counter >= 12 && wood_counter >= 7){
                    log.append("\n You dug a mine!");
                    stone_counter -= 12;
                    wood_counter -= 7;
                    mine.setEnabled(false);
                    mine_b = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor editor3 = mine_shared.edit();
                editor3.putInt("mine", mine_b);
                editor3.apply();






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
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int workshop_b = workshop_shared.getInt("workshop",0);
                if (stone_counter >= 5 && wood_counter >= 9){
                    log.append("\n You built a workshop!");
                    stone_counter -= 5;
                    wood_counter -= 9;
                    workshop.setEnabled(false);
                    workshop_b = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor stone3 = workshop_shared.edit();
                stone3.putInt("workshop", workshop_b);
                stone3.apply();






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
                int wood_counter = wood1_counter.getInt("wood", 0);
                int leaves_counter = leaves1_counter.getInt("leaves", 0);
                int stone_counter = stone1_counter.getInt("stone",0);
                int trade_post_b = trade_post_shared.getInt("trade_post",0);
                if (leaves_counter >= 50){
                    //TODO Change later items required
                    log.append("\n You build a trade post!");
                    leaves_counter -= 50;
                    trade_post.setEnabled(false);
                    trade_post_b = 1;

                    storage.setText("\t Storage: \n Wood: " + wood_counter + "\n Leaves: " + leaves_counter + "\n Stones: " + stone_counter);
                }else{
                    log.append("\n You don't have enough resources!");
                }


                //Save counter
                SharedPreferences.Editor editor = wood1_counter.edit();
                editor.putInt("wood", wood_counter);
                editor.apply();
                SharedPreferences.Editor editor2 = stone1_counter.edit();
                editor2.putInt("stone", stone_counter);
                editor2.apply();
                SharedPreferences.Editor leaf3 = trade_post_shared.edit();
                leaf3.putInt("trade_post", trade_post_b);
                leaf3.apply();






            }

            ;

        });
        trade_post.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                trade_post.setText("Leaves: 50");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        trade_post.setText("Trade Post");
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
