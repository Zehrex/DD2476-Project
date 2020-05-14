14
https://raw.githubusercontent.com/FanChael/MVVM/master/modules/module_main/src/main/java/com/hl/modules_main/view/adapter/HomeAdatper.java
package com.hl.modules_main.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hl.base_module.adapter.BaseMulViewHolder;
import com.hl.base_module.adapter.BaseMutilayoutAdapter;
import com.hl.lib_refreshlayout.R;
import com.hl.lib_refreshlayout.example.RvBean;
import com.hl.modules_main.model.bean.HomeBean;

import java.util.List;

public class HomeAdatper extends BaseMutilayoutAdapter<HomeBean.DatasBean> {
    public HomeAdatper(Context context, List<HomeBean.DatasBean> datas) {
        super(context, datas);
    }

    @Override
    protected BaseMulViewHolder getHolder(Context context, ViewGroup parent, int viewType) throws Exception {
        return new BaseMulViewHolder<RvBean>(LayoutInflater.from(context).inflate(R.layout.rv_layout, parent, false));
    }

    @Override
    protected void handleHolder(Context context, BaseMulViewHolder baseHolder, int viewType) {

    }

    @Override
    protected void bindData(Context context, BaseMulViewHolder baseHolder, HomeBean.DatasBean datasBean, int postion, int itemViewType) {
        baseHolder.setText(R.id.rl_title, datasBean.getTitle());
        addOnItemClickListener(baseHolder.getItemView(), postion, datasBean, itemViewType, null);
    }
}
