1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/TransferActivity.java
package com.hjq.demo.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;

import butterknife.BindView;

/**
 * 划转界面
 */
public class TransferActivity extends MyActivity {

    @BindView(R.id.id_rlayout_switch)
    RelativeLayout mRLayoutSwitch;
    @BindView(R.id.id_tv_capital)
    TextView mTvCapital;
    @BindView(R.id.id_tv_deal)
    TextView mTvDeal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transfer;
    }


    @Override
    protected void initView() {
        mRLayoutSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mTvCapital.getText().toString();
                mTvCapital.setText(mTvDeal.getText().toString());
                mTvDeal.setText(content);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
