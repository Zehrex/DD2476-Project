4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/BackupedProjectsAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.item.BackupedProject;
import com.sketch.code.two.util.AppBottomDialogUtil;
import com.sketch.code.two.util.GlideUtil;

import net.neonteam.appbottomdialog.builders.AppBottomDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

public class BackupedProjectsAdapter  extends RecyclerView.Adapter<BackupedProjectsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public ArrayList<ArrayList<BackupedProject>> data;

    private Activity activity;

    public BackupedProjectsAdapter(ArrayList<ArrayList<BackupedProject>> data, Activity activity) {
        this.data = data;
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;
    }
    @NotNull
    @Override
    public BackupedProjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.projects_manager_item_project, parent, false);
        return new BackupedProjectsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull BackupedProjectsAdapter.ViewHolder holder, int position) {
        if (data.size() != 0) {
            int lastPos = data.get(position).size() - 1;
            holder.title.setText(data.get(position).get(lastPos).getProject().getProjectName());
            holder.subtitle.setText(data.get(position).get(lastPos).getProject().getProjectPackage().concat(" (")
                    .concat(activity.getString(R.string.title_backups_count).concat(": ").concat(String.valueOf(data.get(position).size())).concat(")")));
            GlideUtil.set(new File(data.get(position).get(lastPos).getProject().getIconPath()), holder.icon, activity);
            holder.itemView.setOnClickListener(v -> {
                // init view for dialog (recyclerView)
                RecyclerView recyclerView = new RecyclerView(activity);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                recyclerView.setAdapter(new BackupsProjectAdapter(data.get(position), activity));
                recyclerView.setNestedScrollingEnabled(false);
                new AppBottomDialog.Builder(activity)
                        .setTitle(R.string.title_choose_project_backup_version)
                        .setTheme(AppBottomDialogUtil.getThemeSettings(activity))
                        .setContentView(recyclerView)
                        .show();
            });
        } else {
            holder.title.setText("No info");
            holder.subtitle.setText("No info");
            holder.icon.setImageDrawable(activity.getDrawable(R.mipmap.ic_launcher));
        }
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
