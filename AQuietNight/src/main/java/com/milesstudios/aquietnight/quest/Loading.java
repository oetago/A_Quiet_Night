package com.milesstudios.aquietnight.quest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.quest.forest_temple.Acorn;
import com.milesstudios.aquietnight.quest.forest_temple.Branch;
import com.milesstudios.aquietnight.quest.forest_temple.Mother_Tree;
import com.milesstudios.aquietnight.quest.forest_temple.Sapling;

import java.util.Random;

/**
 * Created by Ryan on 2/22/2015.
 */
public class Loading extends Activity {
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
        randomEnemy();
    }

    public void randomEnemy() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        boolean leaf_armorb = sharedPref.getBoolean("leaf_armor", false);
        boolean chain_armor_b = sharedPref.getBoolean("chain_armor", false);
        int health = 10;
        if (chain_armor_b) {
            health = 20;
        } else if (leaf_armorb) {
            health = 15;
        }
        editor.putInt("player_health", health);
        editor.apply();
        bossesLoading("Forest Temple",4);
    }
    @Override
    public void onBackPressed() {

    }
    public void bossesLoading(String place, int encounters) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.progress_quest_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.name);
        text.setText(place);
        final NumberProgressBar bnp = (NumberProgressBar) dialog.findViewById(R.id.number_progress_bar);
        counter = sharedPref.getInt("quest_progress", 0);
        bnp.setProgress(counter);
        int total = 10000 / encounters;
        CountDownTimer ham = new CountDownTimer(9000,346) {
            @Override
            public void onTick(long millisUntilFinished) {
                bnp.incrementProgressBy(1);
                counter++;
            }

            @Override
            public void onFinish() {
                editor.putInt("quest_progress", counter);
                if (sharedPref.getInt("enemycount_ft", 0) <= 3) {
                    Random rng = new Random();
                    //int enemy = rng.nextInt(3) + 1;
                    int enemy = 1;
                    if (enemy == 1) {
                        Intent i = new Intent(Loading.this, Acorn.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    } else if (enemy == 2) {
                        Intent i = new Intent(Loading.this, Sapling.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    } else if (enemy == 3) {
                        Intent i = new Intent(Loading.this, Branch.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }
                } else if (sharedPref.getInt("enemycount_ft", 0) == 4) {
                    Intent i = new Intent(Loading.this, Mother_Tree.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        };
        ham.start();
        dialog.show();
    }

}
