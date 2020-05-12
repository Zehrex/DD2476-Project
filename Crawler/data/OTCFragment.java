1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/fragment/otc/OTCFragment.java
package com.hjq.demo.ui.fragment.otc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.hjq.demo.R;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.otc.FundingRecordsActivity;
import com.hjq.demo.ui.adapter.otc.TabPagerAdapter;
import com.hjq.demo.view.magicindicator.MagicIndicator;
import com.hjq.demo.view.magicindicator.ViewPagerHelper;
import com.hjq.demo.view.magicindicator.buildins.UIUtil;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.titles.ColorFlipPagerTitleView;
import com.hjq.demo.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.hjq.demo.widget.XCollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTCFragment extends MyFragment<HomeActivity>
        implements XCollapsingToolbarLayout.OnScrimsListener  {

    @BindView(R.id.magicIndicator)
    MagicIndicator mIndicator;

    @BindView(R.id.view_page)
    ViewPager mFrgVP;

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFrgs = new ArrayList<>();

    private int mCurVPPosition;
    private TabPagerAdapter mFrgAdapter;

    private OTCBuyFragment mOTCBugFragment;
    private OTCSellFragment mOTCSellFragment;

    public static OTCFragment newInstance() {
        return new OTCFragment();
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.otc_fragment, container, false);
//        ButterKnife.bind(this, view);
//
//
//        return view;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.otc_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mTitles.add(getString(R.string.buy));
        mTitles.add(getString(R.string.sell));
        initMagicIndicator(mTitles);
        setViewPage();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    private void initMagicIndicator(final List<String> titles) {
        mIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(titles.get(index));
                simplePagerTitleView.setTextSize(UIUtil.dip2px(context, 16));//设置导航的文字大小
                simplePagerTitleView.setMaxLines(1);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.color_000000));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.color_000000));

                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         mFrgVP.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 5));
                indicator.setTop(UIUtil.dip2px(context, 10));
                indicator.setColors(ContextCompat.getColor(context, R.color.color_318AFD));
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mFrgVP);
    }

    public void setViewPage() {
        mFrgs.clear();
        Bundle homeBundle = new Bundle();
        mOTCBugFragment = new OTCBuyFragment();
        mOTCBugFragment.setArguments(homeBundle);
        mFrgs.add(mOTCBugFragment);

        Bundle homeBundle1 = new Bundle();
        mOTCSellFragment = new OTCSellFragment();
        mOTCSellFragment.setArguments(homeBundle1);
        mFrgs.add(mOTCSellFragment);

        mFrgAdapter = new TabPagerAdapter(getChildFragmentManager(), mFrgs, mTitles);
        mFrgVP.setAdapter(mFrgAdapter);
        mFrgVP.setOffscreenPageLimit(mTitles.size());


        mFrgVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurVPPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.funding_records})
    public void OnClick(){
        Intent intent = new Intent(getContext(), FundingRecordsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);

    }

    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {

    }
}
