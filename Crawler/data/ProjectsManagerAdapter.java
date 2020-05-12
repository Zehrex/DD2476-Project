4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/ProjectsManagerAdapter.java
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
import com.sketch.code.two.item.SketchwareProject;
import com.sketch.code.two.util.AppBottomDialogUtil;
import com.sketch.code.two.util.GlideUtil;
import com.sketch.code.two.util.ProjectUtil;
import com.sketch.code.two.util.ThreadLoader;

import net.neonteam.appbottomdialog.builders.AppBottomDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

public class ProjectsManagerAdapter extends RecyclerView.Adapter<ProjectsManagerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public ArrayList<SketchwareProject> data;

    private Activity activity;

    public ProjectUtil projectUtil;

    public LoadingDialog loadingDialog;

    public ProjectsManagerAdapter(ArrayList<SketchwareProject> data, Activity activity) {
        this.data = data;
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;
        projectUtil = new ProjectUtil();
    }
    @NotNull
    @Override
    public ProjectsManagerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.projects_manager_item_project, parent, false);
        return new ProjectsManagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ProjectsManagerAdapter.ViewHolder holder, int position) {
        if(loadingDialog == null)
            loadingDialog = new LoadingDialog(activity);
        holder.title.setText(data.get(position).getProjectName());
        holder.subtitle.setText(data.get(position).getProjectPackage().concat(" (").concat(String.valueOf(data.get(position).getId())).concat(")"));
        GlideUtil.set(new File(data.get(position).getIconPath()), holder.icon, activity);
        holder.itemView.setOnClickListener(v -> holder.menu.performClick());
        holder.menu.setOnClickListener(v -> {
            new AppBottomDialog.Builder(activity)
                    .setTitle(R.string.title_menu)
                    .setTheme(AppBottomDialogUtil.getThemeSettings(activity))
                    .setupMenu(R.menu.projectsmanager_menu)
                    .setListener(menuItem -> {
                        switch (menuItem.getItemId()) {
                            case R.id.backupProject:
                                loadingDialog.show();
                                new ThreadLoader(() -> {
                                    projectUtil.backupProject(data.get(position).getId());
                                }, () -> {
                                    Toast.makeText(activity, activity.getString(R.string.title_backuped), Toast.LENGTH_SHORT).show();
                                    MyProjectsAdapter.onProjectsUpdated.onUpdate();
                                    loadingDialog.dismiss();
                                });
                                break;
                        }
                    })
                    .build()
                    .show();
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
