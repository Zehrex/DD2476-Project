4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/activity/MyProjectsActivity.java
package com.sketch.code.two.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sketch.code.two.R;
import com.sketch.code.two.adapter.viewpager.MyProjectsAdapter;

public class MyProjectsActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TabLayout tabLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);
        findViews();
        initLogic();
    }

    public void findViews () {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolbar);
    }

    public void initLogic () {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        initViewPager();
    }

    public void initViewPager () {
        viewPager.setAdapter(new MyProjectsAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);
    }

}