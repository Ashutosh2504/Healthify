package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences(LoginActivity.SHARED_PREFS,0);
                boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);
                boolean hasRegitration=sharedPreferences.getBoolean("hasRegitration",false);

                if (hasLoggedIn){
                    startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
                    //startActivity(new Intent(getApplicationContext(), FIrstActivity.class));
                    finish();


                }else if(hasRegitration){
                    startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
                    finish();

                }else {

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }



            }




        },3000);

    }
}