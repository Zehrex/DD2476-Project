2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/adapter/ChatMessageAdapter.java
package com.mediaroom.adapter;


import android.content.Context;
import android.widget.TextView;

import com.mediaroom.R;
import com.mediaroom.bean.ChatMessage;
import com.mediaroom.utils.BaseAdapter;
import com.mediaroom.utils.LogUtil;

import java.util.List;


public class ChatMessageAdapter extends BaseAdapter<ChatMessage> {
    public static final int TYPE_ITEM_CHATMESSAGE_OTHER = 1;
    public static final int TYPE_ITEM_CHATMESSAGE_TIP = 2;


    private Context context;

    public ChatMessageAdapter(List<ChatMessage> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        int layoutId = 0;
        switch (viewType) {
            case TYPE_ITEM_CHATMESSAGE_OTHER:
                layoutId = R.layout.item_chatmessage;
                break;
            case TYPE_ITEM_CHATMESSAGE_TIP:
                layoutId = R.layout.item_chatmessage_tip;
                break;
        }
        return layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return getData().get(position).getMsgType();
    }

    @Override
    public void convert(VH holder, ChatMessage data, int position) {
        LogUtil.d("zhaochong", " rv  position : " + position);
        switch (data.getMsgType()) {
            case TYPE_ITEM_CHATMESSAGE_OTHER:
                handleContent(holder, data);
                break;
            case TYPE_ITEM_CHATMESSAGE_TIP:
                holder.setText(R.id.tv_chatmessage_tip, data.getMessage());
                break;
        }
    }

    private void handleContent(VH holder, ChatMessage data) {
        TextView charmessage = holder.getView(R.id.tv_chatmessage);
        charmessage.setText(data.getUid() + ": " + data.getMessage());
    }
}
