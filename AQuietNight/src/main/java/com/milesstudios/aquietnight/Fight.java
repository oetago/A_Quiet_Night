package com.milesstudios.aquietnight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easyandroidanimations.library.ShakeAnimation;
import com.milesstudios.aquietnight.reference.SharedPref;

import java.util.Random;

/**
 * Created by Ryanm14 on 2/27/2015.
 */
public class Fight extends Activity {
    ProgressBar playerh, enemyh, strongb, swingb, eatb, stabb;
    TextView player, enemy, log, enemy_name, enemy_progress_text, player_progress_text, storage;
    Button swing, stab, eat, strong;
    int player_health, enemy_health, sword, boiled_water, cooked_food, player_health_max, q = 0;
    SharedPreferences sharedPref;
    CountDownTimer enemy_attack_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight);

        //Declare
        sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        playerh = (ProgressBar) findViewById(R.id.player_health_bar);
        enemyh = (ProgressBar) findViewById(R.id.enemy_health_bar);
        strongb = (ProgressBar) findViewById(R.id.strong_bar);
        swingb = (ProgressBar) findViewById(R.id.swing_bar);
        eatb = (ProgressBar) findViewById(R.id.eat_bar);
        stabb = (ProgressBar) findViewById(R.id.stab_bar);
        swing = (Button) findViewById(R.id.swing);
        eat = (Button) findViewById(R.id.eat);
        stab = (Button) findViewById(R.id.stab);
        strong = (Button) findViewById(R.id.strong);
        player = (TextView) findViewById(R.id.player);
        enemy = (TextView) findViewById(R.id.enemy);
        log = (TextView) findViewById(R.id.quest_log);
        enemy_name = (TextView) findViewById(R.id.enemyname);
        enemy_progress_text = (TextView) findViewById(R.id.enemyhealthtext);
        player_progress_text = (TextView) findViewById(R.id.playerhealthtext);
        player_health = sharedPref.getInt(SharedPref.PLAYER_HEALTH, 0);
        storage = (TextView) findViewById(R.id.quest_storage);

        //Setup

        log.setText("A wild " + Monster.getName() + " fights you");
        enemy_name.setText(Monster.getName());
        player_health_max = Monster.getPlayerMax();
        playerh.setMax(Monster.getPlayerMax());//temp
        playerh.setProgress(player_health); //temp
        enemyh.setMax(Monster.getEnemyHealth());
        enemyh.setProgress(Monster.getEnemyHealth());
        enemy.setText(Monster.getName());//temp
        stabb.setVisibility(View.INVISIBLE);
        eatb.setVisibility(View.INVISIBLE);
        swingb.setVisibility(View.INVISIBLE);
        strongb.setVisibility(View.INVISIBLE);
        player_health = sharedPref.getInt(SharedPref.PLAYER_HEALTH, 0);
        Log.d("Fight", "Helath:" + player_health);
        enemy_health = Monster.getEnemyHealth();
        sword = getSword();
        boiled_water = sharedPref.getInt(SharedPref.BOILED_WATER, 0);
        cooked_food = sharedPref.getInt(SharedPref.COOKED_FOOD, 0);
        enemy_progress_text.setText(enemy_health + "/" + enemy_health);
        player_progress_text.setText(player_health + "/" + Monster.getPlayerMax());
        checkButtons();
        enemyAttackNormal();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("attack", true);
        editor.apply();
        //Crashes
        stab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stab();
            }

        });

        strong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strong();
            }

        });

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eat();
            }

        });
    }


    public void enemyAttackNormal() {
        if (sharedPref.getBoolean("attack", false)) {
            Random rng = new Random();
            int attack = rng.nextInt(1) + 1; //Chance to attack every 5 seconds
            if (attack == 1) { //Add multiple attacks
                player_health -= Monster.getDamage(); //Damage done
                Log.d("Fight:", "fsmsge " + Monster.getDamage());
                new ShakeAnimation(player).animate();
                playerh.setProgress(player_health);
                player_progress_text.setText(player_health + "/" + Monster.getPlayerMax());
                checkButtons();
                playerDead();
                q++;
                log.setText("You took " + Monster.getDamage() + " dmg! \n" + log.getText());
                enemy_attack_timer = new CountDownTimer(3000, 20) {
                    @Override
                    public void onTick(long millisUntilFinishedl) {
                    }

                    @Override
                    public void onFinish() {
                        enemyAttackNormal();
                    }
                };
                enemy_attack_timer.start();
            } else {
                enemy_attack_timer = new CountDownTimer(2000, 20) {
                    @Override
                    public void onTick(long millisUntilFinishedl) {
                    }

                    @Override
                    public void onFinish() {
                        enemyAttackNormal();
                    }
                };
                enemy_attack_timer.start();
            }
        }
    }

    public void playerDead() {
        if (player_health <= 0) {
            Log.d("Fight", "Died:" + player_health);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(SharedPref.PLAYER_HEALTH, player_health_max);
            editor.putBoolean("attack", false);
            editor.putBoolean(SharedPref.FOREST_TEMPLE, false);
            editor.apply();
            Intent youlose = new Intent(Fight.this, Ending.class);
            youlose.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(youlose);
            if (q > 0) {
                enemy_attack_timer.cancel();
            }
            finish();
        }
    }

    public int getSword() {
        if (sharedPref.getBoolean(SharedPref.TIN_SWORD, false)) {
            return 2;
        } else {
            return 1;
        }
    }

    public void checkButtons() {
        if (cooked_food <= 0) {
            eat.setEnabled(false);
        } else {
            eat.setEnabled(true);
        }
        if (boiled_water <= 0) {
            stab.setEnabled(false);
            strong.setEnabled(false);
        } else if (boiled_water == 1) {
            strong.setEnabled(false);
        } else {
            stab.setEnabled(true);
            strong.setEnabled(true);
        }
        storage.setText("Storage:\nBoiled Water: " + boiled_water + "\nCooked Food: " + cooked_food);
    }

    public void swing(View v) {
        switch (sword) {
            case 1:
                enemy_health -= 1;
                log.setText("You did 1 dmg! \n" + log.getText());
                break;
            case 2:
                enemy_health -= 2;
                log.setText("You did 2 dmg! \n" + log.getText());
                break;
        }
        enemyh.setProgress(enemy_health);
        new ShakeAnimation(enemy).animate();
        enemy_progress_text.setText(enemy_health + "/" + Monster.getEnemyHealth());
        enemyDead();
        swing.setEnabled(false);
        swingb.setVisibility(View.VISIBLE);
        swingb.setMax(48);
        CountDownTimer swing_timer = new CountDownTimer(1000, 10) {
            int s = 0;

            @Override
            public void onTick(long millisUntilFinishedl) {
                s++;
                swingb.setProgress(s);

            }

            @Override
            public void onFinish() {
                swing.setEnabled(true);
                swingb.setVisibility(View.INVISIBLE);
                s = 0;
                swingb.setProgress(s);
            }
        };
        swing_timer.start();
    }

    public void stab() {
        switch (sword) {
            case 1:
                enemy_health -= 2;
                log.setText("You did 2 dmg! \n" + log.getText());
                break;
            case 2:
                enemy_health -= 3;
                log.setText("You did 3 dmg! \n" + log.getText());
                break;
        }
        boiled_water -= 1;
        checkButtons();
        enemyh.setProgress(enemy_health);
        new ShakeAnimation(enemy).animate();
        enemy_progress_text.setText(enemy_health + "/" + Monster.getEnemyHealth());
        enemyDead();
        stab.setEnabled(false);
        stabb.setVisibility(View.VISIBLE);
        stabb.setMax(48);
        CountDownTimer swing_timer = new CountDownTimer(3500, 35) {
            int s = 0;

            @Override
            public void onTick(long millisUntilFinishedl) {
                s++;
                stabb.setProgress(s);

            }

            @Override
            public void onFinish() {
                stab.setEnabled(true);
                stabb.setVisibility(View.INVISIBLE);
                s = 0;
                stabb.setProgress(s);
            }
        };
        swing_timer.start();
    }

    public void strong() {
        switch (sword) {
            case 1:
                enemy_health -= 3;
                log.setText("You did 3 dmg! \n" + log.getText());
                break;
            case 2:
                enemy_health -= 5;
                log.setText("You did 5 dmg! \n" + log.getText());
                break;
        }
        boiled_water--;
        checkButtons();
        enemyh.setProgress(enemy_health);
        enemy_progress_text.setText(enemy_health + "/" + Monster.getEnemyHealth());
        new ShakeAnimation(enemy).animate();
        enemyDead();
        strong.setEnabled(false);
        strongb.setVisibility(View.VISIBLE);
        strongb.setMax(48);
        CountDownTimer swing_timer = new CountDownTimer(2000, 20) {
            int s = 0;

            @Override
            public void onTick(long millisUntilFinishedl) {
                s++;
                strongb.setProgress(s);

            }

            @Override
            public void onFinish() {
                strong.setEnabled(true);
                strongb.setVisibility(View.INVISIBLE);
                s = 0;
                strongb.setProgress(s);
            }
        };
        swing_timer.start();
    }

    public void eat() {
        log.setText("You healed for 5 health! \n" + log.getText());
        cooked_food--;
        checkButtons();
        if (player_health >= (player_health_max - 5)) {
            player_health = player_health_max;
        } else {
            player_health += 5;
        }
        player_progress_text.setText(player_health + "/" + Monster.getPlayerMax());
        playerh.setProgress(player_health);
        eat.setEnabled(false);
        eatb.setVisibility(View.VISIBLE);
        eatb.setMax(48);
        CountDownTimer swing_timer = new CountDownTimer(3000, 30) {
            int s = 0;

            @Override
            public void onTick(long millisUntilFinishedl) {
                s++;
                eatb.setProgress(s);

            }

            @Override
            public void onFinish() {
                eat.setEnabled(true);
                eatb.setVisibility(View.INVISIBLE);
                s = 0;
                eatb.setProgress(s);
            }
        };
        swing_timer.start();
    }

    public void enemyDead() {
        if (enemy_health <= 0) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(SharedPref.BOILED_WATER, boiled_water);
            editor.putInt(SharedPref.COOKED_FOOD, cooked_food);
            editor.putInt(SharedPref.PLAYER_HEALTH, player_health);
            editor.putBoolean("attack", true);
            editor.apply();
            if (q > 0) {
                enemy_attack_timer.cancel();
            }
            Monster.addEncounter();
            switch (Monster.getPlace()) {
                case 1:
                    ft();
                    finish();
            }
        }
    }

    private void ft() {
        Monster.setPlace(1);
        Log.d("Fight", "Lunching new fight");
        Random rng = new Random();
        Intent openMain = new Intent(this, Fight.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (Monster.getEncounter() < 4) {
            switch (rng.nextInt(3) + 1) {
                case 1:
                    new Monster("Acorn", 5, 2);
                    startActivity(openMain);
                    break;
                case 2:
                    new Monster("Branch", 9, 3);
                    startActivity(openMain);
                    break;
                case 3:
                    new Monster("Tree", 12, 1);
                    startActivity(openMain);
                    break;
            }
        } else if ((Monster.getEncounter() == 4)) {
            new Monster("THE BIG ONE", 7, 4);
            startActivity(openMain);
        } else {
            Log.d("Fight", "Going home");
            Monster.zeroEncounter();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(SharedPref.FOREST_TEMPLE, true);
            editor.apply();
            Intent openHome = new Intent(this, Ending.class);
            openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(openHome);
        }
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SharedPref.BOILED_WATER, boiled_water);
        editor.putInt(SharedPref.COOKED_FOOD, cooked_food);
        editor.apply();
        finish();
        super.onPause();
    }

    @Override
    public void onBackPressed() {

    }
}