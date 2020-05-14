15
https://raw.githubusercontent.com/Florizt/RxMVVM/master/rxmvvmlib/src/main/java/com/rx/rxmvvmlib/base/IBaseView.java
package com.rx.rxmvvmlib.base;

import android.os.Bundle;


public interface IBaseView {
    /**
     * 初始化界面传递参数
     */
    void initParam();

    /**
     * 初始化界面
     */
    void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
