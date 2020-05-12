1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/view/magicindicator/buildins/commonnavigator/abs/IPagerIndicator.java
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

package com.hjq.demo.view.magicindicator.buildins.commonnavigator.abs;

import com.hjq.demo.view.magicindicator.buildins.commonnavigator.model.PositionData;

import java.util.List;


/**
 * 抽象的viewpager指示器，适用于CommonNavigator
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public interface IPagerIndicator {
    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);

    void onPositionDataProvide(List<PositionData> dataList);
}
