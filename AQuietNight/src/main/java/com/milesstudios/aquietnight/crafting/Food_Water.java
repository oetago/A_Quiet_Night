package com.milesstudios.aquietnight.crafting;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
 * Created by Ryan on 9/27/2014.
 */
public class Food_Water extends ActivityGroup {
    int wood_counter, leaves_counter, stone_counter, stone_axeb, stone_pickb, hard_wood_counter, workshop_b, boiled_water_counter, cooked_food_counter, leaf_canteen_b;
    Button boil_water, cook_food, leaf_canteen;
    TextView log, storage;

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Food_Water.this, Cave.class);
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
                ArrayAdapter.createFromResource(this, R.array.food_water,
                        R.layout.spinner_item);

// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.food_water); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Food and Water")) {
                }
                if (items[position].equals("Weapons and Armor")) {
                    Intent openWepaons_Armor = new Intent(Food_Water.this, Weapons_Armor.class);
                    startActivity(openWepaons_Armor);
                }
                if (items[position].equals("Tools")) {
                    setContentView(R.layout.crafting_tools);
                    Intent openTools = new Intent(Food_Water.this, Tools.class);
                    startActivity(openTools);
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
        setContentView(R.layout.crafting_food_water);
        log = (TextView) findViewById(R.id.log);
        storage = (TextView) findViewById(R.id.storage);
        boil_water = (Button) findViewById(R.id.boil_water);
        cook_food = (Button) findViewById(R.id.cook_food);
        final Button leaf_canteen = (Button) findViewById(R.id.leaf_canteen);
        log.setTextSize(11);
        storage.setTextSize(15);
        //Saving
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int workshop_b = sharedPref.getInt("workshop", 0);
        int fireplace_b = sharedPref.getInt("fireplace", 0);
        int leaf_canteen_b = sharedPref.getInt("leaf_canteen", 0);
        int stone_sword_b = sharedPref.getInt("stone_sword", 0);


        if (fireplace_b == 1 && stone_sword_b == 1) {
            cook_food.setEnabled(true);
            cook_food.setVisibility(View.VISIBLE);
        } else {
            cook_food.setEnabled(false);
            cook_food.setVisibility(View.INVISIBLE);
        }
        if (fireplace_b == 0 || leaf_canteen_b == 0) {
            boil_water.setEnabled(false);
            boil_water.setVisibility(View.INVISIBLE);
        }
        if (stone_sword_b == 0) {
            leaf_canteen.setEnabled(false);
            leaf_canteen.setVisibility(View.INVISIBLE);
        }
        if (leaf_canteen_b == 1) {
            leaf_canteen.setEnabled(false);
            leaf_canteen.setVisibility(View.INVISIBLE);
        }
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        updateText();

        //Saving Tab
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 1;
        int tools = 0;
        int weapons_armor = 0;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
    }

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

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Food_Water.this, Cave.class);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        super.onPause();
    }

    //Buttons
    public void buttonBoilWater(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Food_Water.this);
        alertDialog.setTitle("Boil Water");
        alertDialog.setMessage("Wood: 1 (Per L)");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setNeutralButton("Boil 1L", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
                int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
                if (wood_counter >= 1 && dirty_water_counter >= 1) {
                    log.setText(" You boiled 1L of Water! \n" + log.getText());
                    wood_counter -= 1;
                    boiled_water_counter += 1;
                    dirty_water_counter -= 1;
                } else {
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("boiled_water", boiled_water_counter);
                editor.putInt("dirty_water", dirty_water_counter);
                editor.apply();
                updateText();
            }

        });
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        alertDialog.setPositiveButton("Boil All (" + dirty_water_counter + " L)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
                int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
                if (dirty_water_counter <= wood_counter) {
                    log.setText(" You boiled " + dirty_water_counter + " L of Water! \n" + log.getText());
                    wood_counter -= dirty_water_counter;
                    boiled_water_counter += dirty_water_counter;
                    dirty_water_counter = 0;
                } else {
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("boiled_water", boiled_water_counter);
                editor.putInt("dirty_water", dirty_water_counter);
                editor.apply();
                updateText();
            }

        });
        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    public void buttonCookFood(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Food_Water.this);
        alertDialog.setTitle("Cook Food");
        alertDialog.setMessage("Wood: 1 (Per Lb)");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setNeutralButton("Cook 1Lb", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int food_counter = sharedPref.getInt("food", 0);
                int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
                if (wood_counter >= 1 && food_counter >= 1) {
                    log.setText(" You cooked 1Lb of Food! \n" + log.getText());;
                    wood_counter -= 1;
                    cooked_food_counter += 1;
                    food_counter -= 1;
                } else {
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("food", food_counter);
                editor.putInt("cooked_food", cooked_food_counter);
                editor.apply();
                updateText();
            }

        });
        int food_counter = sharedPref.getInt("food", 0);
        alertDialog.setPositiveButton("Cook All (" + food_counter + " Lb)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int wood_counter = sharedPref.getInt("wood", 0);
                int food_counter = sharedPref.getInt("food", 0);
                int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
                if (food_counter >= wood_counter) {
                    log.setText(" You cooked " + cooked_food_counter + " Lbs of Food! \n" + log.getText());
                    wood_counter -= food_counter;
                    cooked_food_counter += food_counter;
                    food_counter = 0;
                } else {
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
                //Save counter
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("wood", wood_counter);
                editor.putInt("food", food_counter);
                editor.putInt("cooked_food", cooked_food_counter);
                editor.apply();
                updateText();
            }

        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buttonLeafCanteen(View v) {
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Food_Water.this);
        alertDialog.setTitle("Craft: Leaf Canteen");
        alertDialog.setMessage("Leaves: 6");
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        alertDialog.setPositiveButton("Craft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Button leaf_canteen = (Button) findViewById(R.id.leaf_canteen);

                int leaves_counter = sharedPref.getInt("leaves", 0);
                if (leaves_counter >= 6) {
                    log.setText(" You crafted a Leaf Canteen \n" + log.getText());
                    leaves_counter -= 6;
                    leaf_canteen_b = 1;
                    leaf_canteen.setVisibility(View.GONE);
                    //Save counter
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("leaf_canteen", leaf_canteen_b);
                    editor.putInt("leaves", leaves_counter);
                    editor.apply();
                    updateText();
                } else {
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
            }

        });
        AlertDialog alert = alertDialog.create();
        alert.show();

    }
}







