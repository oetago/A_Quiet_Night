package com.milesstudios.aquietnight.quest.forest_temple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.quest.Bosses;
import com.milesstudios.aquietnight.quest.Loading;

import java.util.Random;

/**
 * Created by Ryan on 2/22/2015.
 */
public class Boss_Helper extends Activity{
    Context context;
    private int enemy_health;
    private int player_health;
    private int player_health_max;
    private int enemy_health_max;

    public Boss_Helper(Context context, int p, int e) {
        this.context = context;
        enemy_health_max = e;
        player_health = p;
    }
    public void setup(Context context){
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        ProgressBar player_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.player_health_bar);
        ProgressBar enemy_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.enemy_health_bar);
        TextView enemy = (TextView) ((Activity) context).findViewById(R.id.enemy);
        Button stab = (Button) ((Activity) context).findViewById(R.id.stab);
        Button strong = (Button) ((Activity) context).findViewById(R.id.strong);
        if (sharedPref.getBoolean("chain_armor",false)) {
            player_health_bar.setMax(20);
        } else if (sharedPref.getBoolean("leaf_armor",false)) {
            player_health_bar.setMax(15);
        } else {
            player_health_bar.setMax(8);
        }
        player_health_bar.setProgress(player_health);
        enemy_health_bar.setMax(enemy_health_max);
        enemy_health_bar.setProgress(enemy_health_max);
        enemy.setText("A");
        if (sharedPref.getBoolean("rusty_sword",false)) { //Fix later
            strong.setEnabled(false);
        }
        if (sharedPref.getInt("boiled_water",0)> 0) {
            stab.setEnabled(false);
        }
    }
    public void storage() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        TextView storage = (TextView) ((Activity) context).findViewById(R.id.quest_storage);
        storage.setText("Storage:");
        storage.append("\nBoiled Water: " + sharedPref.getInt("boiled_water", 0));
        storage.append("\nCooked Food: " + sharedPref.getInt("cooked_food", 0));
    }

    public void enemyAttack() {
        Random rng = new Random();
        int attack = rng.nextInt(5) + 1;
        if (attack == 1) {
            final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
            ProgressBar player_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.player_health_bar);
            TextView player = (TextView) ((Activity) context).findViewById(R.id.player);
            TextView log = (TextView) ((Activity) context).findViewById(R.id.quest_log);
            player_health -= 1;
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            player.startAnimation(shake);
            player_health_bar.setProgress(player_health);
            playerDead();
            log.setText("You took 1 dmg! \n" + log.getText());
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
    public void stab() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Button stab = (Button) ((Activity) context).findViewById(R.id.stab);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        if (boiled_water >= 1) {
            ProgressBar enemy_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.enemy_health_bar);
            final ProgressBar stab_bar = (ProgressBar) ((Activity) context).findViewById(R.id.stab_bar);
            TextView enemy = (TextView) ((Activity) context).findViewById(R.id.enemy);
            TextView log = (TextView) ((Activity) context).findViewById(R.id.quest_log);
            enemy_health -= 2;
            boiled_water -= 1;
            enemy_health_bar.setProgress(enemy_health);
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            enemy.startAnimation(shake);
            storage();
            log.setText("You did 2 dmg! \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
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
    public void swing() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        ProgressBar enemy_health_bar = (ProgressBar)  ((Activity) context).findViewById(R.id.enemy_health_bar);
        final ProgressBar swing_bar = (ProgressBar)  ((Activity) context).findViewById(R.id.swing_bar);
        final Button swing = (Button) ((Activity) context).findViewById(R.id.swing);
        TextView enemy = (TextView)  ((Activity) context).findViewById(R.id.enemy);
        TextView log = (TextView)  ((Activity) context).findViewById(R.id.quest_log);
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
        storage();
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        enemy.startAnimation(shake);
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

    public void strong() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int boiled_water = sharedPref.getInt("boiled_water", 0);
        if (boiled_water >= 3) {
            ProgressBar enemy_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.enemy_health_bar);
            final ProgressBar strong_bar = (ProgressBar) ((Activity) context).findViewById(R.id.strong_bar);
            TextView enemy = (TextView) ((Activity) context).findViewById(R.id.enemy);
            final Button strong = (Button) ((Activity) context).findViewById(R.id.strong);
            TextView log = (TextView) ((Activity) context).findViewById(R.id.quest_log);
            enemy_health -= 3;
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            enemy.startAnimation(shake);
            boiled_water -= 3;
            enemy_health_bar.setProgress(enemy_health);
            storage();
            log.setText("You did 3 dmg! \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
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
        }
    }
    public void enemyDead() {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        if (enemy_health <= 0) {
            TextView enemy = (TextView) ((Activity) context).findViewById(R.id.enemy);
            Animation fade =  AnimationUtils.loadAnimation(this, R.anim.monster_fade);
            enemy.startAnimation(fade);
            editor.putInt("player_health", player_health);
            editor.apply();
            Loading loading = new Loading();
            loading.bossesLoading("Forest Temple",4);
        }
    }
    public void eat() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int cooked_food = sharedPref.getInt("cooked_food", 0);
        if (cooked_food >= 1) {
            ProgressBar player_health_bar = (ProgressBar) ((Activity) context).findViewById(R.id.player_health_bar);
            final ProgressBar eat_bar = (ProgressBar) ((Activity) context).findViewById(R.id.eat_bar);
            final Button eat = (Button) ((Activity) context).findViewById(R.id.eat);
            TextView log = (TextView) ((Activity) context).findViewById(R.id.quest_log);
            player_health += 2;
            cooked_food -= 1;
            player_health_bar.setProgress(player_health);
            storage();
            log.setText("You healed for 2 health. \n" + log.getText());
            SharedPreferences.Editor wood = sharedPref.edit();
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
    public void playerDead() {
        if (player_health <= 0) {
            TextView player = (TextView) ((Activity) context).findViewById(R.id.player);
            Animation fade = AnimationUtils.loadAnimation(this, R.anim.monster_fade);
            player.startAnimation(fade);
            Intent i = new Intent(Boss_Helper.this, Bosses.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

}
