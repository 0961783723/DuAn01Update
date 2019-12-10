package com.demoNhom11.DuAn01;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



public class HelloActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    ImageView logoHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(HelloActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                finish();

            }
        }, SPLASH_TIME_OUT);

        logoHello = findViewById(R.id.logoHello);
        Animation animationZoom = AnimationUtils.loadAnimation(this, R.anim.anim_zoom);
        logoHello.startAnimation(animationZoom);
    }

    private void fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}

