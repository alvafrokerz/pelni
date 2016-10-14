package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by ALVA on 8/27/2016.
 */

public class splashscreen  extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
         Animation a = AnimationUtils.loadAnimation(this,R.anim.move_up);
        ImageView image =(ImageView) findViewById(R.id.imageView);
        image.setAnimation(a);
        try {
            Thread.sleep(3000);
            Animation b = AnimationUtils.loadAnimation(this,R.anim.move_ship);
            ImageView image2 =(ImageView) findViewById(R.id.imageView2);
            image2.setAnimation(b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashscreen.this,login.class));
            }
        },6000);

    }
}
