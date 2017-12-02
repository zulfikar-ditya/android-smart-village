package com.example.user.smartvillage.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.smartvillage.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                //TO DO auto-generated method sub
                Intent i = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(i);
                //jeda selesai SplashScreen
                finish();
            }
        }, 2000);
    }

}
