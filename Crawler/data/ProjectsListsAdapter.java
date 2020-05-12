4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/ProjectsListsAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sketch.code.two.R;
import com.sketch.code.two.activity.ProjectsViewActivity;
import com.sketch.code.two.api.item.Projects;
import com.sketch.code.two.util.ThreadLoader;

import java.util.ArrayList;

public class ProjectsListsAdapter extends RecyclerView.Adapter<ProjectsListsAdapter.ViewHolder> {

    private ArrayList<Projects> data;

    private Activity activity;

    public ProjectsListsAdapter(ArrayList<Projects> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ProjectsListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.market_recycler_view_item_projects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectsListsAdapter.ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getProjectsName());
        holder.subtitle.setText(data.get(position).getProjectsDesc());
        new ThreadLoader(() -> {
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            holder.adapter = new ProjectsAdapter(data.get(position).getProjects(), activity);
        }, () -> {
            holder.recyclerView.setAdapter(holder.adapter);
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, ProjectsViewActivity.class);
            intent.putExtra("projects", new Gson().toJson(data.get(position).getProjects()));
            intent.putExtra("title", data.get(position).getProjectsName());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private TextView title;
        private TextView subtitle;

        public ProjectsAdapter adapter;

        ViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.list);
            title = view.findViewById(R.id.itemProjectsTitle);
            subtitle = view.findViewById(R.id.itemProjectsSubtitle);
        }
    }
}

