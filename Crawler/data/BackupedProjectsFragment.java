4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/fragment/BackupedProjectsFragment.java
package com.sketch.code.two.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.adapter.recyclerview.BackupedProjectsAdapter;
import com.sketch.code.two.util.ProjectUtil;
import com.sketch.code.two.util.ThreadLoader;

import java.util.Objects;


public class BackupedProjectsFragment extends Fragment {

    public RecyclerView recyclerView;

    private ProjectUtil projectUtil;

    public BackupedProjectsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_backuped_projects, container, false);
        initLogic();
        return recyclerView;
    }

    public void initLogic () {
        projectUtil = new ProjectUtil();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new ThreadLoader(() -> {
            adapter = new BackupedProjectsAdapter(projectUtil.getBackupedProjects(), Objects.requireNonNull(getActivity()));
        }, () -> {
            recyclerView.setAdapter(adapter);
        });
    }

    public void update () {
        new ThreadLoader(() -> {
            adapter.data = projectUtil.getBackupedProjects();
        }, () -> {
            adapter.notifyDataSetChanged();
        });
    }

}