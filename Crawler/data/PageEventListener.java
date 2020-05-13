1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/pageevent/src/main/java/com/wkp/pageevent/inter/PageEventListener.java
package com.wkp.pageevent.inter;

import android.support.annotation.NonNull;
import com.wkp.pageevent.info.Event;

/**
 * Created by wkp111 on 2020/5/6.
 * 页面普通事件回调监听
 * <p>
 *     主要用于Activity和Fragment等之间相互通信
 * </p>
 */
public interface PageEventListener {
    /**
     * 普通事件回调
     * @param event 事件
     */
    void onEvent(@NonNull Event event);
}
