1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/pageevent/src/main/java/com/wkp/pageevent/base/EventActivity.java
package com.wkp.pageevent.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.wkp.pageevent.helper.EventHelper;
import com.wkp.pageevent.info.Event;
import com.wkp.pageevent.inter.PageEvent;
import com.wkp.pageevent.inter.PageEventListener;
import com.wkp.pageevent.inter.PageStickEventListener;
import com.wkp.pageevent.log.LogHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wkp111 on 2020/5/6.
 * 实现页面间事件传递的基类Activity
 * <p>
 *     主要负责管理页面间事件监听器的注册及注销，默认该页面支持事件触发
 * </p>
 */
public class EventActivity extends Activity implements PageEventListener, PageStickEventListener, PageEvent {
    private static final String TAG = "EventActivity";
    /**
     * 是否开启页面事件监听，默认不开启
     */
    private boolean mPageEventEnable;
    /**
     * 当前Activity需要监听事件的页面集合
     */
    private List<Class<? extends PageEvent>> mEventList;

    /**
     * 是否开启页面粘性事件监听，默认不开启
     */
    private boolean mPageStickEventEnable;
    /**
     * 当前Activity需要监听粘性事件的页面集合
     */
    private List<Class<? extends PageEvent>> mStickEventList;

    /**
     * 初始化页面监听
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 普通事件监听初始化
        mPageEventEnable = initPageEvent();
        if (mPageEventEnable && mEventList != null) {
            for (Class<? extends PageEvent> eventPage : mEventList) {
                EventHelper.registerEventPage(this, eventPage);
            }
        }
        // 粘性事件监听初始化
        mPageStickEventEnable = initPageStickEvent();
        if (mPageStickEventEnable && mStickEventList != null) {
            for (Class<? extends PageEvent> eventPage : mStickEventList) {
                EventHelper.registerStickEventPage(this, eventPage);
            }
        }
        LogHelper.i(TAG, "activity: " + this +
                ", mPageEventEnable: " + mPageEventEnable +
                ", mPageStickEventEnable: " + mPageStickEventEnable);
    }

    /**
     * 注销页面监听
     */
    @Override
    protected void onDestroy() {
        // 普通事件监听注销
        if (mPageEventEnable && mEventList != null) {
            for (Class<? extends PageEvent> eventPage : mEventList) {
                EventHelper.unregisterEventPage(this, eventPage);
            }
            mEventList.clear();
            mEventList = null;
        }
        // 粘性事件监听注销
        if (mPageStickEventEnable && mStickEventList != null) {
            for (Class<? extends PageEvent> eventPage : mStickEventList) {
                EventHelper.unregisterStickEventPage(this, eventPage);
            }
            mStickEventList.clear();
            mStickEventList = null;
        }
        super.onDestroy();
    }

    /**
     * 初始化页面事件监听
     * <p>
     *     处理是否开启页面监听，添加监听页面等
     * </p>
     * @return true 开启页面监听
     */
    protected boolean initPageEvent() {
        return false;
    }

    /**
     * 添加监听页面
     * @param eventPage 事件触发页面
     */
    protected void addPageEvent(Class<? extends PageEvent> eventPage) {
        if (eventPage == null) {
            return;
        }
        if (mEventList == null) {
            mEventList = new ArrayList<>();
        }
        if (mEventList.contains(eventPage)) {
            return;
        }
        mEventList.add(eventPage);
    }

    /**
     * 监听页面的事件回调
     * @param event 事件
     */
    @Override
    public void onEvent(@NonNull Event event) {
    }

    /**
     * 初始化页面粘性事件监听
     * <p>
     *     处理是否开启页面粘性事件监听，添加粘性事件监听页面等
     * </p>
     * @return true 开启页面粘性事件监听
     */
    protected boolean initPageStickEvent() {
        return false;
    }

    /**
     * 添加粘性事件监听页面
     * @param eventPage 事件触发页面
     */
    protected void addPageStickEvent(Class<? extends PageEvent> eventPage) {
        if (eventPage == null) {
            return;
        }
        if (mStickEventList == null) {
            mStickEventList = new ArrayList<>();
        }
        if (mStickEventList.contains(eventPage)) {
            return;
        }
        mStickEventList.add(eventPage);
    }

    /**
     * 监听页面的粘性事件回调
     * @param event 事件
     * @return true拦截粘性事件，后续不再传递
     */
    @Override
    public boolean onStickEvent(@NonNull Event event) {
        return false;
    }
}
