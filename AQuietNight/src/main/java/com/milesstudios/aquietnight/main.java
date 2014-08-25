package com.milesstudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ActionBar;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.concurrent.locks.Condition;

public class main extends Activity {
    Button fix_walls, buildings, trading, crafting, quests;
    TextView log, storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cave);
        buildings = (Button) findViewById(R.id.buildings);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        log.setText("THIS APP IS FOR TESTING HI!");
        // Look up the AdView as a resource and load a request.

        AdView adView = (AdView) this.findViewById(R.id.ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        //TODO Check other buttons and scaling



        buildings.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent openForest = new Intent(main.this, Forest.class);
                startActivity(openForest);
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