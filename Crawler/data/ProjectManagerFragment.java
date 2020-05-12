4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/fragment/ProjectManagerFragment.java
package com.sketch.code.two.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.adapter.recyclerview.ProjectsManagerAdapter;
import com.sketch.code.two.util.ProjectUtil;
import com.sketch.code.two.util.ThreadLoader;

public class ProjectManagerFragment extends Fragment {

    private View view;

    private RecyclerView list;

    private ProjectUtil projectUtil = new ProjectUtil();
    private ProjectsManagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_project_manager, null);
        findViews();
        initLogic();
        return view;
    }

    public void findViews () {
        list = view.findViewById(R.id.list);
    }

    public void initLogic () {
        new ThreadLoader(() -> {
            adapter = new ProjectsManagerAdapter(projectUtil.getProjects(), getActivity());
        }, () -> {
            list.setLayoutManager(new LinearLayoutManager(getContext()));
            list.setAdapter(adapter);
        });
    }

    public void update () {
        new ThreadLoader(() -> {
            adapter.data = projectUtil.getProjects();
        }, () -> {
            adapter.notifyDataSetChanged();
        });
    }

}
