1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/fragment/otc/OTCBuyFragment.java
package com.hjq.demo.ui.fragment.otc;

import android.content.Intent;
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
import com.hjq.demo.ui.activity.otc.OrderPayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTCBuyFragment extends Fragment {

    @BindView(R.id.bt_buy_now)
    Button mBtBuyNow;

    @BindView(R.id.usdt_ed)
    EditText mUsdtEdit;
    @BindView(R.id.cny_ed)
    TextView mCnyTV;

    private List<String> mTitles = new ArrayList<>();

    public static OTCBuyFragment newInstance() {
        return new OTCBuyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.otc_buy_fragment, container, false);
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
        Intent intent = new Intent(getContext(), OrderPayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);

    }

}
