1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/activity/otc/DetailedRecordActivity.java
package com.hjq.demo.ui.activity.otc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hjq.demo.R;

import butterknife.ButterKnife;

public class DetailedRecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_record);
        ButterKnife.bind(this);
    }
}
