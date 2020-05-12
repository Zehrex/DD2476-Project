1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/widget/ContractKindPopWindow.java
package com.hjq.demo.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.bean.contract.ClearingBean;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.ui.adapter.contract.ClearingDropDownAdapter;
import com.hjq.demo.view.magicindicator.buildins.UIUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ContractKindPopWindow extends PopupWindow {
    private Context mContext;
    public ContractKindPopWindow(Context context) {
        mContext=context;
        this.setContentView(initView());
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(UIUtil.getColor(mContext,R.color.white));
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(true);
    }

    private View initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_contract_kind_pop_window, null);
        ListView listView= (ListView) view.findViewById(R.id.lv_kind);
        ImageView iv_up= (ImageView) view.findViewById(R.id.iv_up);
        //TODO test start
        List<ClearingBean> mDatas=new ArrayList<>();
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        mDatas.add(new ClearingBean(1,"BTC/USDT","永续","25533.5","+0.33%"));
        //TODO test end
        listView.setAdapter(new ClearingDropDownAdapter(mContext,mDatas));
        RxView.clicks(iv_up).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onNext(Object o) {
                        dismiss();
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    public void showContractKindPopWindow(View anchor, int xoff, int yoff){
        this.showAsDropDown(anchor,xoff,yoff);
    }
}
