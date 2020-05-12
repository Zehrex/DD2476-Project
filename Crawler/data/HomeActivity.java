1
https://raw.githubusercontent.com/hashtrojans/SampleApp/master/app/src/main/java/com/expert/splashapp/HomeActivity.java
package com.expert.splashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    Button b1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1 = findViewById(R.id.b1);
        et1 = findViewById(R.id.et1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass_value = et1.getText().toString();
                Intent intent = new Intent(HomeActivity.this,FirstActivity.class);
                intent.putExtra("pass_value",pass_value);
                startActivity(intent);
            }
        });

    }
}
