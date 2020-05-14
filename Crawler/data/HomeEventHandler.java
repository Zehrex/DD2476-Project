14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_main/src/main/java/com/hl/modules_main/view/event/HomeEventHandler.java
package com.hl.modules_main.view.event;

import android.view.View;

import com.hl.base_module.handler.BaseHandlers;
import com.hl.base_module.util.app.ToastUtil;
import com.hl.modules_main.view.HomeFragment;
import com.hl.modules_main.databinding.FragmentHomeBinding;

/**
 * 点击事件统一到一个类
 *
 * @Author: hl
 * @Date: created at 2020/3/27 10:43
 * @Description: com.hl.modules_personal.view.event
 */
public class HomeEventHandler extends BaseHandlers<HomeFragment, FragmentHomeBinding> {

    public HomeEventHandler(HomeFragment fragment) {
        super(fragment, fragment.getViewDataBinding());
    }

    /**
     * 名称点击事件
     *
     * @param view
     */
    public void OnClickNameTv(View view) {
        ToastUtil.showTost("主页名称点击事件", true);
    }

    /**
     * 获取首页列表数据
     * @param page
     */
    public void getHomeListData(int page) {
        getBindedView().homeViewModel.getHomeList(page);
    }
}
