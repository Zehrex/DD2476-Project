1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/ui/main/SectionsPagerAdapter.java
package com.agm.ipmanager.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.agm.ipmanager.R;
import com.agm.ipmanager.deploys.DeploysFragment;
import com.agm.ipmanager.machines.MachinesFragment;
import com.agm.ipmanager.settings.SettingsFragment;
import com.agm.ipmanager.status.StatusFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_0, R.string.tab_1, R.string.tab_2, R.string.tab_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StatusFragment.newInstance();
            case 1:
                return DeploysFragment.newInstance();
            case 2:
                return MachinesFragment.newInstance();
            case 3:
                return SettingsFragment.newInstance();
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}