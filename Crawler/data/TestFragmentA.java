1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/fragment/TestFragmentA.java
package com.hjq.demo.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.ui.activity.ChargeBitActivity;
import com.hjq.demo.ui.activity.GetBitActivity;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.TransferActivity;
import com.hjq.demo.ui.adapter.FortuneRecordAdapter;
import com.hjq.demo.widget.XCollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 资产界面
 */
public final class TestFragmentA extends MyFragment<HomeActivity>
        implements XCollapsingToolbarLayout.OnScrimsListener, View.OnClickListener {


    private LinearLayout mLLayoutFrance, mLLayoutDeal;
    private int selectedTextColor, unselectedTextColor;
    private ImageView mIvFrance, mIvDeal;
    private RecyclerView rvRecord;
    private TextView mTvChargeBit, mTvGetBit;
    private LinearLayout mLLayoutTransfer;
//    @BindView(R.id.ctl_test_bar)
//    XCollapsingToolbarLayout mCollapsingToolbarLayout;
//    @BindView(R.id.t_test_title)
//    Toolbar mToolbar;
//
//    @BindView(R.id.tv_test_address)
//    TextView mAddressView;
//    @BindView(R.id.tv_test_hint)
//    TextView mHintView;
//    @BindView(R.id.iv_test_search)
//    ImageView mSearchView;

    public static TestFragmentA newInstance() {
        return new TestFragmentA();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fortune;
    }

    @Override
    protected void initView() {
//        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
//        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);
//
//        //设置渐变监听
//        mCollapsingToolbarLayout.setOnScrimsListener(this);

        mLLayoutFrance = findViewById(R.id.id_llayout_france);
        mLLayoutDeal = findViewById(R.id.id_llayout_deal);
        selectedTextColor = getResources().getColor(R.color.selectedTextColor);
        unselectedTextColor = getResources().getColor(R.color.unselectedTextColor);

        mIvFrance = findViewById(R.id.id_iv_france_arrow);
        mIvDeal = findViewById(R.id.id_iv_deal_arrow);

        rvRecord = findViewById(R.id.id_rv_record);
        mTvChargeBit = findViewById(R.id.id_tv_charge_bit);
        mTvGetBit = findViewById(R.id.id_tv_get_bit);
        mLLayoutTransfer = findViewById(R.id.id_llayout_transfer);

        List<String> mList = new ArrayList<String>();
        mList.add("充值");
        mList.add("法币账户划转资金到合约账户");
        mList.add("充值");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvRecord.setLayoutManager(layoutManager);
        FortuneRecordAdapter adapter = new FortuneRecordAdapter(getActivity(), mList);
        rvRecord.setAdapter(adapter);

        mLLayoutFrance.setOnClickListener(this);
        mLLayoutDeal.setOnClickListener(this);
        mTvChargeBit.setOnClickListener(this);
        mTvGetBit.setOnClickListener(this);
        mLLayoutTransfer.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_llayout_france:
                //法币账户
                mLLayoutFrance.setBackgroundResource(R.drawable.selected_fortune_bg);
                ((TextView) mLLayoutFrance.getChildAt(0)).setTextColor(selectedTextColor);
                ((TextView) mLLayoutFrance.getChildAt(1)).setTextColor(selectedTextColor);

                mLLayoutDeal.setBackgroundResource(R.drawable.unselected_fortune_bg);
                ((TextView) mLLayoutDeal.getChildAt(0)).setTextColor(unselectedTextColor);
                ((TextView) mLLayoutDeal.getChildAt(1)).setTextColor(unselectedTextColor);

                mIvFrance.setVisibility(View.VISIBLE);
                mIvDeal.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_llayout_deal:
                //合约账户
                mLLayoutFrance.setBackgroundResource(R.drawable.unselected_fortune_bg);
                ((TextView) mLLayoutFrance.getChildAt(0)).setTextColor(unselectedTextColor);
                ((TextView) mLLayoutFrance.getChildAt(1)).setTextColor(unselectedTextColor);

                mLLayoutDeal.setBackgroundResource(R.drawable.selected_fortune_bg);
                ((TextView) mLLayoutDeal.getChildAt(0)).setTextColor(selectedTextColor);
                ((TextView) mLLayoutDeal.getChildAt(1)).setTextColor(selectedTextColor);

                mIvFrance.setVisibility(View.INVISIBLE);
                mIvDeal.setVisibility(View.VISIBLE);
                break;
            case R.id.id_tv_charge_bit:
                //充币
                Intent intent = new Intent(getActivity(), ChargeBitActivity.class);
                startActivity(intent);
                break;
            case R.id.id_tv_get_bit:
                //提币
                Intent intent2 = new Intent(getActivity(), GetBitActivity.class);
                startActivity(intent2);
                break;
            case R.id.id_llayout_transfer:
                //资产划转
                Intent intent3 = new Intent(getActivity(), TransferActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean statusBarDarkFont() {
//        return mCollapsingToolbarLayout.isScrimsShown();
        return false;
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     * <p>
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
//        if (shown) {
//            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
//            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
//            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black60));
//            mSearchView.setImageResource(R.drawable.ic_search_black);
//            getStatusBarConfig().statusBarDarkFont(true).init();
//        } else {
//            mAddressView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
//            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
//            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
//            mSearchView.setImageResource(R.drawable.ic_search_white);
//            getStatusBarConfig().statusBarDarkFont(false).init();
//        }
    }
}