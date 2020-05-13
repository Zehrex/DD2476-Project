1
https://raw.githubusercontent.com/hashtrojans/SampleApp/master/app/src/main/java/com/expert/splashapp/FirstActivity.java
package com.expert.splashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    TextView tv1;
    Button b1, b2;
    Animation animation;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        tv1 = findViewById(R.id.tv1);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        value = getIntent().getStringExtra("pass_value");
        tv1.setText(value);
        tv1.setAnimation(animation);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,VideoActivity.class);
                intent.putExtra("value",value);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
