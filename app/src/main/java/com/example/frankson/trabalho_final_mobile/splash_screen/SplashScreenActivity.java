package com.example.frankson.trabalho_final_mobile.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.frankson.trabalho_final_mobile.R;
import com.example.frankson.trabalho_final_mobile.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 2 seconds
                Intent abreLogin = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(abreLogin);
                finish();
            }
        }, 2000);
    }

}
