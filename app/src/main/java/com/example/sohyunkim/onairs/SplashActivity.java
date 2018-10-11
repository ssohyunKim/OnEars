package com.example.sohyunkim.onairs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SaveSharedPreferences.getuserIDState(SplashActivity.this).toString() == "") {
            //call LoginActivity
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            //pass
            intent = new Intent(SplashActivity.this, MainActivity.class);
            //intent.putExtra("name", SaveSharedPreferences.getNameState(this).toString());
            intent.putExtra("userID", SaveSharedPreferences.getuserIDState(this).toString());
            startActivity(intent);
            this.finish();
        }
    }
}

