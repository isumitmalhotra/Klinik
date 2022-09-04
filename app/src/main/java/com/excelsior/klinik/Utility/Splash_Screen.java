package com.excelsior.klinik.Utility;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.excelsior.klinik.MainActivity;
import com.excelsior.klinik.R;
import com.excelsior.klinik.RegisterActivity;


public class Splash_Screen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 1900;
    Animation fade;
    ImageView logo;
    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Removes Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Animations
        fade = AnimationUtils.loadAnimation(this, R.anim.animm);
        logo = findViewById(R.id.splash_logo);
        textView = findViewById(R.id.splash_from);
        textView1 = findViewById(R.id.splash_my_name);

        // Assigning animations to image and text
        logo.setAnimation(fade);
        textView.setAnimation(fade);
        textView1.setAnimation(fade);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}