14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_main/src/main/java/com/hl/modules_main/viewmodel/HomeViewModel.java
package com.hl.modules_main.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hl.anotation.NotProguard;
import com.hl.lib_network.controller.presenter.BaseControlPresenter;
import com.hl.modules_main.model.bean.HomeBean;
import com.hl.modules_main.model.bean.IHomeModel;
import com.hl.modules_main.model.respository.HomeModelRespository;

import java.util.List;

@NotProguard
public class HomeViewModel extends ViewModel implements IHomeModel {
    private MutableLiveData<List<HomeBean.DatasBean>> homeLd;
    private HomeModelRespository homeModelRespository;

    public HomeViewModel(BaseControlPresenter baseControlPresenter) {
        homeModelRespository = new HomeModelRespository(baseControlPresenter);
    }

    public MutableLiveData<List<HomeBean.DatasBean>> getHomeLd() {
        if (null == homeLd) {
            homeLd = new MutableLiveData<>();
        }
        return homeLd;
    }

    @Override
    public void getHomeList(int page) {
        homeModelRespository.getHomeList(page);
    }
}
