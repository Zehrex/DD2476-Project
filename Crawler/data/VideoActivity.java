1
https://raw.githubusercontent.com/hashtrojans/SampleApp/master/app/src/main/java/com/expert/splashapp/VideoActivity.java
package com.expert.splashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView vv1;
    Button b1;
    String pass_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        b1 = findViewById(R.id.b1);

        pass_value = getIntent().getStringExtra("value");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this,FirstActivity.class);
                intent.putExtra("pass_value",pass_value);
                startActivity(intent);
            }
        });

        vv1 = findViewById(R.id.vv1);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv1);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/splashapp-31e45.appspot.com/o/Coronavirus%20Myths%20vs%20Facts.mp4?alt=media&token=94d4d44c-bcb2-4b7e-b794-3f59eb96e85f");
        vv1.setVideoURI(uri);
        vv1.setMediaController(mediaController);
        vv1.start();
    }
}
