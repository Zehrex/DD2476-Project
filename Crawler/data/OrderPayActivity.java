1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/otc/OrderPayActivity.java
package com.hjq.demo.ui.activity.otc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPayActivity extends AppCompatActivity {

    @BindView(R.id.back_img)
    ImageView mBackImg;
    @BindView(R.id.title_lay)
    RelativeLayout mTitleLay;
    @BindView(R.id.buy_in)
    TextView mBuyIn;
    @BindView(R.id.order_num_tv)
    TextView mOrderNumTv;
    @BindView(R.id.order_num_lin)
    RelativeLayout mOrderNumLin;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.quantity_tv)
    TextView mQuantityTv;
    @BindView(R.id.paid_amount_tv)
    TextView mPaidAmountTv;
    @BindView(R.id.copy_paid_amount)
    TextView mCopyPaidAmount;
    @BindView(R.id.buy_in_tip)
    TextView mBuyInTip;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.copy_name)
    TextView mCopyName;
    @BindView(R.id.pay_platform_tv)
    TextView mPayPlatformTv;
    @BindView(R.id.account_tv)
    TextView mAccountTv;
    @BindView(R.id.qr_code_img)
    ImageView mQrCodeImg;
    @BindView(R.id.order_tip)
    TextView mOrderTip;
    @BindView(R.id.minute)
    TextView mMinute;
    @BindView(R.id.second)
    TextView mSecond;
    @BindView(R.id.rmb)
    TextView mRmb;
    @BindView(R.id.bt_buy_cancel)
    TextView mBtBuyCancel;
    @BindView(R.id.bt_buy)
    TextView mBtBuy;
    @BindView(R.id.buttom_bt)
    LinearLayout mButtomBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_img, R.id.copy_account_name, R.id.copy_paid_amount, R.id.copy_name , R.id.bt_buy_cancel, R.id.bt_buy, R.id.buttom_bt})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.buy_in:
                break;
            case R.id.copy_paid_amount:
                break;
            case R.id.copy_name:
                break;
            case R.id.bt_buy_cancel:
                break;
            case R.id.buttom_bt:
                break;
        }
    }
}
