14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_refreshlayout/src/main/java/com/hl/lib_refreshlayout/handler/RefreshListenner.java
package com.hl.lib_refreshlayout.handler;

/**
 * 刷新相关状态回调
 */
public interface RefreshListenner {
    void OnRefresh();

    void OnRefreshFinish();

    void OnLoadMore();

    void OnLoadMoreFinish();
}
