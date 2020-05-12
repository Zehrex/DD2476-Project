1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/view/magicindicator/ScrollState.java
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

/**
 * 自定义滚动状态，消除对ViewPager的依赖
 * Created by hackware on 2016/8/27.
 */

public interface ScrollState {
    int SCROLL_STATE_IDLE = 0;
    int SCROLL_STATE_DRAGGING = 1;
    int SCROLL_STATE_SETTLING = 2;
}
