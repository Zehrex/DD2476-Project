4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/activity/ProjectsViewActivity.java
package com.sketch.code.two.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sketch.code.two.R;
import com.sketch.code.two.adapter.recyclerview.ProjectsAdapter;
import com.sketch.code.two.api.item.Project;
import com.sketch.code.two.manager.GridAutofitLayoutManager;
import com.sketch.code.two.util.MathUtil;
import com.sketch.code.two.util.ThreadLoader;

import java.util.ArrayList;

public class ProjectsViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private ArrayList<Project> arrayList;
    public ProjectsAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_view);
        findViews();
        initLogic();
    }

    public void findViews () {
        recyclerView = findViewById(R.id.list);
        toolbar = findViewById(R.id.toolbar);
    }

    public void initLogic () {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(getIntent().getStringExtra("title"));
        arrayList = new Gson().fromJson(getIntent().getStringExtra("projects"), new TypeToken<ArrayList<Project>>() {
        }.getType());
        new ThreadLoader(() -> {
            recyclerView.setLayoutManager(new GridAutofitLayoutManager(ProjectsViewActivity.this, MathUtil.toDp(80, getApplicationContext())));
            adapter = new ProjectsAdapter(arrayList, ProjectsViewActivity.this);
        }, () -> recyclerView.setAdapter(adapter));
    }

}