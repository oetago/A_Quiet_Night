package com.milesstudios.aquietnight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.milesstudios.aquietnight.reference.SharedPref;

/**
 * Created by Ryanm14 on 2/28/2015.
 */
public class Ending extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
        TextView ham = (TextView) findViewById(R.id.ending);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        if(sharedPref.getBoolean(SharedPref.FOREST_TEMPLE,false)){
            ham.setText("You WONN111!11DEAAWDWA");
        }
        else{
            ham.setText("You lose LOL");
        }

    }
}
