package com.milesstudios.aquietnight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.milesstudios.aquietnight.reference.SharedPref;

import java.util.Random;

/**
 * Created by Ryanm14 on 2/26/2015.
 */
public class Quest extends Activity {
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bosses);
        Button ft = (Button) findViewById(R.id.ft);
        sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);

        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft();
            }

        });
    }

    public void ft() {
        SharedPreferences.Editor editor = sharedPref.edit();
        Monster.setPlace(1);
        sendPlayerMax();
        editor.putInt(SharedPref.PLAYER_HEALTH, Monster.getPlayerMax());
        editor.apply();
        Monster.zeroEncounter();
        //MonsterList b1 = new MonsterList("Mother Tree",20,2,50,5,35,10,15);
        Random rng = new Random();
        Intent openMain = new Intent(Quest.this, Fight.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        switch(rng.nextInt(3)+1){
            case 1: new Monster("Acorn",5,2);
                startActivity(openMain);
                break;
            case 2: new Monster("Branch",9,3);
                startActivity(openMain);
                break;
            case 3: new Monster("Tree",12,1);
                startActivity(openMain);
            break;
        }

    }

    public void sendPlayerMax() {
        if(sharedPref.getBoolean(SharedPref.TIN_ARMOR,false)){
            Monster.setPlayerMax(25);
        }else if(sharedPref.getBoolean(SharedPref.LEAF_ARMOR,false)){
            Monster.setPlayerMax(15);
        }else{
            Monster.setPlayerMax(5);
        }
    }


}
class Monster extends Activity{
    private static String name;
    private static int place,playermaxhealth,health,damage,encounter=0;

    public Monster(String n, int h, int d){
        name = n;
        health = h;
        damage = d;
    }
    
    public static int getEnemyHealth(){
        return health;
    }

    public static String getName() {
        return name;
    }
    public static void addEncounter(){
        encounter++;
    }

    public static void setPlace(int ft) {
        place = ft;
    }
    public static int getPlace(){
        return place;
    }
    public static int getEncounter(){
        return encounter;
    }

    public static void zeroEncounter() {
        encounter = 0;
    }

    public static int getDamage() {
        return damage;
    }

    public static int getPlayerMax() {
        return playermaxhealth;
    }
    public static void setPlayerMax(int a) {
        playermaxhealth =  a;
    }
}




