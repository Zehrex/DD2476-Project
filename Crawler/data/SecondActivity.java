1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/app/src/main/java/com/wkp/pageeventdemo/SecondActivity.java
package com.wkp.pageeventdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wkp.pageevent.base.EventAppCompatActivity;
import com.wkp.pageevent.helper.EventHelper;
import com.wkp.pageevent.info.Event;

/**
 * Created by wkp111 on 2020/5/6.
 */

public class SecondActivity extends EventAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected boolean initPageEvent() {
        addPageEvent(MainActivity.class);
        return true;
    }

    @Override
    protected boolean initPageStickEvent() {
        addPageStickEvent(MainActivity.class);
        return true;
    }

    @Override
    public void onEvent(@NonNull Event event) {
        int eventType = event.getEventType();
        Bundle eventParams = event.getEventParams();
        // 处理结束回调
        if (eventType == Constants.EVENT_TYPE_END) {
            if (eventParams != null) {
                String string = eventParams.getString(Constants.EVENT_PARAM_END);
                Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onStickEvent(@NonNull Event event) {
        int eventType = event.getEventType();
        Bundle eventParams = event.getEventParams();
        if (eventType == Constants.STICK_EVENT_FIRST) {
            String string = eventParams.getString(Constants.STICK_EVENT_PARAM_FIRST);
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        } else if (eventType == Constants.STICK_EVENT_SECOND) {
            String string = eventParams.getString(Constants.STICK_EVENT_PARAM_SECOND);
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void testEventPage(View view) {
        Bundle eventParams = new Bundle();
        eventParams.putString(Constants.EVENT_PARAM_START, "开始普通事件回调");
        EventHelper.onEvent(this, Constants.EVENT_TYPE_START, eventParams);
    }
}
