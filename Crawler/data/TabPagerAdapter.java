1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/adapter/otc/TabPagerAdapter.java
/**
 *  _                _
 * | |              (_)
 * | | ___   _  __ _ _ _   _  __ _ _ __
 * | |/ / | | |/ _` | | | | |/ _` | '_ \
 * |   <| |_| | (_| | | |_| | (_| | | | |
 * |_|\_\\__,_|\__,_|_|\__, |\__,_|_| |_|
 *                      __/ |
 *                     |___/
 */

package com.hjq.demo.ui.adapter.otc;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * 
 * @author LeoWang
 * 
 * @Package cn.com.drpeng.runman.common.adapter
 * 
 * @Description TabLayout关联适配器
 * 
 * @Date 2019/7/3 16:26
 * 
 * @modify:
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private FragmentManager mFragmentManager;

    private FragmentTransaction mFragmentTransaction;

    private List<Fragment> list;
    private List<String> titles;

    public TabPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles){
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragmentManager = fm;
        mFragmentTransaction = mFragmentManager.beginTransaction();
        this.list = list;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position % titles.size());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = list.get(position);// 获取要销毁的fragment
        mFragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }
}
