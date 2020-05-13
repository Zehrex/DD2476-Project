1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/fragment/contract/ContractFragment.java
package com.hjq.demo.ui.fragment.contract;

import android.graphics.drawable.ColorDrawable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.bean.contract.ClearingBean;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.view.magicindicator.buildins.UIUtil;
import com.hjq.demo.ui.adapter.contract.ClearingDropDownAdapter;
import com.hjq.demo.widget.ContractKindPopWindow;
import com.hjq.widget.view.SwitchButton;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *    首页-合约fragment
 *    zilong
 */
public final class ContractFragment extends MyFragment<HomeActivity>{

    @BindView(R.id.tv_kind)
    TextView mTextKind;

    @BindView(R.id.ll_kind)
    LinearLayout mLLKind;

    @BindView(R.id.tv_clearing)
    TextView mTextClearing;

    private ContractKindPopWindow mContractKindPopWindow;

    public static ContractFragment newInstance() {
        return new ContractFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initView() {
        mContractKindPopWindow=new ContractKindPopWindow(MyApplication.getApplication());
    }

    @Override
    protected void initData() {
        mTextKind.setText("BTC/USDT永续");
        setClick();

    }

    private void setClick() {
        RxView.clicks(mLLKind).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(Object o) {
                mContractKindPopWindow.showContractKindPopWindow(mLLKind,0,0);
            }
            @Override
            public void onError(Throwable e) {}
            @Override
            public void onComplete() {}
        });
    }

}