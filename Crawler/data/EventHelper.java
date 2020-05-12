1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/pageevent/src/main/java/com/wkp/pageevent/helper/EventHelper.java
package com.wkp.pageevent.helper;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.wkp.pageevent.annotations.EventType;
import com.wkp.pageevent.info.Event;
import com.wkp.pageevent.inter.PageEvent;
import com.wkp.pageevent.inter.PageEventListener;
import com.wkp.pageevent.inter.PageStickEventListener;
import com.wkp.pageevent.log.LogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wkp111 on 2020/5/6.
 * 页面事件监听帮助类
 */
public final class EventHelper {
    private static final String TAG = "EventHelper";
    /**
     * 页面普通事件监听器集合映射管理
     */
    private static final Map<Class<? extends PageEvent>, List<WeakReference<? extends PageEventListener>>> EVENT_LISTENER_MAP = new HashMap<>();
    /**
     * 页面粘性事件监听器集合映射管理
     */
    private static final Map<Class<? extends PageEvent>, List<WeakReference<? extends PageStickEventListener>>> STICK_EVENT_LISTENER_MAP = new HashMap<>();
    /**
     * 页面粘性事件缓存集合映射管理
     */
    private static final Map<Class<? extends PageEvent>, List<Event>> STICK_EVENT_MAP = new HashMap<>();

    private EventHelper() {
    }

    /**
     * 注册某个页面的事件监听
     * @param listener 事件监听器
     * @param eventPage 事件触发页面
     */
    @MainThread
    public static void registerEventPage(@NonNull PageEventListener listener, @NonNull Class<? extends PageEvent> eventPage) {
        List<WeakReference<? extends PageEventListener>> eventList = EVENT_LISTENER_MAP.get(eventPage);
        if (eventList == null) {
            eventList = new ArrayList<>();
            EVENT_LISTENER_MAP.put(eventPage, eventList);
        }
        if (indexEventListener(eventList, listener) == -1) {
            eventList.add(new WeakReference<>(listener));
        }
        LogHelper.i(TAG, "registerEventPage listener: " + listener);
        LogHelper.i(TAG, "registerEventPage eventPage: " + eventPage);
    }

    /**
     * 注销某个页面的事件监听
     * @param listener 监听器
     * @param eventPage 事件触发页面
     */
    @MainThread
    public static void unregisterEventPage(@NonNull PageEventListener listener, @NonNull Class<? extends PageEvent> eventPage) {
        List<WeakReference<? extends PageEventListener>> eventList = EVENT_LISTENER_MAP.get(eventPage);
        if (eventList != null) {
            int index = indexEventListener(eventList, listener);
            if (index != -1) {
                eventList.remove(index);
            }
        }
        LogHelper.i(TAG, "unregisterEventPage listener: " + listener);
        LogHelper.i(TAG, "unregisterEventPage eventPage: " + eventPage);
    }

    /**
     * 页面事件回调
     * @param eventPage 事件触发页面
     * @param eventType 事件类型
     * @param eventParams 事件信息参数
     */
    @MainThread
    public static void onEvent(@NonNull PageEvent eventPage, @EventType int eventType, @Nullable Bundle eventParams) {
        Class<? extends PageEvent> eventPageClass = eventPage.getClass();
        List<WeakReference<? extends PageEventListener>> eventList = EVENT_LISTENER_MAP.get(eventPageClass);
        if (eventList != null) {
            Event event = new Event(new WeakReference<>(eventPage), eventType, eventParams);
            for (WeakReference<? extends PageEventListener> reference : eventList) {
                PageEventListener listener = reference.get();
                if (listener != null) {
                    listener.onEvent(event);
                }
            }
            LogHelper.i(TAG, "onEvent event: " + event);
        }
    }

