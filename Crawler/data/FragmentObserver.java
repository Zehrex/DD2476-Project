14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_common/src/main/java/com/hl/base_module/page/observer/FragmentObserver.java
package com.hl.base_module.page.observer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class FragmentObserver implements DefaultLifecycleObserver {
    private Context context;

    public FragmentObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }
}
