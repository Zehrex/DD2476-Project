2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/adapter/RoomMemberAdapter.java
package com.mediaroom.adapter;


import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.hummer.im.HMR;
import com.mediaroom.R;
import com.mediaroom.bean.UserInfo;
import com.mediaroom.facade.HummerManager;
import com.mediaroom.utils.BaseAdapter;
import com.mediaroom.utils.LogUtil;

import java.util.List;


public class RoomMemberAdapter extends BaseAdapter<UserInfo> {
    private static final int TYPE_ITEM_MEMBER = 1;
    private static final int TYPE_ITEM_MEMBER_OWNER = 2;

    private Context context;

    public RoomMemberAdapter(List<UserInfo> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        int layoutId = 0;
        switch (viewType) {
            case TYPE_ITEM_MEMBER:
                layoutId = R.layout.item_member;
                break;
            case TYPE_ITEM_MEMBER_OWNER:
                layoutId = R.layout.item_member_owner;
                break;
        }
        return layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_MEMBER_OWNER;
        }
        return TYPE_ITEM_MEMBER;
    }

    @Override
    public void convert(VH holder, UserInfo userInfo, int position) {
        LogUtil.d("zhaochong", " rv  position : " + position);
        if ("owner".equals(userInfo.getRole())) {
            holder.setText(R.id.item_tv_uid, "房主:" + userInfo.getUid() + (HummerManager.isOwner ? "(我)" : ""));
        } else {
            holder.setText(R.id.item_tv_uid, "观众:" + userInfo.getUid() + (userInfo.getUid() == HMR.getMe().getId() ? "(我)" : ""));
        }

        if (position == 0) {
            Button btnCloseRoom = holder.getView(R.id.btn_closeroom);
            btnCloseRoom.setVisibility(HummerManager.isOwner ? View.VISIBLE : View.GONE);
            btnCloseRoom.setOnClickListener(view -> onItemChildClickListener.onItemChildClick(view, position));
        } else {
            if (HummerManager.isOwner) {
                Button btnMuted = holder.getView(R.id.btn_muted);
                Button btnKicked = holder.getView(R.id.btn_kicked);
                btnMuted.setVisibility(View.VISIBLE);
                btnKicked.setVisibility(View.VISIBLE);
                btnMuted.setOnClickListener(view -> onItemChildClickListener.onItemChildClick(view, position));
                btnKicked.setOnClickListener(view -> onItemChildClickListener.onItemChildClick(view, position));

                btnMuted.setText(userInfo.isMuted() ? "恢复发言" : "禁言");
                btnMuted.setBackground(context.getResources().getDrawable(userInfo.isMuted() ? R.drawable.bg_btn_blue : R.drawable.bg_btn_blue_e0));
                btnMuted.setTextColor(context.getResources().getColor(userInfo.isMuted() ? R.color.white95 : R.color.blue_font));
            }
        }
    }

}
