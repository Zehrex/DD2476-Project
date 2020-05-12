4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/activity/PhotoViewActivity.java
package com.sketch.code.two.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.sketch.code.two.R;
import com.sketch.code.two.adapter.viewpager.PhotoViewAdapter;

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        findViews();
        initLogic();
    }

    public void findViews () {
        viewPager = findViewById(R.id.viewPager);
    }

    public void initLogic() {
        viewPager.setAdapter(new PhotoViewAdapter(getIntent().getIntegerArrayListExtra("photos"), getApplicationContext()));
        viewPager.setCurrentItem(getIntent().getIntExtra("page", 0));
    }

}