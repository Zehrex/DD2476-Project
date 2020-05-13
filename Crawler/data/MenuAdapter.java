2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/adapter/MenuAdapter.java
package com.mediaroom.adapter;

import android.content.Context;

import com.mediaroom.R;
import com.mediaroom.base.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * Menu Adapter
 *
 * ZH:
 * 菜单适配器
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019年12月18日
 */
public class MenuAdapter extends BaseAdapter<String> {

    private Context context;

    public MenuAdapter(List<String> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.layout_menu_item;
    }

    @Override
    public void onCreateViewHolder(@NotNull VH holder, int viewType) {

    }

    @Override
    public void onBindViewHolder(VH holder, String data, int position) {
        holder.setText(R.id.tv, data);
    }
}
