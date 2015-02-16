package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/13/2014.
 */
public class Village extends ActivityGroup {

    TextView log;
    Helper helper = new Helper(this);
    private android.os.Handler counterHandler = new android.os.Handler();

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Village.this, Cave.class);
                openMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Saving
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.village);
        helper.updateText();
        TextView log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        storage.setMovementMethod(new ScrollingMovementMethod());
        storage.setTextSize(15);
        helper.updatePopulation();
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        helper.updateWorkers();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Village.this, Cave.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        log = (TextView)findViewById(R.id.log);
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        finish();
        super.onPause();
    }

    public void lumberJackPlus(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        log = (TextView)findViewById(R.id.log);
        int max = 3 * sharedPref.getInt("shack",0) + 10 * sharedPref.getInt("house",0);
        int ava = max - sharedPref.getInt("lumber_jack",0) - sharedPref.getInt("miner",0);
        if(ava > 0) {
            int lumberjack_counter = sharedPref.getInt("lumber_jack", 0);
            editor.putInt("lumber_jack", lumberjack_counter + 1);
            editor.apply();
            helper.updatePopulation();
        }else{
            log.setText("Not enough available Workers!\n" + log.getText());
        }
    }



    public void lumberJackMinus(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        log = (TextView)findViewById(R.id.log);
        int lumberjack_counter = sharedPref.getInt("lumber_jack",0);
        if(lumberjack_counter > 0) {
            editor.putInt("lumber_jack", lumberjack_counter - 1);
            editor.apply();
            helper.updatePopulation();
        }else{
            log.setText("There are no Lumber Jacks to remove!\n" + log.getText());
        }
    }
    public void minerPlus(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        log = (TextView)findViewById(R.id.log);
        int max = 3 * sharedPref.getInt("shack",0) + 10 * sharedPref.getInt("house",0);
        int ava = max - sharedPref.getInt("lumber_jack",0) - sharedPref.getInt("miner",0);
        if(ava > 0) {
            int miner_counter = sharedPref.getInt("miner", 0);
            editor.putInt("miner", miner_counter + 1);
            editor.apply();
            helper.updatePopulation();
        }else{
            log.setText("Not enough available workers!\n" + log.getText());
        }
    }
    public void minerMinus(View v) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        log = (TextView)findViewById(R.id.log);
        int miner_counter = sharedPref.getInt("miner",0);
        if(miner_counter > 0) {
            editor.putInt("miner", miner_counter - 1);
            editor.apply();
            helper.updatePopulation();
        }else{
            log.setText("There are no Miners to remove!\n" + log.getText());
        }
    }

    public void shack(View v) {
        helper.buildVillage("Shack","Wood: 10 \nStone: 10","wood",10,"stone",10,"shack",this);
    }
    public void house(View v) {
        helper.buildVillage("House","Wood: 25 \nStone: 25","wood",25,"stone",25,"house",this);
    }

}


