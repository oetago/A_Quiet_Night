package com.milesstudios.aquietnight.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.milesstudios.aquietnight.Buildings;
import com.milesstudios.aquietnight.R;

public class Helper extends Activity {
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Helper(Context context){
        this.context=context;
    }

    public void build(String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String output, Context context){
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
       final TextView log = (TextView) ((Activity)context).findViewById(R.id.log);
       final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
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
                   int r1_counter_save = r1_counter -  amount1;
                   int r2_counter_save = r2_counter -  amount2;
                   SharedPreferences.Editor editor = sharedPref.edit();
                   editor.putInt(r1, r1_counter_save);
                   editor.putInt(r2, r2_counter_save);
                   editor.putBoolean(output, true);
                   editor.apply();
                   dialog.dismiss();
                   Buildings b = new Buildings();
                   log.setText("You built a Trade Post! \n" + log.getText());
               } else {
                 dialog.dismiss();
                 log.setText(" You don't have enough resources! \n" + log.getText());
               }
           }
       });
       AlertDialog alert = alertDialog.create();
       alert.show();
//Fix output and update
    }
}