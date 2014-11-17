package com.milesstudios.aquietnight.quest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.milesstudios.aquietnight.Cave;
import com.milesstudios.aquietnight.R;

/**
 * Created by Ryanm14 on 10/15/2014.
 */
public class Quest_Main extends Activity {
Button storage,wood_chopper,apple_collector,leaf_collector,hunter,purifier;
int wood_counter,apple_counter,coin_counter;
Boolean purifier_b,hunter_b,apple_collector_b,wood_chopper_b,leaf_collector_b;
TextView log;
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Quest_Main.this, Cave.class);
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
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        // Adapter
        SpinnerAdapter adapter =
                ArrayAdapter.createFromResource(this, R.array.quest,
                        android.R.layout.simple_spinner_dropdown_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.quest); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {

                if (items[position].equals("Bosses")) {
                    Intent openBosses = new Intent(Quest_Main.this, Bosses.class);
                    startActivity(openBosses);
                }

                Log.d("NavigationItemSelected", items[position]);
                return true;

            }

        };

// Action Bar
        ActionBar actions = getActionBar();
        actions.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actions.setDisplayShowTitleEnabled(false);
        actions.setListNavigationCallbacks(adapter, callback);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_main);
        final TextView log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        final Button apple_collector = (Button) findViewById(R.id.quest_apple_collector);
        final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chopper);
        hunter = (Button) findViewById(R.id.hunter);
        purifier = (Button) findViewById(R.id.purifier);
        leaf_collector = (Button) findViewById(R.id.leaf_collector);
        //Saving
        final int wood_counter = sharedPref.getInt("wood", 0);
         hunter_b = sharedPref.getBoolean("hunter", false);
         purifier_b = sharedPref.getBoolean("purifier", false);
         wood_chopper_b = sharedPref.getBoolean("wood_chopper", false);
         apple_collector_b = sharedPref.getBoolean("apple_collector", false);
         leaf_collector_b = sharedPref.getBoolean("leaf_collector", false);
         int hard_wood_counter= sharedPref.getInt("hard_wood", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);
        int water_canteen_b = sharedPref.getInt("water_canteen", 0);

        if(stone_sword_b == 0){
            hunter.setVisibility(View.INVISIBLE);
            hunter.setEnabled(false);
            leaf_collector.setVisibility(View.INVISIBLE);
            leaf_collector.setEnabled(false);

        }
        if(wood_chopper_b){
            wood_chopper.setVisibility(View.INVISIBLE);
            wood_chopper.setEnabled(false);
        }
        if(apple_collector_b){
            apple_collector.setVisibility(View.INVISIBLE);
            apple_collector.setEnabled(false);
        }
        if(leaf_collector_b){
            leaf_collector.setVisibility(View.INVISIBLE);
            leaf_collector.setEnabled(false);
        }
        if(hunter_b){
            hunter.setVisibility(View.INVISIBLE);
            hunter.setEnabled(false);
        }
        if(purifier_b){
            purifier.setVisibility(View.INVISIBLE);
            purifier.setEnabled(false);
        }
        if(water_canteen_b == 0){
            purifier.setVisibility(View.INVISIBLE);
            purifier.setEnabled(false);
        }
        //UpdateText();


    }

     @Override
     public void onBackPressed() {
                Intent openMain = new Intent(Quest_Main.this, Cave.class);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }
    @Override
    public void onPause(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
       // String log_text = log.getText().toString();
       // editor.putString("log_text",log_text);
        //editor.apply();
        super.onPause();
    }

    public void UpdateText() {
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
        int copper_counter = sharedPref.getInt("copper",0);
        int r_copper_counter = sharedPref.getInt("r_copper",0);
        int coal_counter = sharedPref.getInt("coal",0);

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

    public void buttonWoodChopper(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
                final Dialog dialog = new Dialog(Quest_Main.this);
                dialog.setContentView(R.layout.quests_dialog);

                TextView title = (TextView) dialog.findViewById(R.id.title);
                TextView desc = (TextView) dialog.findViewById(R.id.desc);
                Button accept = (Button) dialog.findViewById(R.id.accept);
                Button decline = (Button) dialog.findViewById(R.id.decline);
                final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chopper);
                final TextView log = (TextView) findViewById(R.id.log);
                title.setText("Quest: Wood Chopper");
                desc.setText("Hello! I seem to have misplaced my wood can you go chop some for me? I will glady pay you\n\n Needs: 7 Wood\n Reward: 3 Coins");
                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }

                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int coin_counter = sharedPref.getInt("coins", 0);
                        int wood_counter = sharedPref.getInt("wood", 0);
                        if (wood_counter >= 7) {
                            log.append("\n You earned 3 Coins!");
                            wood_counter -= 7;
                            coin_counter += 3;
                            wood_chopper.setEnabled(false);
                            wood_chopper.setVisibility(View.INVISIBLE);
                            wood_chopper_b = true;
                            SharedPreferences.Editor wood = sharedPref.edit();
                            wood.putInt("coins",coin_counter);
                            wood.putBoolean("wood_chopper",wood_chopper_b);
                            wood.putInt("wood", wood_counter);
                            wood.apply();
                        } else {
                            log.append("\n You don't have enough resources!");
                        }
                        dialog.dismiss();
                    }
                });
                 dialog.show();


            }
    public void buttonAppleCollector(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(Quest_Main.this);
        final TextView log = (TextView) findViewById(R.id.log);

        dialog.setContentView(R.layout.quests_dialog);
        final Button apple_collector = (Button) findViewById(R.id.quest_apple_collector);
        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView desc = (TextView) dialog.findViewById(R.id.desc);
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button decline = (Button) dialog.findViewById(R.id.decline);
        title.setText("Quest: Apple Collector");
        desc.setText("Hello! I seem to have misplaced my apples can you go chop some for me? I will glady pay you\n\n Needs: 3 Apples\n Reward: 6 Coins");
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int apple_counter = sharedPref.getInt("apples", 0);
                int coin_counter = sharedPref.getInt("coins", 0);
                if (apple_counter >= 3) {
                    log.append("\n You earned 6 Coins!");
                    apple_counter -= 3;
                    coin_counter += 6;
                    apple_collector.setEnabled(false);
                    apple_collector.setVisibility(View.INVISIBLE);
                    apple_collector_b = true;
                    SharedPreferences.Editor wood = sharedPref.edit();
                    wood.putBoolean("apple_collector", apple_collector_b);
                    wood.putInt("coins",coin_counter);
                    wood.putInt("apples", apple_counter);
                    wood.apply();
                } else {
                    log.append("\n You don't have enough resources!");
                }
                dialog.dismiss();
            }
        });
        dialog.show();


    }
    public void buttonHunter(View v){
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(Quest_Main.this);
        dialog.setContentView(R.layout.quests_dialog);

        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView desc = (TextView) dialog.findViewById(R.id.desc);
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button decline = (Button) dialog.findViewById(R.id.decline);
        final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chopper);
        final TextView log = (TextView) findViewById(R.id.log);
        title.setText("Quest: Hunter");
        desc.setText("I lost all my meat to the wolves, and my family are starving can you help me?\n\n Needs: 5 Cooked Meat\n Reward: 6 Coins");
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int coin_counter = sharedPref.getInt("coins", 0);
                int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
                if (cooked_food_counter >= 5) {
                    log.append("\n You earned 6 Coins!");
                    cooked_food_counter -= 5;
                    coin_counter += 6;
                    hunter.setEnabled(false);
                    hunter.setVisibility(View.INVISIBLE);
                    hunter_b = true;
                    SharedPreferences.Editor wood = sharedPref.edit();
                    wood.putInt("coins",coin_counter);
                    wood.putBoolean("hunter", hunter_b);
                    wood.putInt("cooked_food", cooked_food_counter);
                    wood.apply();
                   // UpdateText();
                } else {
                    log.append("\n You don't have enough resources!");
                }
                dialog.dismiss();
            }
        });
        dialog.show();


    }
    public void buttonLeafCollector(View v){
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(Quest_Main.this);
        dialog.setContentView(R.layout.quests_dialog);

        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView desc = (TextView) dialog.findViewById(R.id.desc);
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button decline = (Button) dialog.findViewById(R.id.decline);
        final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chopper);
        final TextView log = (TextView) findViewById(R.id.log);
        title.setText("Quest: Leaf Collector");
        desc.setText("I only need 4 more leaves to complete my project, but I am tired can you get them for me?\n\n Needs: 4 Leaves\n Reward: 2 Coins");
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int coin_counter = sharedPref.getInt("coins", 0);
                int leaves_counter = sharedPref.getInt("leaves", 0);
                if (leaves_counter >= 4) {
                    log.append("\n You earned 6 Coins!");
                    leaves_counter -= 4;
                    coin_counter += 2;
                    leaf_collector_b = true;
                    //TODO Crashes
                    leaf_collector.setEnabled(false);
                    leaf_collector.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor wood = sharedPref.edit();
                    wood.putInt("coins", coin_counter);
                    wood.putInt("leaves", leaves_counter);
                    wood.putBoolean("leaf_collector",leaf_collector_b);
                    wood.apply();
                } else {
                    log.append("\n You don't have enough resources!");
                }
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public void buttonPurifier(View v){
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(Quest_Main.this);
        dialog.setContentView(R.layout.quests_dialog);

        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView desc = (TextView) dialog.findViewById(R.id.desc);
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button decline = (Button) dialog.findViewById(R.id.decline);
        final Button wood_chopper = (Button) findViewById(R.id.quest_wood_chopper);
        final TextView log = (TextView) findViewById(R.id.log);
        title.setText("Quest: Purifier");
        desc.setText("Someone took my clean water can you bring me 6L of clean water?\n\n Needs: 6L of Clean Water\n Reward: 6 Coins");
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int coin_counter = sharedPref.getInt("coins", 0);
                int clean_water_counter = sharedPref.getInt("clean_water",0);
                if (clean_water_counter >= 6) {
                    log.append("\n You earned 6 Coins!");
                    clean_water_counter -= 6;
                    coin_counter += 6;
                    purifier.setEnabled(false);
                    purifier_b = true;
                    purifier.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor wood = sharedPref.edit();
                    wood.putInt("coins",coin_counter);
                    wood.putInt("clean_water", clean_water_counter);
                    wood.putBoolean("purifier", purifier_b);
                    wood.apply();
                } else {
                    log.append("\n You don't have enough resources!");
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }



}

