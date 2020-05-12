2
https://raw.githubusercontent.com/LakshyaKhatri/Lifetime/master/app/src/main/java/com/compiletales/lifetime/TimerEndActivity.java
package com.compiletales.lifetime;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TimerEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer_end);

        TextView tx = findViewById(R.id.end_activity_toolbar_text);
        Typeface adelline_font = Typeface.createFromAsset(getAssets(),  "fonts/adelline.ttf");
        tx.setTypeface(adelline_font);
        Button newTimerButton = findViewById(R.id.start_new_timer_button);
        newTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
