package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.app.ActionBar;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.concurrent.locks.Condition;

public class main extends ActivityGroup {
    Button fix_walls, buildings, trading, crafting, quests, forest_button;
    TextView log, storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        buildings = (Button) findViewById(R.id.buildings);
        forest_button = (Button) findViewById(R.id.forest_button);
        crafting = (Button) findViewById(R.id.crafting);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        log.setText("THIS APP IS FOR TESTING HI!");
        View f_b = findViewById(R.id.forest_button);
        //TODO chnage animation for the 3 of them?
        // Look up the AdView as a resource and load a request.


        //AdView adView = (AdView) this.findViewById(R.id.ad);
       // AdRequest adRequest = new AdRequest.Builder().build();
        //adView.loadAd(adRequest);



        //TODO Check other buttons and scaling



        forest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forest_button.setBackgroundColor(Color.BLACK);
                forest_button.getBackground().setAlpha(64);
                Intent openForest = new Intent(main.this, Forest.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

        crafting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCrafting = new Intent(main.this, Crafting.class);
                startActivity(openCrafting);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });








    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }


}