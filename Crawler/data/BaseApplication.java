9
https://raw.githubusercontent.com/TrillGates/TaobaoUnion/master/app/src/main/java/com/sunofbeaches/taobaounion/base/BaseApplication.java
package com.sunofbeaches.taobaounion.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
    }


    public static Context getAppContext() {
        return appContext;
    }
}
