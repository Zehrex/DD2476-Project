1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/fragment/otc/OTCSellFragment.java
package com.hjq.demo.ui.fragment.otc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.hjq.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTCSellFragment extends Fragment {

    @BindView(R.id.bt_buy_now)
    Button mBtBuyNow;

    @BindView(R.id.usdt_ed)
    EditText mUsdtEdit;
    @BindView(R.id.cny_ed)
    TextView mCnyTV;

    private List<String> mTitles = new ArrayList<>();

    public static OTCSellFragment newInstance() {
        return new OTCSellFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.otc_sell_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }


    @OnClick(R.id.bt_buy_now)
    public void OnClick(){

    }

}
