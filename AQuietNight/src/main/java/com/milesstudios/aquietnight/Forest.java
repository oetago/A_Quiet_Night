package com.milesstudios.aquietnight;

/**
 * Forest
 * Created by Ryanm14 on 7/14/2014.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Random;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;


public class Forest extends FragmentActivity {

    //Declare Statements
    int wood_counter, leaves_counter, stone_counter, w, l, s, d, f, stone_axeb, stone_pickb, hard_wood_counter, dirty_water_counter, food_counter;
    long wood_update, wood_remaining, sys_time, wood_remaining2;
    Button wood, stone, leaves, cave_button, dirty_water, hunt;
    TextView log, storage;
    ProgressBar wood_bar, leaves_bar, stone_bar, dirty_water_bar, hunt_bar;
    CountDownTimer wood_timer, leaves_timer, stone_timer, dirty_water_timer, hunt_timer;


    //For setting up back button
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Forest.this, Cave.class);
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

        //Acitivates Action Bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Decalres xml layout and id's and saving
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);


        //Declare Textviews
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);


        wood = (Button) findViewById(R.id.wood);
        stone = (Button) findViewById(R.id.stone);
        leaves = (Button) findViewById(R.id.leaves);
        dirty_water = (Button) findViewById(R.id.dirty_water);
        hunt = (Button) findViewById(R.id.hunt);

        //Declare and hide progress bars
        wood_bar = (ProgressBar) findViewById(R.id.wood_bar);
        leaves_bar = (ProgressBar) findViewById(R.id.leaves_bar);
        stone_bar = (ProgressBar) findViewById(R.id.stone_bar);
        hunt_bar = (ProgressBar) findViewById(R.id.hunt_bar);
        dirty_water_bar = (ProgressBar) findViewById(R.id.dirty_water_bar);

        //Setup TextSize and display Storage
        storage.setMovementMethod(new ScrollingMovementMethod());
        log.setTextSize(12);
        storage.setTextSize(15);

        final TextView cave_tab = (TextView) findViewById(R.id.cave_tab);
        final TextView forest_tab = (TextView) findViewById(R.id.forest_tab);
        cave_tab.setTextSize(20);
        forest_tab.setPaintFlags(forest_tab.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forest_tab.setTextSize(20);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/TNRB.ttf");
        cave_tab.setTypeface(tf);
        forest_tab.setTypeface(tf);

        //Gets crafting and buildings
        final int stone_axeb = sharedPref.getInt("stone_axe", 0);
        final int stone_pickb = sharedPref.getInt("stone_pick", 0);
        final int wood_counter = sharedPref.getInt("wood", 0);
        final int leaves_counter = sharedPref.getInt("leaves", 0);
        final int stone_counter = sharedPref.getInt("stone", 0);
        final int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        final int stone_sword_b = sharedPref.getInt("stone_sword", 0);
        final int leaf_canteen_b = sharedPref.getInt("leaf_canteen", 0);
        final int apple_counter = sharedPref.getInt("apples", 0);
        final String log_text = sharedPref.getString("log_text", "");
        final TextView cave_tab_wmine = (TextView) findViewById(R.id.cave_tab_wmine);
        final TextView forest_tab_wmine = (TextView) findViewById(R.id.forest_tab_wmine);
        final TextView mine_tab = (TextView) findViewById(R.id.mine_tab);
        Boolean rebuild_mine_b = sharedPref.getBoolean("mine", false);
        if (!rebuild_mine_b) {
            cave_tab_wmine.setEnabled(false);
            cave_tab_wmine.setVisibility(View.INVISIBLE);
            forest_tab_wmine.setEnabled(false);
            forest_tab_wmine.setVisibility(View.INVISIBLE);
            mine_tab.setEnabled(false);
            mine_tab.setVisibility(View.INVISIBLE);
        } else {
            cave_tab.setEnabled(false);
            cave_tab.setVisibility(View.INVISIBLE);
            forest_tab.setEnabled(false);
            forest_tab.setVisibility(View.INVISIBLE);
        }
        log.setText(log_text);
        updateText();


        //"Tab Button"
        cave_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCave = new Intent(Forest.this, Cave.class);
                startActivity(openCave);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }

        });

        if (leaf_canteen_b == 0) {
            dirty_water.setEnabled(false);
            dirty_water.setVisibility(View.INVISIBLE);
        }
        if (stone_sword_b == 0) {
            hunt.setEnabled(false);
            hunt.setVisibility(View.INVISIBLE);
            leaves.setEnabled(false);
            leaves.setVisibility(View.INVISIBLE);
        }
        mine_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForest = new Intent(Forest.this, Mine.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });
        cave_tab_wmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForest = new Intent(Forest.this, Cave.class);
                startActivity(openForest);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }

        });

    }


    @Override
    public void onPause() {
        if (w > 0) {
            wood_timer.cancel();
        }
        if (s > 0) {
            stone_timer.cancel();
        }
        if (l > 0) {
            leaves_timer.cancel();
        }
        if (d > 0) {
            dirty_water_timer.cancel();
        }
        if (f > 0) {
            hunt_timer.cancel();
        }
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Forest.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        updateBars();
        super.onStart();
    }

    //Update Bars
    public void updateText() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        int food_counter = sharedPref.getInt("food", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int apple_counter = sharedPref.getInt("apples", 0);
        int coin_counter = sharedPref.getInt("coins", 0);
        int copper_counter = sharedPref.getInt("copper", 0);
        int r_copper_counter = sharedPref.getInt("r_copper", 0);
        int coal_counter = sharedPref.getInt("coal", 0);

        storage.setText("\t Storage:");
        if (wood_counter >= 1) {
            storage.append("\n Wood: " + wood_counter);
        }
        if (leaves_counter >= 1) {
            storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            storage.append("\n Stone: " + stone_counter);
        }
        if (copper_counter >= 1) {
            storage.append("\n Raw Copper: " + copper_counter);
        }
        if (r_copper_counter >= 1) {
            storage.append("\n Refined Copper: " + r_copper_counter);
        }
        if (coal_counter >= 1) {
            storage.append("\n Coal: " + coal_counter);
        }
        if (dirty_water_counter >= 1) {
            storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if (food_counter >= 1) {
            storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if (cooked_food_counter >= 1) {
            storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if (boiled_water_counter >= 1) {
            storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        if (apple_counter >= 1) {
            storage.append("\n Apples: " + apple_counter);
        }


        if (coin_counter >= 1) {
            storage.append("\n \n \n Coins: " + coin_counter);
        }


    }

    public void updateBars() {
        updateLeaves();
        updateStone();
        updateWood();
        updateDirtyWater();
        updateHunt();
    }

    public void updateWood() {
        final long wood_remaining;
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long wood_systemtime = sharedPref.getLong("wood_systemtime", 0);
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        Boolean copper_axe_b = sharedPref.getBoolean("copper_axe", false);
        if (stone_axeb == 0 && !copper_axe_b) {
            wood_update = wood_systemtime + 15000;
            if (wood_update >= System.currentTimeMillis()) {
                wood.setEnabled(false);
                wood_bar.setVisibility(View.VISIBLE);
                wood_remaining = wood_update - System.currentTimeMillis();
                w = (((int) wood_remaining * -1 + 15000) / 150) - 1; //Equation -x + 15000 / 150
                wood_remaining2 = wood_remaining / (100 - w);
                wood_timer = new CountDownTimer(wood_remaining, wood_remaining2) {

                    @Override
                    public void onTick(long millisUntilFinishedw) {
                        // Log.w("Update", "Tick of Progress" + w + " " + millisUntilFinishedw);

                        w++;
                        wood_bar.setProgress(w);


                    }

                    @Override
                    public void onFinish() {
                        wood.setEnabled(true);
                        wood_bar.setVisibility(View.INVISIBLE);
                        w = 0;
                        wood_bar.setProgress(w);

                    }
                };
                wood_timer.start();
            } else {
                wood.setEnabled(true);
                wood_bar.setVisibility(View.INVISIBLE);

            }
        } else if (stone_axeb == 1 || copper_axe_b) {
            wood_update = wood_systemtime + 13000;
            if (wood_update >= System.currentTimeMillis()) {
                wood.setEnabled(false);
                wood_bar.setVisibility(View.VISIBLE);
                wood_remaining = wood_update - System.currentTimeMillis();
                w = (((int) wood_remaining * -1 + 13000) / 130) - 1; //Equation -x + 15000 / 150
                wood_remaining2 = wood_remaining / (100 - w);
                wood_timer = new CountDownTimer(wood_remaining, wood_remaining2) {

                    @Override
                    public void onTick(long millisUntilFinishedw) {
                        // Log.w("Update", "Tick of Progress" + w + " " + millisUntilFinishedw);

                        w++;
                        wood_bar.setProgress(w);


                    }

                    @Override
                    public void onFinish() {
                        wood.setEnabled(true);
                        wood_bar.setVisibility(View.INVISIBLE);
                        w = 0;
                        wood_bar.setProgress(w);

                    }
                };
                wood_timer.start();

            } else {
                wood.setEnabled(true);
                wood_bar.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void updateStone() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long stone_systemtime = sharedPref.getLong("stone_systemtime", 0);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        Boolean copper_pick_b = sharedPref.getBoolean("copper_pick", false);
        if (stone_pickb == 0 && !copper_pick_b) {
            long stone_update = stone_systemtime + 22000;
            if (stone_update >= System.currentTimeMillis()) {
                stone.setEnabled(false);
                stone_bar.setVisibility(View.VISIBLE);
                long stone_remaining = stone_update - System.currentTimeMillis();
                s = (((int) stone_remaining * -1 + 22000) / 220) - 1;
                long stone_remaining2 = stone_remaining / (100 - s);
                stone_timer = new CountDownTimer(stone_remaining, stone_remaining2) {

                    @Override
                    public void onTick(long millisUntilFinisheds) {
                        // Log.w("Update", "Tick of Progress" + w + " " + millisUntilFinishedw);

                        s++;
                        stone_bar.setProgress(s);


                    }

                    @Override
                    public void onFinish() {
                        stone.setEnabled(true);
                        stone_bar.setVisibility(View.INVISIBLE);
                        s = 0;
                        stone_bar.setProgress(s);

                    }
                };
                stone_timer.start();

            } else {
                stone.setEnabled(true);
                stone_bar.setVisibility(View.INVISIBLE);

            }
        } else if (stone_pickb == 1 || copper_pick_b) {
            long stone_update = stone_systemtime + 18000;
            if (stone_update >= System.currentTimeMillis()) {
                stone.setEnabled(false);
                stone_bar.setVisibility(View.VISIBLE);
                long stone_remaining = stone_update - System.currentTimeMillis();
                s = (((int) stone_remaining * -1 + 18000) / 180) - 1;
                long stone_remaining2 = stone_remaining / (100 - s);
                stone_timer = new CountDownTimer(stone_remaining, stone_remaining2) {

                    @Override
                    public void onTick(long millisUntilFinisheds) {
                        // Log.w("Update", "Tick of Progress" + w + " " + millisUntilFinishedw);

                        s++;
                        stone_bar.setProgress(s);


                    }

                    @Override
                    public void onFinish() {
                        stone.setEnabled(true);
                        stone_bar.setVisibility(View.INVISIBLE);
                        s = 0;
                        stone_bar.setProgress(s);

                    }
                };
                stone_timer.start();
            } else {
                stone.setEnabled(true);
                stone_bar.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void updateLeaves() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long leaves_systemtime = sharedPref.getLong("leaves_systemtime", 0);
        long leaves_update = leaves_systemtime + 9000;
        if (leaves_update >= System.currentTimeMillis()) {
            leaves.setEnabled(false);
            leaves_bar.setVisibility(View.VISIBLE);
            long leaves_remaining = leaves_update - System.currentTimeMillis();
            l = (((int) leaves_remaining * -1 + 9000) / 90) - 1;
            long leaves_remaining2 = leaves_remaining / (100 - l);
            leaves_timer = new CountDownTimer(leaves_remaining, leaves_remaining2) {

                @Override
                public void onTick(long millisUntilFinishedl) {
                    // Log.w("Update", "Tick of Progress" + w + " " + millisUntilFinishedw);

                    l++;
                    leaves_bar.setProgress(l);


                }

                @Override
                public void onFinish() {
                    leaves.setEnabled(true);
                    leaves_bar.setVisibility(View.INVISIBLE);
                    l = 0;
                    leaves_bar.setProgress(s);

                }
            };
            leaves_timer.start();

        } else {
            leaves.setEnabled(true);
            leaves_bar.setVisibility(View.INVISIBLE);

        }
    }

    public void updateDirtyWater() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long dirty_water_systemtime = sharedPref.getLong("dirty_water_systemtime", 0);
        long dirty_water_update = dirty_water_systemtime + 9000;
        if (dirty_water_update >= System.currentTimeMillis()) {
            dirty_water.setEnabled(false);
            dirty_water_bar.setVisibility(View.VISIBLE);
            long dirty_water_remaining = dirty_water_update - System.currentTimeMillis();
            d = (((int) dirty_water_remaining * -1 + 9000) / 90) - 1;
            long dirty_water_remaining2 = dirty_water_remaining / (100 - l);
            dirty_water_timer = new CountDownTimer(dirty_water_remaining, dirty_water_remaining2) {

                @Override
                public void onTick(long millisUntilFinishedd) {
                    d++;
                    dirty_water_bar.setProgress(d);


                }

                @Override
                public void onFinish() {
                    dirty_water.setEnabled(true);
                    dirty_water_bar.setVisibility(View.INVISIBLE);
                    d = 0;
                    dirty_water_bar.setProgress(s);

                }
            };
            dirty_water_timer.start();

        } else {
            dirty_water.setEnabled(true);
            dirty_water_bar.setVisibility(View.INVISIBLE);

        }
    }

    public void updateHunt() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        long food_systemtime = sharedPref.getLong("food_systemtime", 0);
        long hunt_update = food_systemtime + 25000;
        if (hunt_update >= System.currentTimeMillis()) {
            hunt.setEnabled(false);
            hunt_bar.setVisibility(View.VISIBLE);
            long food_remaining = hunt_update - System.currentTimeMillis();
            f = (((int) food_remaining * -1 + 25000) / 250) - 1;
            long food_remaining2 = food_remaining / (100 - f);
            hunt_timer = new CountDownTimer(food_remaining, food_remaining2) {

                @Override
                public void onTick(long millisUntilFinishedf) {
                    f++;
                    hunt_bar.setProgress(f);


                }

                @Override
                public void onFinish() {
                    hunt.setEnabled(true);
                    hunt_bar.setVisibility(View.INVISIBLE);
                    f = 0;
                    hunt_bar.setProgress(f);

                }
            };
            hunt_timer.start();

        } else {
            hunt.setEnabled(true);
            hunt_bar.setVisibility(View.INVISIBLE);

        }
    }

    //Buttons
    public void buttonWood(View v) {
        int apple = 0;
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Random rng = new Random();
        int stone_axeb = sharedPref.getInt("stone_axe", 0);
        Boolean copper_axe_b = sharedPref.getBoolean("copper_axe", false);

        if (stone_axeb == 0 && !copper_axe_b) {
            int wood_counter = sharedPref.getInt("wood", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            wood_counter = sharedPref.getInt("wood", 0);
            log.setText(" You gathered 1 wood from the ground \n" + log.getText());
            wood_counter += 1;

            editor = sharedPref.edit();
            editor.putInt("wood", wood_counter);
            editor.putLong("wood_systemtime", System.currentTimeMillis());
            editor.apply();
            updateText();
            apple = rng.nextInt(101);
            if (apple <= 20) {
                int apple_counter = sharedPref.getInt("apples", 0);
                apple_counter += 1;
                editor = sharedPref.edit();
                editor.putInt("apples", apple_counter);
                editor.apply();
                log.setText(" You found an Apple! \n" + log.getText());
                updateText();

            }

            wood_bar.setVisibility(View.VISIBLE);
            wood.setEnabled(false);
            wood_timer = new CountDownTimer(15000, 150) {

                @Override
                public void onTick(long millisUntilFinishedw) {
                    //  Log.v("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedw / 1000);
                    w++;
                    wood_bar.setProgress(w);

                }

                @Override
                public void onFinish() {
                    wood.setEnabled(true);
                    wood_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    w = 0;
                    wood_bar.setProgress(w);

                }
            };
            wood_timer.start();
        } else if (stone_axeb == 1 && !copper_axe_b) {
            int wood_counter = sharedPref.getInt("wood", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            wood_counter = sharedPref.getInt("wood", 0);
            log.setText(" You gathered 2 wood from the ground \n" + log.getText());
            wood_counter += 2;

            apple = rng.nextInt(101);
            if (apple <= 35) {
                int apple_counter = sharedPref.getInt("apples", 0);
                apple_counter += 1;
                editor = sharedPref.edit();
                editor.putInt("apples", apple_counter);
                editor.apply();
                log.setText(" You found an Apple! \n" + log.getText());
                updateText();

            }

            //Save counter
            editor = sharedPref.edit();
            editor.putInt("wood", wood_counter);
            editor.putLong("wood_systemtime", System.currentTimeMillis());
            editor.apply();
            updateText();

            wood_bar.setVisibility(View.VISIBLE);
            wood.setEnabled(false);
            wood_timer = new CountDownTimer(13000, 130) {

                @Override
                public void onTick(long millisUntilFinishedw) {
                    Log.w("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedw);
                    w++;
                    wood_bar.setProgress(w);

                }

                @Override
                public void onFinish() {
                    wood.setEnabled(true);
                    wood_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    w = 0;
                    wood_bar.setProgress(w);

                }
            };
            wood_timer.start();
        } else if (copper_axe_b) {
            int wood_counter = sharedPref.getInt("wood", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            wood_counter = sharedPref.getInt("wood", 0);
            log.setText(" You gathered 3 wood from the ground \n" + log.getText());
            wood_counter += 3;

            apple = rng.nextInt(101);
            if (apple <= 35) {
                int apple_counter = sharedPref.getInt("apples", 0);
                apple_counter += 2;
                editor = sharedPref.edit();
                editor.putInt("apples", apple_counter);
                editor.apply();
                log.setText(" You found two Apples! \n" + log.getText());
                updateText();

            }

            //Save counter
            editor = sharedPref.edit();
            editor.putInt("wood", wood_counter);
            editor.putLong("wood_systemtime", System.currentTimeMillis());
            editor.apply();
            updateText();

            wood_bar.setVisibility(View.VISIBLE);
            wood.setEnabled(false);
            wood_timer = new CountDownTimer(13000, 130) {

                @Override
                public void onTick(long millisUntilFinishedw) {
                    Log.w("Log_tag", "Tick of Progress" + w + " " + millisUntilFinishedw);
                    w++;
                    wood_bar.setProgress(w);

                }

                @Override
                public void onFinish() {
                    wood.setEnabled(true);
                    wood_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    w = 0;
                    wood_bar.setProgress(w);

                }
            };
            wood_timer.start();
        }


    }

    public void buttonStone(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int stone_pickb = sharedPref.getInt("stone_pick", 0);
        Boolean copper_pick_b = sharedPref.getBoolean("copper_pick", false);
        if (stone_pickb == 0 && !copper_pick_b) {
            int stone_counter = sharedPref.getInt("stone", 0);
            log.setText(" You gathered 1 stone from the ground \n" + log.getText());
            stone_counter += 1;
            //Save counter
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("stone", stone_counter);
            editor.apply();
            editor.putLong("stone_systemtime", System.currentTimeMillis());
            editor.apply();
            stone_bar.setVisibility(View.VISIBLE);
            stone.setEnabled(false);
            updateText();

            stone_timer = new CountDownTimer(22000, 220) {


                @Override
                public void onTick(long millisUntilFinishedl) {
                    Log.v("Log_tag", "Tick of Progress" + l + " " + millisUntilFinishedl);
                    s++;
                    stone_bar.setProgress(s);

                }

                @Override
                public void onFinish() {
                    stone.setEnabled(true);
                    stone_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    s = 0;
                    stone_bar.setProgress(s);

                }
            };
            stone_timer.start();


        } else if (stone_pickb == 1 && !copper_pick_b) {
            int stone_counter = sharedPref.getInt("stone", 0);
            log.setText(" You gathered 2 stones from the ground \n" + log.getText());
            stone_counter += 2;
            //Save counter
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("stone", stone_counter);
            editor.apply();
            editor.putLong("stone_systemtime", System.currentTimeMillis());
            editor.apply();
            stone_bar.setVisibility(View.VISIBLE);
            stone.setEnabled(false);
            updateText();
            stone_timer = new CountDownTimer(18000, 180) {

                @Override

                public void onTick(long millisUntilFinisheds) {
                    Log.v("Log_tag", "Tick of Progress" + s + " " + millisUntilFinisheds);
                    s++;
                    stone_bar.setProgress(s);

                }

                @Override
                public void onFinish() {
                    stone.setEnabled(true);
                    stone_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    s = 0;
                    stone_bar.setProgress(s);

                }
            };
            stone_timer.start();

        } else if (copper_pick_b) {
            int stone_counter = sharedPref.getInt("stone", 0);
            log.setText(" You gathered 3 stones from the ground \n" + log.getText());
            stone_counter += 3;

            //Save counter
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("stone", stone_counter);
            editor.putLong("stone_systemtime", System.currentTimeMillis());
            editor.apply();
            stone_bar.setVisibility(View.VISIBLE);
            stone.setEnabled(false);
            updateText();

            stone_timer = new CountDownTimer(18000, 180) {

                @Override

                public void onTick(long millisUntilFinisheds) {
                    Log.v("Log_tag", "Tick of Progress" + s + " " + millisUntilFinisheds);
                    s++;
                    stone_bar.setProgress(s);

                }

                @Override
                public void onFinish() {
                    stone.setEnabled(true);
                    stone_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    s = 0;
                    stone_bar.setProgress(s);

                }
            };
            stone_timer.start();

        }
    }

    public void buttonLeaves(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        log.setText(" You gathered 1 leaf! \n" + log.getText());
        // StartAnimations();
        leaves_counter += 1;
        leaves_bar.setVisibility(View.VISIBLE);
        leaves.setEnabled(false);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("leaves", leaves_counter);
        editor.apply();
        editor.putLong("leaves_systemtime", System.currentTimeMillis());
        editor.apply();
        updateText();
        leaves_timer = new CountDownTimer(9000, 90) {


            @Override
            public void onTick(long millisUntilFinishedl) {
                Log.v("Log_tag", "Tick of Progress" + l + " " + millisUntilFinishedl);
                l++;
                leaves_bar.setProgress(l);
            }

            @Override
            public void onFinish() {
                leaves.setEnabled(true);
                leaves_bar.setVisibility(View.INVISIBLE);

                //Do what you want
                l = 0;
                leaves_bar.setProgress(l);

            }
        };
        leaves_timer.start();


    }

    public void buttonDirtyWater(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        if (dirty_water_counter <= 20) {

            int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
            log.append("\n You gathered 1 Liter of dirty water");
            dirty_water_counter += 1;
            dirty_water_bar.setVisibility(View.VISIBLE);
            dirty_water.setEnabled(false);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("dirty_water", dirty_water_counter);
            editor.apply();
            editor.putLong("dirty_water_systemtime", System.currentTimeMillis());
            editor.apply();
            updateText();
            dirty_water_timer = new CountDownTimer(13000, 130) {


                @Override
                public void onTick(long millisUntilFinishedu) {

                    d++;
                    dirty_water_bar.setProgress(d);
                }

                @Override
                public void onFinish() {
                    dirty_water.setEnabled(true);
                    dirty_water_bar.setVisibility(View.INVISIBLE);

                    //Do what you want
                    d = 0;
                    dirty_water_bar.setProgress(d);

                }
            };
            dirty_water_timer.start();


        } else {
            log.append("You can't hold any more water!");
        }
    }

    public void buttonHunt(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int rusty_sword_b = sharedPref.getInt("rusty_sword", 0);
        if (food_counter < 20) {
            if (rusty_sword_b == 0) {
                int food_counter = sharedPref.getInt("food", 0);
                log.setText(" You gathered 1Lb of Food! \n" + log.getText());
                food_counter += 1;
                hunt_bar.setVisibility(View.VISIBLE);
                hunt.setEnabled(false);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("food", food_counter);
                editor.apply();
                editor.putLong("food_systemtime", System.currentTimeMillis());
                editor.apply();
                updateText();
                hunt_timer = new CountDownTimer(25000, 250) {


                    @Override
                    public void onTick(long millisUntilFinishedf) {


                        f++;
                        hunt_bar.setProgress(f);
                    }

                    @Override
                    public void onFinish() {
                        hunt.setEnabled(true);
                        hunt_bar.setVisibility(View.INVISIBLE);

                        //Do what you want
                        f = 0;
                        hunt_bar.setProgress(f);

                    }
                };
                hunt_timer.start();

        } else if (food_counter < 19 && rusty_sword_b == 1) {
                int food_counter = sharedPref.getInt("food", 0);
                log.setText(" You gathered 2Lbs of Food! \n" + log.getText());
                food_counter += 2;
                hunt_bar.setVisibility(View.VISIBLE);
                hunt.setEnabled(false);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("food", food_counter);
                editor.apply();
                editor.putLong("food_systemtime", System.currentTimeMillis());
                editor.apply();
                updateText();
                hunt_timer = new CountDownTimer(25000, 250) {


                    @Override
                    public void onTick(long millisUntilFinishedf) {


                        f++;
                        hunt_bar.setProgress(f);
                    }

                    @Override
                    public void onFinish() {
                        hunt.setEnabled(true);
                        hunt_bar.setVisibility(View.INVISIBLE);

                        //Do what you want
                        f = 0;
                        hunt_bar.setProgress(f);

                    }
                };
                hunt_timer.start();

            } else {
                log.setText(" You can't hold anymore Food! \n" + log.getText());
            }
        } else {
            log.setText(" You can't hold anymore Food! \n" + log.getText());
        }

    }
}





