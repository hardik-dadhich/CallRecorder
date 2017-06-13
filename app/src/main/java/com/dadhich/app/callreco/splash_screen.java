package com.dadhich.app.callreco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.animation.ObjectAnimator;
import android.view.animation.DecelerateInterpolator;

public class splash_screen extends AppCompatActivity {


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);





        progressBar =(ProgressBar)findViewById(R.id.progressBar) ;

        ObjectAnimator anim1=  ObjectAnimator.ofInt(progressBar,"Progress",0,100);
        anim1.setDuration(3000);
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.start();


        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(getApplicationContext(),Tabbed_activity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

}
