package com.example.sohyunkim.onairs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.sohyunkim.project_1.R;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(3000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
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
                intent.putExtra("name",SaveSharedPreferences.getNameState(this).toString());
                startActivity(intent);
                this.finish();
            }



    }
}

