package com.milesstudios.aquietnight.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;

import java.util.Random;

public class Helper extends Activity {
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Helper(Context context) {
        this.context = context;
    }

    public void build(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String output, Context context) {
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
                } else {
                    dialog.dismiss();
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void build(final String title, String message, final String r1, final int amount1,final String output, Context context) {
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
                    log.setText("You built a " + title + "! \n" + log.getText());
                } else {
                    dialog.dismiss();
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void buildBigger(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String r3, final int amount3, final String output, Context context) {
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
                } else {
                    dialog.dismiss();
                    log.setText(" You don't have enough resources! \n" + log.getText());
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
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else if (special_counter <= 249) {
                special_counter += sp_amount;
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special, special_counter);
            editor.apply();


        }

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
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else if (special_counter <= 249) {
                special_counter += sp_amount;
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special, special_counter);
            editor.apply();

        }

        if (rng.nextInt(1001) <= chance2) {
            int special_counter2 = sharedPref.getInt(special2, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter2 <= 999) {
                special_counter2 += sp_amount2;
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special2 + "! \n" + log.getText());
                }
            } else if (special_counter2 <= 249) {
                special_counter2 += sp_amount2;
                if (special2.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special2 + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special2, special_counter2);
            editor.apply();

        }

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
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else if (special_counter <= 249) {
                special_counter += sp_amount;
                if (special.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special, special_counter);
            editor.apply();

        }

        if (rng.nextInt(1001) <= chance2) {
            int special_counter2 = sharedPref.getInt(special2, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter2 <= 999) {
                special_counter2 += sp_amount2;
                if (special2.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special2 + "! \n" + log.getText());
                }
            } else if (special_counter2 <= 249) {
                special_counter2 += sp_amount2;
                if (special2.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special2 + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special2, special_counter2);
            editor.apply();

        }

        if (rng.nextInt(1001) <= chance3) {
            int special_counter3 = sharedPref.getInt(special3, 0);
            if (sharedPref.getBoolean("storage_shed", false) && special_counter3 <= 999) {
                special_counter3 += sp_amount3;
                if (special3.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special3 + "! \n" + log.getText());
                }
            } else if (special_counter3 <= 249) {
                special_counter3 += sp_amount3;
                if (special3.equals("apple")) {
                    log.setText(" You found an apple! \n" + log.getText());
                } else {
                    log.setText(" You found a " + special3 + "! \n" + log.getText());
                }
            } else {
            }
            editor.putInt(special3, special_counter3);
            editor.apply();

        }

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
        } else {
        }
        editor.putInt(item, counter);
        editor.apply();
    }

    public void collectL(String title, String item, int amount) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final TextView log = (TextView) ((Activity) context).findViewById(R.id.log);
        Random rng = new Random();
        final SharedPreferences.Editor editor = sharedPref.edit();
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && counter <= 54) {
            counter += amount;
        } else if (counter <= 24) {
            counter += amount;
        } else {
        }
        editor.putInt(item, counter);
        editor.apply();
    }

    public void fandW(String item, int amount, String output, int need) {
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        int wood = sharedPref.getInt("wood",0);
        int output_test = sharedPref.getInt("output",0);
        int counter = sharedPref.getInt(item, 0);
        if (sharedPref.getBoolean("storage_shed", false) && output_test <= 54 && wood >= need) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else if (output_test <= 24 && wood >= need) {
            output_test += need;
            counter -= amount;
            wood -= need;
        } else {
        }
        editor.putInt(item, counter);
        editor.putInt(output, output_test);
        editor.putInt("wood", wood);
        editor.apply();
    }

    public void updateText() {
        final TextView storage = (TextView) ((Activity) context).findViewById(R.id.storage);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int amber = sharedPref.getInt("amber", 0);
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        int food_counter = sharedPref.getInt("raw_food", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int apple_counter = sharedPref.getInt("apple", 0);
        int coin_counter = sharedPref.getInt("coins", 0);
        int copper_counter = sharedPref.getInt("copper", 0);
        int r_copper_counter = sharedPref.getInt("r_copper", 0);
        int coal_counter = sharedPref.getInt("coal", 0);
        int tin = sharedPref.getInt("tin", 0);
        int bismuth = sharedPref.getInt("bismuth", 0);
        int amethyst = sharedPref.getInt("amethyst", 0);
        int nickel = sharedPref.getInt("nickel", 0);

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
            storage.append("\n Food: " + food_counter);
        }
        if (cooked_food_counter >= 1) {
            storage.append("\n Cooked Food: " + cooked_food_counter);
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
            storage.append("\n \n \n Coins: " + coin_counter);
        }


    }

    public boolean buttonChecker(String name){
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        return sharedPref.getBoolean(name,false);


    }


}

