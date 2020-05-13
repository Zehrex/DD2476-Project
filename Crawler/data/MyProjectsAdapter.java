4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/viewpager/MyProjectsAdapter.java
package com.sketch.code.two.adapter.viewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sketch.code.two.R;
import com.sketch.code.two.fragment.BackupedProjectsFragment;
import com.sketch.code.two.fragment.ProjectManagerFragment;

public class MyProjectsAdapter extends FragmentPagerAdapter {

    private Context context;

    public static OnProjectsUpdated onProjectsUpdated;

    public ProjectManagerFragment projectManagerFragment;
    public BackupedProjectsFragment backupedProjectsFragment;

    public MyProjectsAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        onProjectsUpdated = () -> {
            projectManagerFragment.update();
            backupedProjectsFragment.update();
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                projectManagerFragment = new ProjectManagerFragment();
                return projectManagerFragment;
            case 1:
                backupedProjectsFragment = new BackupedProjectsFragment();
                return backupedProjectsFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.sketchware_projects);
            case 1:
                return context.getString(R.string.backup_projects);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public interface OnProjectsUpdated {
        void onUpdate();
    }

}
