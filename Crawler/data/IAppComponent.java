14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_common/src/main/java/com/hl/base_module/appcomponent/IAppComponent.java
package com.hl.base_module.appcomponent;

import android.app.Application;

/**
 * 组件Application初始化时需要实现的接口
 */
public interface IAppComponent {
    void initialize(Application app);
}
