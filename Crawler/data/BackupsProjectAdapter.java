4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/BackupsProjectAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.adapter.viewpager.MyProjectsAdapter;
import com.sketch.code.two.dialog.LoadingDialog;
import com.sketch.code.two.item.BackupedProject;
import com.sketch.code.two.util.GlideUtil;
import com.sketch.code.two.util.ProjectUtil;
import com.sketch.code.two.util.ThreadLoader;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BackupsProjectAdapter extends RecyclerView.Adapter<BackupsProjectAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BackupedProject> data;

    private Activity activity;

    public ProjectUtil projectUtil;

    public LoadingDialog loadingDialog;

    public BackupsProjectAdapter(ArrayList<BackupedProject> data, Activity activity) {
        this.data = data;
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;
        projectUtil = new ProjectUtil();
    }
    @NotNull
    @Override
    public BackupsProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.projects_manager_item_project, parent, false);
        return new BackupsProjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull BackupsProjectAdapter.ViewHolder holder, int position) {
        if(loadingDialog == null)
            loadingDialog = new LoadingDialog(activity);
        holder.title.setText(data.get(position).getProject().getProjectName());
        holder.subtitle.setText(data.get(position).getProject().getProjectPackage().concat(" (").concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(position).getTime())).concat(")"));
        GlideUtil.set(new File(data.get(position).getProject().getIconPath()), holder.icon, activity);
        holder.itemView.setOnClickListener(v -> holder.menu.performClick());
        holder.menu.setOnClickListener(v -> {
            LoadingDialog loadingDialog = new LoadingDialog(activity)
                    .show();
            new ThreadLoader(() -> projectUtil.copyProject(data.get(position).getConfiguration(), projectUtil.getNewConfiguration(projectUtil.nextFreeId())), () -> {
                Toast.makeText(activity, activity.getString(R.string.title_restored), Toast.LENGTH_SHORT).show();
                MyProjectsAdapter.onProjectsUpdated.onUpdate();
                loadingDialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subtitle;

        public ImageView menu;
        public ImageView icon;

        public ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.itemProjectName);
            subtitle = view.findViewById(R.id.itemProjectSubtitle);
            menu = view.findViewById(R.id.itemMenu);
            icon = view.findViewById(R.id.itemProjectAvatar);
        }
    }

}


