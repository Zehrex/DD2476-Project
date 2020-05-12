4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/ProjectsAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.activity.ProjectViewActivity;
import com.sketch.code.two.api.item.Project;
import com.sketch.code.two.util.GlideUtil;

import java.util.ArrayList;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Project> data;

    private Activity activity;

    public ProjectsAdapter(ArrayList<Project> data, Activity activity) {
        this.data = data;
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;
    }
    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.market_item_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectsAdapter.ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getProjectName());
        GlideUtil.set(data.get(position).getProjectIcon(), holder.icon, activity);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(activity, ProjectViewActivity.class);
            i.putExtra("id", data.get(position).getId());
            activity.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView size;
        public ImageView icon;

        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.itemAppName);
            size = view.findViewById(R.id.itemAppSize);
            icon = view.findViewById(R.id.itemAppIcon);
        }
    }

}
