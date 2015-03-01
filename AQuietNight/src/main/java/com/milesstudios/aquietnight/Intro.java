package com.milesstudios.aquietnight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by Ryan on 10/24/2014.
 */
public class Intro extends Activity {
    Button buttonNext;
    String textToShow[] = {"You stood up against Valeon’s evil king. Rose up against a tyrannicl regime.", "But you were exiled. You were disgraced. Defeated.", "Forced to wander the wilderness. With only a cave as your home.",
            "But you will take back what is right.", "You will fix what is wrong. And you will take the crown.", "But until then, here you are. You slowly come to your senses.", "The early light blinds your eyes. "};
    int messageCount = textToShow.length;
    // to keep current Index of text
    int currentIndex = 0;
    private TextSwitcher intro_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        intro_text = (TextSwitcher) findViewById(R.id.intro_text);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        intro_text.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub
                // create new textView and set the properties like clolr, size etc
                TextView myText = new TextView(Intro.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(25);
                myText.setTextColor(Color.WHITE);
                return myText;
            }
        });
        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        intro_text.setText(textToShow[currentIndex]);
        // set the animation type of textSwitcher
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/WalterTurncoat.ttf");
        intro_text.setInAnimation(in);
        intro_text.setOutAnimation(out);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If inreaches maximum reset it
                if (currentIndex == messageCount) {
                    intro_text.clearAnimation();
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
                    if (sharedPref.getInt("start_counter", 0) == 0) {
                        Intent openMain_Screen = new Intent(Intro.this, Forest.class);
                        startActivity(openMain_Screen);
                    } else {
                        Intent openMain_Screen = new Intent(Intro.this, Cave.class);
                        startActivity(openMain_Screen);
                    }

                    //overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    // intro_text.setText(textToShow[currentIndex]);
                } else {
                    intro_text.setText(textToShow[currentIndex]);
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
    }


    public void Launch() {

    }

}



