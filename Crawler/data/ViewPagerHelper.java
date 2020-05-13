1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/view/magicindicator/ViewPagerHelper.java
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

package com.hjq.demo.view.magicindicator;


import androidx.viewpager.widget.ViewPager;

/**
 * 简化和ViewPager绑定
 * Created by hackware on 2016/8/17.
 */

public class ViewPagerHelper {
    public static void bind(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
