1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/view/magicindicator/buildins/commonnavigator/model/PositionData.java
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

package com.hjq.demo.view.magicindicator.buildins.commonnavigator.model;

/**
 * 保存指示器标题的坐标
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class PositionData {
    public int mLeft;
    public int mTop;
    public int mRight;
    public int mBottom;
    public int mContentLeft;
    public int mContentTop;
    public int mContentRight;
    public int mContentBottom;

    public int width() {
        return mRight - mLeft;
    }

    public int height() {
        return mBottom - mTop;
    }

    public int contentWidth() {
        return mContentRight - mContentLeft;
    }

    public int contentHeight() {
        return mContentBottom - mContentTop;
    }

    public int horizontalCenter() {
        return mLeft + width() / 2;
    }

    public int verticalCenter() {
        return mTop + height() / 2;
    }
}
