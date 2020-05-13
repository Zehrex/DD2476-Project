1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/pageevent/src/main/java/com/wkp/pageevent/inter/PageStickEventListener.java
package com.wkp.pageevent.inter;

import android.support.annotation.NonNull;
import com.wkp.pageevent.info.Event;

/**
 * Created by wkp111 on 2020/5/7.
 * 页面粘性事件回调监听
 * <p>
 *     主要用于Activity和Fragment等之间相互通信
 * </p>
 */
public interface PageStickEventListener {
    /**
     * 粘性事件回调
     * @param event 事件
     * @return 是否移除粘性事件，true移除，后续监听器不会继续接收
     */
    boolean onStickEvent(@NonNull Event event);
}
