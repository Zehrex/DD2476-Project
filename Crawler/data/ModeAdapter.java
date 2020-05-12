2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/adapter/ModeAdapter.java
package com.mediaroom.adapter;

import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.widget.TextView;

import com.mediaroom.MyApplication;
import com.mediaroom.R;
import com.mediaroom.bean.UserMode;
import com.mediaroom.utils.BaseAdapter;
import com.mediaroom.utils.Constant;

import java.util.List;

public class ModeAdapter extends BaseAdapter<UserMode> {

    public ModeAdapter(List<UserMode> datas) {
        super(datas);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_pop_mode;
    }


    @Override
    public void convert(VH holder, UserMode data, int position) {
        TextView popTv = holder.getView(R.id.item_pop_tv);
        switch (data.getModeType()) {
            case Constant.LIVE_MODETYPE:
                popTv.setGravity(Gravity.CENTER);
                break;
            case Constant.MEMBERS_MODETYPE:
                popTv.setGravity(Gravity.CENTER | Gravity.LEFT);
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins((int) (MyApplication.getInstance().getResources().getDisplayMetrics().density * 17 + 0.5f), 0, 0, 0);
                popTv.setLayoutParams(layoutParams);
                break;
        }
        popTv.setText(data.getModeTip());
    }
}