    /**
     * 注册某个页面的粘性事件监听
     * @param listener 事件监听器
     * @param eventPage 事件触发页面
     */
    @MainThread
    public static void registerStickEventPage(@NonNull PageStickEventListener listener, @NonNull Class<? extends PageEvent> eventPage) {
        List<WeakReference<? extends PageStickEventListener>> eventList = STICK_EVENT_LISTENER_MAP.get(eventPage);
        if (eventList == null) {
            eventList = new ArrayList<>();
            STICK_EVENT_LISTENER_MAP.put(eventPage, eventList);
        }
        if (indexEventListener(eventList, listener) == -1) {
            // 第一次添加这个监听器，查看是否有监听页面的粘性事件，有则回调给它
            List<Event> events = STICK_EVENT_MAP.get(eventPage);
            if (events != null) {
                Iterator<Event> iterator = events.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    boolean interrupt = listener.onStickEvent(event);
                    // 拦截的事件直接移除
                    if (interrupt) {
                        LogHelper.i(TAG, "registerStickEventPage interrupt event: " + event);
                        iterator.remove();
                    }
                }
            }
            eventList.add(new WeakReference<>(listener));
        }
        LogHelper.i(TAG, "registerStickEventPage listener: " + listener);
        LogHelper.i(TAG, "registerStickEventPage eventPage: " + eventPage);
    }

    /**
     * 注销某个页面的粘性事件监听
     * @param listener 监听器
     * @param eventPage 事件触发页面
     */
    @MainThread
    public static void unregisterStickEventPage(@NonNull PageStickEventListener listener, @NonNull Class<? extends PageEvent> eventPage) {
        List<WeakReference<? extends PageStickEventListener>> eventList = STICK_EVENT_LISTENER_MAP.get(eventPage);
        if (eventList != null) {
            int index = indexEventListener(eventList, listener);
            if (index != -1) {
                eventList.remove(index);
            }
        }
        LogHelper.i(TAG, "unregisterStickEventPage listener: " + listener);
        LogHelper.i(TAG, "unregisterStickEventPage eventPage: " + eventPage);
    }

    /**
     * 页面事件回调
     * @param eventPage 事件触发页面
     * @param eventType 事件类型
     * @param eventParams 事件信息参数
     */
    @MainThread
    public static void onStickEvent(@NonNull PageEvent eventPage, @EventType int eventType, @Nullable Bundle eventParams) {
        Class<? extends PageEvent> eventPageClass = eventPage.getClass();
        Event event = new Event(new WeakReference<>(eventPage), eventType, eventParams);
        // 缓存粘性事件
        List<Event> events = STICK_EVENT_MAP.get(eventPageClass);
        if (events == null) {
            events = new ArrayList<>();
            STICK_EVENT_MAP.put(eventPageClass, events);
        }
        events.add(event);
        // 回调粘性事件
        List<WeakReference<? extends PageStickEventListener>> eventList = STICK_EVENT_LISTENER_MAP.get(eventPageClass);
        if (eventList != null) {
            for (WeakReference<? extends PageStickEventListener> reference : eventList) {
                PageStickEventListener listener = reference.get();
                if (listener != null) {
                    // 回调粘性事件，是否拦截粘性事件，拦截则不再继续传递
                    boolean interrupt = listener.onStickEvent(event);
                    if (interrupt) {
                        LogHelper.i(TAG, "onStickEvent interrupt listener: " + listener);
                        events.remove(event);
                        break;
                    }
                }
            }
        }
        LogHelper.i(TAG, "onStickEvent event: " + event);
    }

    /**
     * 清除某个页面的所有粘性事件
     * @param eventPage 事件页面
     */
    @MainThread
    public static void clearPageStickEvent(Class<? extends PageEvent> eventPage) {
        List<Event> events = STICK_EVENT_MAP.get(eventPage);
        if (events != null) {
            events.clear();
        }
        STICK_EVENT_MAP.remove(eventPage);
        LogHelper.i(TAG, "clearPageStickEvent eventPage: " + eventPage);
    }

    /**
     * 释放所有资源
     */
    @MainThread
    public static void release() {
        LogHelper.i(TAG, "release clear all event");
        clearAllPageStickEvent();
        clearAllPageEventListener();
        clearAllPageStickEventListener();
    }

    /**
     * 清空所有的普通事件监听器
     */
    private static void clearAllPageEventListener() {
        Set<Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageEventListener>>>> entries = EVENT_LISTENER_MAP.entrySet();
        Iterator<Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageEventListener>>>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageEventListener>>> entry = iterator.next();
            List<WeakReference<? extends PageEventListener>> value = entry.getValue();
            if (value != null) {
                value.clear();
            }
            iterator.remove();
        }
    }

    /**
     * 清空所有的粘性事件监听器
     */
    private static void clearAllPageStickEventListener() {
        Set<Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageStickEventListener>>>> entries = STICK_EVENT_LISTENER_MAP.entrySet();
        Iterator<Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageStickEventListener>>>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Class<? extends PageEvent>, List<WeakReference<? extends PageStickEventListener>>> entry = iterator.next();
            List<WeakReference<? extends PageStickEventListener>> value = entry.getValue();
            if (value != null) {
                value.clear();
            }
            iterator.remove();
        }
    }

    /**
     * 清空所有的粘性事件
     */
    private static void clearAllPageStickEvent() {
        Set<Map.Entry<Class<? extends PageEvent>, List<Event>>> entries = STICK_EVENT_MAP.entrySet();
        Iterator<Map.Entry<Class<? extends PageEvent>, List<Event>>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Class<? extends PageEvent>, List<Event>> entry = iterator.next();
            List<Event> value = entry.getValue();
            if (value != null) {
                value.clear();
            }
            iterator.remove();
        }
    }

    /**
     * 事件监听集合中查找某个监听器的索引
     * @param eventList 监听器集合
     * @param listener 监听器
     * @return -1 未找到，其他找到索引
     */
    private static <T> int indexEventListener(List<WeakReference<? extends T>> eventList, T listener) {
        if (eventList == null || listener == null) {
            return -1;
        }
        for (int i = 0; i < eventList.size(); i++) {
            WeakReference<? extends T> reference = eventList.get(i);
            T eventListener = reference.get();
            if (eventListener == listener || listener.equals(eventListener)) {
                return i;
            }
        }
        return -1;
    }
}
