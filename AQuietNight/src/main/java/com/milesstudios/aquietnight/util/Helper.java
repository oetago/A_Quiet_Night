package com.milesstudios.aquietnight.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;

import java.util.Random;

public class Helper extends Activity {
    Context context;
    int counter;

    public Helper(Context context) {
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void build(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String output, Context context, final String update) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final int r1_counter = sharedPref.getInt(r1, 0);
        final int r2_counter = sharedPref.getInt(r2, 0);
        alertDialog.setTitle("Build: " + title);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (r1_counter >= amount1 && r2_counter >= amount2) {
                    int r1_counter_save = r1_counter - amount1;
                    int r2_counter_save = r2_counter - amount2;
                    editor.putInt(r1, r1_counter_save);
                    editor.putInt(r2, r2_counter_save);
                    editor.putBoolean(output, true);
                    editor.apply();
                    dialog.dismiss();
                    log.setText("You built a " + title + "! \n" + log.getText());
                    updateText();
                    updateButtons(update);
                } else {
                    dialog.dismiss();
                    log.setText("You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void build(final String title, String message, final String r1, final int amount1, final String output, Context context, final String update) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final int r1_counter = sharedPref.getInt(r1, 0);
        alertDialog.setTitle("Build: " + title);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (r1_counter >= amount1) {
                    int r1_counter_save = r1_counter - amount1;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(r1, r1_counter_save);
                    editor.putBoolean(output, true);
                    editor.apply();
                    dialog.dismiss();
                    updateText();
                    updateButtons(update);
                    log.setText("You built a " + title + "! \n" + log.getText());
                } else {
                    dialog.dismiss();
                    log.setText("You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void build(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String r3, final int amount3, final String output, Context context, final String update) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final int r1_counter = sharedPref.getInt(r1, 0);
        final int r2_counter = sharedPref.getInt(r2, 0);
        final int r3_counter = sharedPref.getInt(r3, 0);

        alertDialog.setTitle("Build: " + title);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (r1_counter >= amount1 && r2_counter >= amount2 && r3_counter >= amount3) {
                    int r1_counter_save = r1_counter - amount1;
                    int r2_counter_save = r2_counter - amount2;
                    int r3_counter_save = r3_counter - amount3;
                    editor.putInt(r1, r1_counter_save);
                    editor.putInt(r2, r2_counter_save);
                    editor.putInt(r3, r3_counter_save);
                    editor.putBoolean(output, true);
                    editor.apply();
                    dialog.dismiss();
                    log.setText("You built a " + title + "! \n" + log.getText());
                    updateText();
                    updateButtons(update);
                } else {
                    dialog.dismiss();
                    log.setText("You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buildVillage(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String output, Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final int r1_counter = sharedPref.getInt(r1, 0);
        final int r2_counter = sharedPref.getInt(r2, 0);
        alertDialog.setTitle("Build: " + title);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (r1_counter >= amount1 && r2_counter >= amount2) {
                    int r1_counter_save = r1_counter - amount1;
                    int r2_counter_save = r2_counter - amount2;
                    editor.putInt(r1, r1_counter_save);
                    editor.putInt(r2, r2_counter_save);
                    editor.putInt(output, sharedPref.getInt(output, 0) + 1);
                    editor.apply();
                    dialog.dismiss();
                    log.setText("You built a " + title + "! \n" + log.getText());
                    updatePopulation();
                    updateText();
                } else {
                    dialog.dismiss();
                    log.setText("You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void collect(String item, int amount, String special, int sp_amount, int chance) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 999) {
            counter += amount;
        } else if (counter <= 249) {
            counter += amount;
        } else {
        }
        editor.putInt(item, counter);
        editor.apply();

        int random = rng.nextInt(1001);
        if (random <= chance) {
            int special_counter = sharedPref.getInt(special, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter <= 999) {
                special_counter += sp_amount;
            } else if (special_counter <= 249) {
                special_counter += sp_amount;
            }
            editor.putInt(special, special_counter);
            editor.apply();


        }
        updateText();
    }

    public void collect(String item, int amount, String special, int sp_amount, int chance, String special2, int sp_amount2, int chance2) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 999) {
            counter += amount;
        } else if (counter <= 249) {
            counter += amount;
        } else {
        }
        editor.putInt(item, counter);
        editor.apply();
        if (rng.nextInt(1001) <= chance) {
            int special_counter = sharedPref.getInt(special, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter <= 999) {
                special_counter += sp_amount;
            } else if (special_counter <= 249) {
                special_counter += sp_amount;
            }
            editor.putInt(special, special_counter);
            editor.apply();

        }

        if (rng.nextInt(1001) <= chance2) {
            int special_counter2 = sharedPref.getInt(special2, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter2 <= 999) {
                special_counter2 += sp_amount2;
            } else if (special_counter2 <= 249) {
                special_counter2 += sp_amount2;
            }
            editor.putInt(special2, special_counter2);
            editor.apply();

        }
        updateText();
    }

    public void collect(String item, int amount, String special, int sp_amount, int chance, String special2, int sp_amount2, int chance2, String special3, int sp_amount3, int chance3) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 999) {
            counter += amount;
        } else if (counter <= 249) {
            counter += amount;
        } else {
        }
        editor.putInt(item, counter);
        editor.apply();
        if (rng.nextInt(1001) <= chance) {
            int special_counter = sharedPref.getInt(special, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter <= 999) {
                special_counter += sp_amount;

            } else if (special_counter <= 249) {
                special_counter += sp_amount;
            }
            editor.putInt(special, special_counter);
            editor.apply();

        }

        if (rng.nextInt(1001) <= chance2) {
            int special_counter2 = sharedPref.getInt(special2, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter2 <= 999) {
                special_counter2 += sp_amount2;
            } else if (special_counter2 <= 249) {
                special_counter2 += sp_amount2;
            }
            editor.putInt(special2, special_counter2);
            editor.apply();
        }

        if (rng.nextInt(1001) <= chance3) {
            int special_counter3 = sharedPref.getInt(special3, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter3 <= 999) {
                special_counter3 += sp_amount3;
            } else if (special_counter3 <= 249) {
                special_counter3 += sp_amount3;
            }
            editor.putInt(special3, special_counter3);
            editor.apply();

        }
        updateText();
    }

    public void collect(String item, int amount) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 999) {
            counter += amount;
        } else if (counter <= 249) {
            counter += amount;
        }
        editor.putInt(item, counter);
        editor.apply();
        updateText();
    }

    public void collectL(String title, String item, int amount) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 24) {
            counter += amount;
        } else if (counter <= 14) {
            counter += amount;
        }
        editor.putInt(item, counter);
        editor.apply();
        updateText();
    }

    public void collectF(String title, String item, int amount) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 14) {
            counter += amount;
        } else if (counter <= 9) {
            counter += amount;
        }
        editor.putInt(item, counter);
        editor.apply();
        updateText();
    }

    public void fandW(String item, int amount, String output, int need) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences.Editor editor = sharedPref.edit();
        int wood = sharedPref.getInt("wood", 0);
        int output_test = sharedPref.getInt(output, 0);
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && output_test <= 25 && wood >= need && counter > 0) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else if (output_test <= 15 && wood >= need && counter > 0) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else {
            log.setText("You don't have enough resources, or you have no space! \n" + log.getText());
        }
        editor.putInt(item, counter);
        editor.putInt(output, output_test);
        editor.putInt("wood", wood);
        editor.apply();
        updateText();
    }

    public void fandW2(String item, int amount, String output, int need) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        final SharedPreferences.Editor editor = sharedPref.edit();
        int wood = sharedPref.getInt("wood", 0);
        int output_test = sharedPref.getInt(output, 0);
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && output_test <= 15 && wood >= need && counter > 0) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else if (output_test <= 10 && wood >= need && counter > 0) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else {
            log.setText("You don't have enough resources, or you have no space! \n" + log.getText());
        }
        editor.putInt(item, counter);
        editor.putInt(output, output_test);
        editor.putInt("wood", wood);
        editor.apply();
        updateText();
    }

    public void updateButtons(String update) {
        if (update.equals("Food_Water")) foodWater();
        else if (update.equals("Tools")) tools();
        else if (update.equals("Weapons_Armor")) weaponsArmor();
        else if (update.equals("Buildings")) buildings();
        //else if (update.equals("Trade")) updateBT.trade();

    }

    public void updateText() {
        TextView storage = (TextView) ((Activity) context).findViewById(R.id.storage);
        SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int amber = sharedPref.getInt("amber", 0);
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        int raw_food = sharedPref.getInt("raw_food", 0);
        int cooked_food = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int apple_counter = sharedPref.getInt("apple", 0);
        int coin_counter = sharedPref.getInt("coins", 0);
        int coal_counter = sharedPref.getInt("coal", 0);
        int tin = sharedPref.getInt("tin", 0);
        int bismuth = sharedPref.getInt("bismuth", 0);
        int amethyst = sharedPref.getInt("amethyst", 0);
        int nickel = sharedPref.getInt("nickel", 0);
        double lumberjacks = sharedPref.getInt("lumber_jack", 0) * 0.2;
        double miners = sharedPref.getInt("miner", 0) * 0.2;

        storage.setText(" Storage:");
        if (wood_counter >= 1) {
            storage.append("\n Wood: " + wood_counter + "   + " + (int) lumberjacks + " - 5s");
        }
        if (leaves_counter >= 1) {
            storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            storage.append("\n Stone: " + stone_counter + "   + " + (int) miners + " - 5s");
        }

        if (dirty_water_counter >= 1) {
            storage.append("\n Dirty Water: " + dirty_water_counter);
        }
        if (raw_food >= 1) {
            storage.append("\n Raw Meat: " + raw_food);
        }
        if (cooked_food >= 1) {
            storage.append("\n Cooked Meat: " + cooked_food);
        }
        if (boiled_water_counter >= 1) {
            storage.append("\n Boiled Water: " + boiled_water_counter);
        }
        if (apple_counter >= 1) {
            storage.append("\n Apples: " + apple_counter);
        }
        if (amber >= 1) {
            storage.append("\n Amber: " + amber);
        }
        if (tin >= 1) {
            storage.append("\n Tin: " + tin);
        }
        if (bismuth >= 1) {
            storage.append("\n Bismuth: " + bismuth);
        }
        if (nickel >= 1) {
            storage.append("\n Nickel: " + nickel);
        }
        if (amethyst >= 1) {
            storage.append("\n Amethyst: " + amethyst);
        }
        if (coin_counter >= 1) {
            storage.append("\n Coins: " + coin_counter);
        }


    }

    public void tools() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Button stonepick = (Button) ((Activity) context).findViewById(R.id.stone_pick);
        Button stoneaxe = (Button) ((Activity) context).findViewById(R.id.stone_axe);
        Button tinpick = (Button) ((Activity) context).findViewById(R.id.tin_pick);
        Button tinaxe = (Button) ((Activity) context).findViewById(R.id.tin_axe);

        if (!sharedPref.getBoolean("stone_pick", false)) {
            tinpick.setVisibility(View.GONE);
            tinaxe.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("stone_pick", false)) {
            stonepick.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("stone_axe", false)) {
            stoneaxe.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("tin_pick", false)) {
            tinpick.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("tin_axe", false)) {
            tinaxe.setVisibility(View.GONE);
        }
    }

    public void foodWater() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        if (sharedPref.getBoolean("leaf_canteen", false)) {
            Button t = (Button) ((Activity) context).findViewById(R.id.leaf_canteen);
            t.setVisibility(View.GONE);

        }
    }

    public void buildings() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        Button bonfire = (Button) ((Activity) context).findViewById(R.id.fireplace);
        Button workshop = (Button) ((Activity) context).findViewById(R.id.workshop);
        Button village = (Button) ((Activity) context).findViewById(R.id.village);
        Button tannery = (Button) ((Activity) context).findViewById(R.id.tannery);
        Button storageshed = (Button) ((Activity) context).findViewById(R.id.storage_shed);


        if (!sharedPref.getBoolean("workshop", false)) {
            bonfire.setVisibility(View.GONE);
            village.setVisibility(View.GONE);
            tannery.setVisibility(View.GONE);
            storageshed.setVisibility(View.GONE);
        } else {
            bonfire.setVisibility(View.VISIBLE);
        }


        if (!sharedPref.getBoolean("stone_axe", false)) {
            village.setVisibility(View.GONE);
            tannery.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("bonfire", false)) {
            bonfire.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("workshop", false)) {
            workshop.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("storage_shed", false)) {
            storageshed.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("village", false)) {
            village.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("tannery", false)) {
            tannery.setVisibility(View.GONE);
        }
    }

    public void weaponsArmor() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getBoolean("stone_sword", false)) {
            Button t = (Button) ((Activity) context).findViewById(R.id.stone_sword);
            t.setVisibility(View.GONE);
        }
        if (sharedPref.getBoolean("leaf_armor", false)) {
            Button t = (Button) ((Activity) context).findViewById(R.id.leaf_armor);
            t.setVisibility(View.GONE);
        }
    }

    public void updatePopulation() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        TextView pop = (TextView) ((Activity) context).findViewById(R.id.population);
        TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        int max = 3 * sharedPref.getInt("shack", 0) + 10 * sharedPref.getInt("house", 0);
        int ava = max - sharedPref.getInt("lumber_jack", 0) - sharedPref.getInt("miner", 0);
        pop.setText(" Max Population: " + max);
        pop.append("\n Available Workers: " + ava);
        pop.append("\n Lumber Jacks: " + sharedPref.getInt("lumber_jack", 0));
        pop.append("\n Miners : " + sharedPref.getInt("miner", 0));
        pop.append("\n");
        double lumberjacks = sharedPref.getInt("lumber_jack", 0) * 0.2;
        pop.append(" Wood + " + (int) lumberjacks + " - 5s");
        double miners = sharedPref.getInt("miner", 0) * 0.2;
        pop.append("\n Stone + " + (int) miners + " - 5s");
        if (max >= 50 && sharedPref.getInt("forest_text", 0) == 2) {
            log.setText("A worker stumbled on an old temple in the forest... \n" + log.getText());
            questText("forest_text", 3);
        } else if (max >= 35 && sharedPref.getInt("forest_text", 0) == 1) {
            log.setText("Strange noises are being heard in the deep forest...\n" + log.getText());
            questText("forest_text", 2);
        } else if (max >= 15 && sharedPref.getInt("forest_text", 0) == 0) {
            log.setText("Workers are seeing strange figures in the deep forest...\n" + log.getText());
            questText("forest_text", 1);
        }
    }

    private void questText(String a, int i) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(a, i);
        editor.apply();
    }

    public void updateWorkers() {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        CountDownTimer mCountDownTimer = new CountDownTimer(5000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (sharedPref.getBoolean("storage_shed", false) && sharedPref.getInt("wood", 0) <= 999) {
                    double lumberjacks = sharedPref.getInt("lumber_jack", 0) * 0.2;
                    editor.putInt("wood", sharedPref.getInt("wood", 0) + (int) lumberjacks);
                } else if (sharedPref.getInt("wood", 0) <= 249) {
                    double lumberjacks = sharedPref.getInt("lumber_jack", 0) * 0.2;
                    editor.putInt("wood", sharedPref.getInt("wood", 0) + (int) lumberjacks);
                    Log.v("Helper", "ljs = " + lumberjacks);
                }

                if (sharedPref.getBoolean("storage_shed", false) && sharedPref.getInt("stone", 0) <= 999) {
                    double miners = sharedPref.getInt("miner", 0) * 0.2;
                    editor.putInt("stone", sharedPref.getInt("stone", 0) + (int) miners);
                } else if (sharedPref.getInt("stone", 0) <= 249) {
                    double miners = sharedPref.getInt("miner", 0) * 0.2;
                    editor.putInt("stone", sharedPref.getInt("stone", 0) + (int) miners);
                    Log.v("Helper", " miners = " + miners);
                }
                editor.apply();
                updateWorkers();
            }
        }.start();

    }


}
