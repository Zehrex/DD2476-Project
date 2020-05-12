2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/adapter/RoomAdapter.java
package com.mediaroom.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.mediaroom.R;
import com.mediaroom.base.BaseAdapter;
import com.mediaroom.bean.UserInfo;
import com.mediaroom.utils.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Homepage View Adapter
 *
 * ZH：
 * 首页视图适配器
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019年12月18日
 */
public class RoomAdapter extends BaseAdapter<UserInfo> {

    private Map<String, UserInfo> maps = new HashMap<>();

    public RoomAdapter(List<UserInfo> datas, Context context) {
        super(datas);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_member;
    }

    @Override
    public void onCreateViewHolder(@NotNull VH holder, int viewType) {
        ImageView ivVolume = holder.getView(R.id.item_iv_volume);
        ivVolume.setOnClickListener(
                view -> onItemClickListener.onItemClick(view, holder.getAdapterPosition()));
    }

    @Override
    public void onBindViewHolder(@NotNull VH holder, UserInfo data, int position) {
        holder.setText(R.id.item_tv_uid,
                data.getUid() + (Constant.mLocalUid.equals(data.getUid()) ? "(我)" : ""));

        ImageView ivVolume = holder.getView(R.id.item_iv_volume);
        if (data.isAudioStreamStopped() || data.isMuteAudio()) {
            ivVolume.setImageResource(R.mipmap.img_audiostop3x);
        } else {
            if (data.getMicVolume() <= 30) {
                ivVolume.setImageResource(R.mipmap.img_audiovolume_level1);
            } else if (data.getMicVolume() <= 60) {
                ivVolume.setImageResource(R.mipmap.img_audiovolume_level2);
            } else {
                ivVolume.setImageResource(R.mipmap.img_audiovolume_level3);
            }
        }
    }

    public UserInfo getUserInfo(String targetUID) {
        return maps.get(targetUID);
    }

    @Override
    public void addItem(int postion, UserInfo data) {
        super.addItem(postion, data);
        maps.put(data.getUid(), data);
    }

    @Override
    public void addItem(UserInfo data) {
        super.addItem(data);
        maps.put(data.getUid(), data);
    }

    @Override
    public void deleteItem(UserInfo data) {
        super.deleteItem(data);
        maps.remove(data.getUid());
    }

    @Override
    public void clear() {
        super.clear();
        maps.clear();
    }
}
