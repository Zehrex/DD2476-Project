2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/adapter/SoundEffectAdapter.java
package com.mediaroom.adapter;

import android.view.View;
import android.widget.ImageView;

import com.mediaroom.R;
import com.mediaroom.base.BaseAdapter;
import com.mediaroom.bean.SoundMode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * Audio list adapter
 *
 * ZH：
 * 声音列表适配器
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019年12月18日
 */
public class SoundEffectAdapter extends BaseAdapter<SoundMode> {

    private int selecteIndex = -1;

    public SoundEffectAdapter(List<SoundMode> data) {
        super(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_sound_effect;
    }

    @Override
    public void onCreateViewHolder(@NotNull VH holder, int viewType) {

    }

    @Override
    public void onBindViewHolder(@NotNull VH holder, SoundMode data, int position) {
        holder.setText(R.id.tvText, data.getName());
        if (position == 0) {
            holder.setText(R.id.tvType, "");
        } else {
            holder.setText(R.id.tvType, position <= 9 ? "（音效）" : "（变声）");
        }

        ImageView selected = holder.getView(R.id.ivSelected);
        selected.setVisibility(selecteIndex == position ? View.VISIBLE : View.GONE);
    }

    public void selecte(int position) {
        if (position == selecteIndex) {
            return;
        }

        int old = this.selecteIndex;
        this.selecteIndex = position;
        notifyItemChanged(old);
        notifyItemChanged(selecteIndex);
    }
}