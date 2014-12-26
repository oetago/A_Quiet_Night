
package com.milesstudios.aquietnight.quest.forest_temple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.quest.Bosses;

import java.util.Random;

/**
 * Created by Ryan on 11/28/2014.
 */
public class Sapling extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int rusty_sword = sharedPref.getInt("rusty_sword", 0);
        int leaf_armor = sharedPref.getInt("leaf_armor", 0);
        int chain_armor = sharedPref.getInt("chain_armor", 0);
        int cooked_food = sharedPref.getInt("cooked_food", 0);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        int player_health = sharedPref.getInt("player_health", 20);
        int enemy_health = 6;
        SharedPreferences.Editor wood = sharedPref.edit();
        wood.putInt("enemy_health", enemy_health);
        // wood.putInt("player_health", health);
        wood.apply();

        final TextView log = (TextView) findViewById(R.id.quest_log);
        TextView storage = (TextView) findViewById(R.id.quest_storage);
        TextView enemy = (TextView) findViewById(R.id.enemy);
        TextView player = (TextView) findViewById(R.id.player);
        final ProgressBar player_health_bar = (ProgressBar) findViewById(R.id.player_health_bar);
        final ProgressBar enemy_health_bar = (ProgressBar) findViewById(R.id.enemy_health_bar);
        Button stab = (Button) findViewById(R.id.stab);
        Button swing = (Button) findViewById(R.id.swing);
        Button strong = (Button) findViewById(R.id.strong);
        ProgressBar swing_bar = (ProgressBar) findViewById(R.id.swing_bar);
        ProgressBar stab_bar = (ProgressBar) findViewById(R.id.stab_bar);
        ProgressBar strong_bar = (ProgressBar) findViewById(R.id.strong_bar);
        ProgressBar eat_bar = (ProgressBar) findViewById(R.id.eat_bar);
        swing_bar.setVisibility(View.INVISIBLE);
        strong_bar.setVisibility(View.INVISIBLE);
        stab_bar.setVisibility(View.INVISIBLE);
        eat_bar.setVisibility(View.INVISIBLE);
        log.setMovementMethod(new ScrollingMovementMethod());

        log.setText("A Sapling approaches you..."); //Approaching text
        if (chain_armor == 1) {
            player_health_bar.setMax(20);
        } else if (leaf_armor == 1) {
            player_health_bar.setMax(15);
        } else {
            player_health_bar.setMax(8);
        }
        player_health_bar.setProgress(player_health);
        enemy_health_bar.setMax(enemy_health);
        enemy_health_bar.setProgress(enemy_health);
        enemy.setText("S"); //Set Icon
        Storage();
        if (rusty_sword == 0) {
            strong.setEnabled(false);
        }
        if (boiled_water == 0) {
            stab.setEnabled(false);
        }
        enemyAttack();


    }

    public void Stab(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        final Button stab = (Button) findViewById(R.id.stab);
        if (boiled_water >= 1) {
            ProgressBar enemy_health_bar = (ProgressBar) findViewById(R.id.enemy_health_bar);
            final ProgressBar stab_bar = (ProgressBar) findViewById(R.id.stab_bar);
            TextView enemy = (TextView) findViewById(R.id.enemy);
            TextView log = (TextView) findViewById(R.id.quest_log);
            int enemy_health = sharedPref.getInt("enemy_health", 5);
            enemy_health -= 2;
            boiled_water -= 1;
            enemy_health_bar.setProgress(enemy_health);
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            enemy.startAnimation(shake);
            Storage();
            log.setText("You did 2 dmg! \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
            wood.putInt("enemy_health", enemy_health);
            wood.putInt("boiled_water", boiled_water);
            wood.apply();
            enemyDead();
            stab.setEnabled(false);
            stab_bar.setVisibility(View.VISIBLE);
            CountDownTimer stab_timer = new CountDownTimer(2000, 20) {
                int st = 0;

                @Override
                public void onTick(long millisUntilFinishedl) {
                    st++;
                    stab_bar.setProgress(st);

                }

                @Override
                public void onFinish() {
                    stab.setEnabled(true);
                    stab_bar.setVisibility(View.INVISIBLE);
                    st = 0;
                    stab_bar.setProgress(st);

                }
            };
            stab_timer.start();
        } else {
            stab.setEnabled(false);
        }

    }

    public void Swing(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        ProgressBar enemy_health_bar = (ProgressBar) findViewById(R.id.enemy_health_bar);
        final ProgressBar swing_bar = (ProgressBar) findViewById(R.id.swing_bar);
        final Button swing = (Button) findViewById(R.id.swing);
        TextView enemy = (TextView) findViewById(R.id.enemy);
        TextView log = (TextView) findViewById(R.id.quest_log);
        int enemy_health = sharedPref.getInt("enemy_health", 4);
        int rusty_sword = sharedPref.getInt("rusty_sword", 0);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        if (rusty_sword == 1) {
            enemy_health -= 2;
            log.setText("You did 2 dmg! \n" + log.getText());
        } else {
            enemy_health -= 1;
            log.setText("You did 1 dmg! \n" + log.getText());
        }
        enemy_health_bar.setProgress(enemy_health);
        Storage();

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        enemy.startAnimation(shake);
        SharedPreferences.Editor wood = sharedPref.edit();
        wood.putInt("enemy_health", enemy_health);
        wood.apply();
        enemyDead();
        swing.setEnabled(false);
        swing_bar.setVisibility(View.VISIBLE);
        swing_bar.setMax(50);
        CountDownTimer swing_timer = new CountDownTimer(1000, 1) {
            int s = 0;

            @Override
            public void onTick(long millisUntilFinishedl) {
                s++;
                swing_bar.setProgress(s);

            }

            @Override
            public void onFinish() {
                swing.setEnabled(true);
                swing_bar.setVisibility(View.INVISIBLE);
                s = 0;
                swing_bar.setProgress(s);

            }
        };
        swing_timer.start();
    }

    public void Strong(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        if (boiled_water >= 3) {
            ProgressBar enemy_health_bar = (ProgressBar) findViewById(R.id.enemy_health_bar);
            final ProgressBar strong_bar = (ProgressBar) findViewById(R.id.strong_bar);
            TextView enemy = (TextView) findViewById(R.id.enemy);
            final Button strong = (Button) findViewById(R.id.strong);
            TextView log = (TextView) findViewById(R.id.quest_log);

            int enemy_health = sharedPref.getInt("enemy_health", 4);
            enemy_health -= 3;
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            enemy.startAnimation(shake);
            boiled_water -= 3;
            enemy_health_bar.setProgress(enemy_health);
            Storage();
            log.setText("You did 3 dmg! \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
            wood.putInt("enemy_health", enemy_health);
            wood.putInt("boiled_water", boiled_water);
            wood.apply();
            enemyDead();
            strong.setEnabled(false);
            strong_bar.setVisibility(View.VISIBLE);
            CountDownTimer strong_timer = new CountDownTimer(4000, 40) {
                int str = 0;

                @Override
                public void onTick(long millisUntilFinishedl) {
                    str++;
                    strong_bar.setProgress(str);

                }

                @Override
                public void onFinish() {
                    strong.setEnabled(true);
                    strong_bar.setVisibility(View.INVISIBLE);
                    str = 0;
                    strong_bar.setProgress(str);

                }
            };
            strong_timer.start();
        } else {

        }
    }

    public void Storage() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        TextView storage = (TextView) findViewById(R.id.quest_storage);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        int cooked_food = sharedPref.getInt("cooked_food", 0);
        storage.setText("Storage:");
        storage.append("\nBoiled Water: " + boiled_water);
        storage.append("\nCooked Food: " + cooked_food);
    }

    public void enemyDead() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int enemy_health = sharedPref.getInt("enemy_health", 4);
        if (enemy_health <= 0) {
            TextView enemy = (TextView) findViewById(R.id.enemy);
            Animation fade = AnimationUtils.loadAnimation(this, R.anim.monster_fade);
            enemy.startAnimation(fade);
            RandomEnemy();
        }
    }

    public void playerDead() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int player_health = sharedPref.getInt("player_health", 20);
        if (player_health <= 0) {
            TextView player = (TextView) findViewById(R.id.player);
            Animation fade = AnimationUtils.loadAnimation(this, R.anim.monster_fade);
            player.startAnimation(fade);
            Intent i = new Intent(Sapling.this, Bosses.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

    public void enemyAttack() {
        Random rng = new Random();
        int attack = rng.nextInt(5) + 1; //Chance to attack every 5 seconds
        if (attack == 1) { //Add multiple attacks
            final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
            ProgressBar player_health_bar = (ProgressBar) findViewById(R.id.player_health_bar);
            TextView player = (TextView) findViewById(R.id.player);
            TextView log = (TextView) findViewById(R.id.quest_log);
            int player_health = sharedPref.getInt("player_health", 20);
            player_health -= 3; //Damage done
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            player.startAnimation(shake);
            player_health_bar.setProgress(player_health);
            playerDead();
            log.setText("You took 3 dmg! \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
            wood.putInt("player_health", player_health);
            wood.apply();
            CountDownTimer enemy_attack_timer = new CountDownTimer(5000, 50) {
                @Override
                public void onTick(long millisUntilFinishedl) {
                }

                @Override
                public void onFinish() {
                    enemyAttack();
                }
            };
            enemy_attack_timer.start();
        } else {
            CountDownTimer enemy_attack_timer = new CountDownTimer(5000, 50) {
                @Override
                public void onTick(long millisUntilFinishedl) {
                }

                @Override
                public void onFinish() {
                    enemyAttack();
                }
            };
            enemy_attack_timer.start();
        }

    }

    public void eat(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int cooked_food = sharedPref.getInt("cooked_food", 0);
        if (cooked_food >= 1) {
            ProgressBar player_health_bar = (ProgressBar) findViewById(R.id.player_health_bar);
            final ProgressBar eat_bar = (ProgressBar) findViewById(R.id.eat_bar);
            final Button eat = (Button) findViewById(R.id.eat);
            TextView enemy = (TextView) findViewById(R.id.enemy);
            TextView log = (TextView) findViewById(R.id.quest_log);
            int player_health = sharedPref.getInt("player_health", 5);
            player_health += 2;
            cooked_food -= 1;
            player_health_bar.setProgress(player_health);
            Storage();
            log.setText("You healed for 2 health. \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
            wood.putInt("player_health", player_health);
            wood.putInt("cooked_food", cooked_food);
            wood.apply();
            eat.setEnabled(false);
            eat_bar.setVisibility(View.VISIBLE);
            CountDownTimer eat_timer = new CountDownTimer(3000, 30) {
                int ea = 0;

                @Override
                public void onTick(long millisUntilFinishedl) {
                    ea++;
                    eat_bar.setProgress(ea);
                }

                @Override
                public void onFinish() {
                    eat.setEnabled(true);
                    eat_bar.setVisibility(View.INVISIBLE);
                    ea = 0;
                    eat_bar.setProgress(ea);

                }
            };
            eat_timer.start();
        }
    }

    private void RandomEnemy() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int enemycount_ft = sharedPref.getInt("enemycount_ft", 0);
        int player_health = sharedPref.getInt("player_health", 20);
        enemycount_ft++;
        if (enemycount_ft < 4) {
            Random rng = new Random();
            int enemy = rng.nextInt(3) + 1;
            if (enemy == 1) {
                Intent i = new Intent(Sapling.this, Acorn.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            } else if (enemy == 2) {
                Intent i = new Intent(Sapling.this, Sapling.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            } else if (enemy == 3) {
                Intent i = new Intent(Sapling.this, Branch.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        } else if (enemycount_ft == 4) {
            Intent i = new Intent(Sapling.this, Mother_Tree.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        }
        SharedPreferences.Editor wood = sharedPref.edit();
        wood.putInt("enemycount_ft", enemycount_ft);
        wood.putInt("player_health", player_health);
        wood.apply();


    }
}
