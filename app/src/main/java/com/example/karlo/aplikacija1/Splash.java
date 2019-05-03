package com.example.karlo.aplikacija1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    //in ms
    private static final int SPLASH_DURATION = 2250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashScreen();

    }

    // function for splash screen at the start of an app
    private void splashScreen() {

        TextView textViewSplash = (TextView) findViewById(R.id.textViewSplash);
        ImageView imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);
        Animation animSplash = AnimationUtils.loadAnimation(this, R.anim.splashtransition);

        // start animation for image(logo) and text
        textViewSplash.startAnimation(animSplash);
        imageViewSplash.startAnimation(animSplash);

        // intent for main activity
        final Intent intentSplash = new Intent(this, MainActivity.class);

        // thread for starting splash activity, then main activity
        Thread timerSplash = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intentSplash);
                    finish();
                }
            }
        };
        // start thread
        timerSplash.start();
    }
}
