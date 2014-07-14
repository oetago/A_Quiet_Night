package AQuietNight.Ryanm14.MilesStudios.aquietnight;

/**
 * Created by Ryanm14 on 7/14/2014.
 */

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class main extends Activity {
    int counter;
    Button wood, wood2;
    TextView maindisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        counter = 0;
        maindisplay = (TextView) findViewById(R.id.maindisplay);
        wood = (Button) findViewById(R.id.wood);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        maindisplay.setTextSize(13);
        maindisplay.setText(" ...W..x..   " +
                "Darkness fills the land   " +
                "and you shiver ");
        //TODO Check other buttons and scaling



        wood.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                counter += 1;
                maindisplay.setText("Wood:" + counter);
                // set the color red first.
                // wood.setBackgroundColor(Color.CONNER_IS_);
                wood.setEnabled(false);
                wood.startAnimation(animAlpha);

                // change to original after 5 secs.
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        //wood.setBackgroundColor(Color.WHITE);
                        wood.setEnabled(true);
                    }
                }, 10000);

            };

        });
    }
}


