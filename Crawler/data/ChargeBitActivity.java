1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/ChargeBitActivity.java
package com.hjq.demo.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.util.QRCodeUtil;

public class ChargeBitActivity extends MyActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_charge_bit;
    }
    @Override
    protected void initView() {
        ImageView ivQR = findViewById(R.id.id_iv_qr);

        Bitmap bitmap = QRCodeUtil.createQRCode("663536qeuu7463847hfoodjt733401");
        Drawable drawable = new BitmapDrawable(bitmap);
        ivQR.setImageDrawable(drawable);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] chargeType = getResources().getStringArray(R.array.charge_type);
                Toast.makeText(ChargeBitActivity.this, "你点击的是:"+chargeType[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_bit);
        ImageView ivQR = findViewById(R.id.id_iv_qr);

        Bitmap bitmap = QRCodeUtil.createQRCode("663536qeuu7463847hfoodjt733401");
        Drawable drawable = new BitmapDrawable(bitmap);
        ivQR.setImageDrawable(drawable);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] chargeType = getResources().getStringArray(R.array.charge_type);
                Toast.makeText(ChargeBitActivity.this, "你点击的是:"+chargeType[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }
}
