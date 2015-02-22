package com.milesstudios.aquietnight.quest.forest_temple;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;

/**
 * Created by Ryan on 11/28/2014.
 */
public class Acorn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight);
        int enemy_health = 4;
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Boss_Helper bh = new Boss_Helper(this,sharedPref.getInt("player_health",0),4);
        SharedPreferences.Editor wood = sharedPref.edit();
        wood.putInt("enemy_health", enemy_health);
        wood.apply();
        final TextView log = (TextView) findViewById(R.id.quest_log);
        ProgressBar swing_bar = (ProgressBar) findViewById(R.id.swing_bar);
        ProgressBar stab_bar = (ProgressBar) findViewById(R.id.stab_bar);
        ProgressBar strong_bar = (ProgressBar) findViewById(R.id.strong_bar);
        ProgressBar eat_bar = (ProgressBar) findViewById(R.id.eat_bar);
        swing_bar.setVisibility(View.INVISIBLE);
        strong_bar.setVisibility(View.INVISIBLE);
        stab_bar.setVisibility(View.INVISIBLE);
        eat_bar.setVisibility(View.INVISIBLE);
        log.setMovementMethod(new ScrollingMovementMethod());
        log.setText("A small acorn approaches you...");
        bh.setup(this);
        bh.storage();
        bh.enemyAttack();
    }

    public void Stab(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Boss_Helper bh = new Boss_Helper(this,sharedPref.getInt("player_health",0),4);
        bh.stab();
    }

    public void Swing(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Boss_Helper bh = new Boss_Helper(this,sharedPref.getInt("player_health",0),4);
        bh.swing();
    }

    public void Strong(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Boss_Helper bh = new Boss_Helper(this,sharedPref.getInt("player_health",0),4);
        bh.strong();
    }

    public void eat(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Boss_Helper bh = new Boss_Helper(this,sharedPref.getInt("player_health",0),4);
        bh.eat();
    }


}
