2
https://raw.githubusercontent.com/sachin2912/torripo/master/app/src/main/java/com/example/torripo/splash_activity.java
package com.example.torripo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash_activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
