14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_common/src/main/java/com/hl/base_module/adapter/BaseMulViewHolder.java
package com.hl.base_module.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

/**
 * Created by hl on 2018/3/14.
 */

public class BaseMulViewHolder<T extends BaseMulDataModel> extends RecyclerView.ViewHolder {
    private View itemView;
    private SparseArray<View> views = new SparseArray<>();
    // 创建Viewholder时用作临时存储一些适配器变量，一些全局值；
    // 重新绑定数据时用来刷新视图，不再重写创建
    private HashMap<String, Object> tempStorage = new HashMap<>();

    public BaseMulViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    /**
     * 添加临时全局存储
     *
     * @param baseHolderName
     * @param moreForView
     */
    public void putStorage(String baseHolderName, Object moreForView) {
        tempStorage.put(baseHolderName, moreForView);
    }

    /**
     * 获取存储的控件，适配器或者其他全局变量
     *
     * @param baseHolderName
     * @return
     */
    public Object getStorage(String baseHolderName) {
        if (tempStorage.containsKey(baseHolderName)) {
            return tempStorage.get(baseHolderName);
        }
        return null;
    }

    /**
     * 获取视图
     *
     * @return
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * 获取子控件
     *
     * @param id
     * @param <V>
     * @return
     */
    public <V extends View> V getView(int id) {
        View view = views.get(id);
        if (null == view) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }

    /**
     * 设置文本 - 字符串
     *
     * @param viewId
     * @param content
     * @return
     */
    public BaseMulViewHolder setText(int viewId, String content) {
        ((TextView) getView(viewId)).setText(content);
        return this;
    }

    /**
     * 设置文本 - 字符串
     *
     * @param viewId
     * @param content
     * @return
     */
    public BaseMulViewHolder setText(int viewId, SpannableString content) {
        ((TextView) getView(viewId)).setText(content);
        return this;
    }

    /**
     * 设置文本 - 资源
     *
     * @param viewId
     * @param resId
     * @return
     */
    public BaseMulViewHolder setText(int viewId, int resId) {
        ((TextView) getView(viewId)).setText(resId);
        return this;
    }

    /**
     * 设置文本颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public BaseMulViewHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    /**
     * 设置文本颜色
     *
     * @param viewId
     * @param colorId
     * @return
     */
    public BaseMulViewHolder setTextColor(Context context, int viewId, int colorId) {
        ((TextView) getView(viewId)).setTextColor(context.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置文本 - 可见性
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public BaseMulViewHolder setVisible(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 设置可用性
     *
     * @param viewId
     * @param bEnable
     */
    public void setEnable(int viewId, boolean bEnable) {
        getView(viewId).setEnabled(bEnable);
    }
}
