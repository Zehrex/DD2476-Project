4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/activity/ProjectViewActivity.java
package com.sketch.code.two.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.adapter.recyclerview.ScreenshotsAdapter;
import com.sketch.code.two.api.item.Project;
import com.sketch.code.two.api.item.ProjectInfoResponse;
import com.sketch.code.two.api.manager.ProjectsManager;
import com.sketch.code.two.util.GlideUtil;
import com.sketch.code.two.util.ThreadLoader;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectViewActivity extends AppCompatActivity {

    private TextView appSize;
    private TextView appTitle;
    private TextView appCategory;
    private TextView downloads;

    private TextView appSubtitle;

    private ImageView appIcon;

    private ProjectsManager projectsManager = ProjectsManager.getInstance();

    private ScreenshotsAdapter screenshotsAdapter;
    private RecyclerView screenshots;

    private NestedScrollView scrollView;
    private ProgressBar progressBar;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);
        findViews();
        initLogic();
    }

    public void findViews () {
        appSize = findViewById(R.id.itemAppSize);
        appTitle = findViewById(R.id.itemAppName);
        appCategory = findViewById(R.id.itemAppCategory);
        downloads = findViewById(R.id.itemAppDownloads);
        appIcon = findViewById(R.id.itemAppIcon);
        appSubtitle = findViewById(R.id.itemAppSubtitle);
        screenshots = findViewById(R.id.screenshots);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);
        toolbar = findViewById(R.id.toolbar);
    }


    public void initLogic () {
        setSupportActionBar(toolbar);
        scrollView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        projectsManager.getApi()
                .getProject(getIntent().getIntExtra("id", -1))
                .enqueue(new Callback<ProjectInfoResponse>() {
                    @Override
                    public void onResponse(Call<ProjectInfoResponse> call, Response<ProjectInfoResponse> response) {
                        if(response.code() == 200 && response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            Project project = response.body().getData();
                            Objects.requireNonNull(getSupportActionBar()).setTitle(project.getProjectName());
                            appTitle.setText(project.getProjectName());
                            appSubtitle.setText(project.getProjectAbout());
                            appCategory.setText(project.getProjectCategoryName());
                            GlideUtil.set(project.getProjectIcon(), appIcon, getApplicationContext());
                            new ThreadLoader(() -> {
                                screenshotsAdapter = new ScreenshotsAdapter(project.getProjectScreenshots());
                                screenshots.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                            }, () -> {
                                screenshots.setAdapter(screenshotsAdapter);
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            });
                        } else if(response.code() == 200) {
                            Toast.makeText(ProjectViewActivity.this, getString(R.string.error_occured), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ProjectInfoResponse> call, Throwable t) {
                        Toast.makeText(ProjectViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        finish();
                    }
                });
    }

}