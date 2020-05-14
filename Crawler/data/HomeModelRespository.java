14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_main/src/main/java/com/hl/modules_main/model/respository/HomeModelRespository.java
package com.hl.modules_main.model.respository;

import com.hl.lib_network.controller.presenter.BaseControlPresenter;
import com.hl.lib_network.net.response.TypeCallBack;
import com.hl.modules_main.model.bean.IHomeModel;
import com.hl.modules_main.model.bean.HomeBean;

public class HomeModelRespository implements IHomeModel {
    private BaseControlPresenter baseControlPresenter;

    public HomeModelRespository(BaseControlPresenter baseControlPresenter) {
        this.baseControlPresenter = baseControlPresenter;
    }

    @Override
    public void getHomeList(int page) {
        if (null == baseControlPresenter) {
            throw new NullPointerException("请求服务未创建!");
        }
        baseControlPresenter.requestData("/article/list/" + page + "/json",
                new TypeCallBack<HomeBean>() {},  null, true);
    }
}
