1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/otc/FundingRecordsActivity.java
package com.hjq.demo.ui.activity.otc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.demo.R;
import com.hjq.demo.bean.FundRecordBean;
import com.hjq.demo.ui.adapter.otc.FundRecordAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FundingRecordsActivity extends AppCompatActivity {

    @BindView(R.id.back_img)
    ImageView mBackImg;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private FundRecordAdapter mAdapter;
    private List<FundRecordBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_record);
        ButterKnife.bind(this);

        mAdapter = new FundRecordAdapter(this, mList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mAdapter);
        getData();
    }

    @OnClick({R.id.back_img})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_img:
                onBackPressed();
                break;
        }
    }

    public void getData() {
        for (int i = 0; i < 10; i++) {
            FundRecordBean ecommendBean = new FundRecordBean();
            ecommendBean.setNum(100);
            ecommendBean.setStatus(i);
            ecommendBean.setMessage("买入");
            ecommendBean.setTime(1588685332362L);
            Date currentTime = new Date(1588685332362L);
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = formatter1.format(currentTime);
            ecommendBean.setTitle(dateStr);
            mList.add(ecommendBean);
        }
        mAdapter.setUpdate(mList);
    }
}
