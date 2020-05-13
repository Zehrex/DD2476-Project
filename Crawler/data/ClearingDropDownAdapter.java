1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/adapter/contract/ClearingDropDownAdapter.java
package com.hjq.demo.ui.adapter.contract;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.bean.contract.ClearingBean;

import java.util.List;

/**
 * 结算适配器
 */
public class ClearingDropDownAdapter extends BaseAdapter {
    private Context context;
    private List<ClearingBean> mDatas;

    public ClearingDropDownAdapter(Context context, List<ClearingBean> datas) {
        this.context = context;
        this.mDatas = datas;
    }

    public void setData(List<ClearingBean> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public ClearingBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_clearing_drop_down_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_remark = convertView.findViewById(R.id.tv_remark);
            viewHolder.tv_value = convertView.findViewById(R.id.tv_value);
            viewHolder.tv_change = convertView.findViewById(R.id.tv_change);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);

        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        ClearingBean item = getItem(position);
        viewHolder.tv_num.setText(String.valueOf(item.getNum()));
        viewHolder.tv_title.setText(item.getTitle());
        viewHolder.tv_remark.setText(item.getRemark());
        viewHolder.tv_value.setText(item.getValue());
        viewHolder.tv_change.setText(item.getChange());
    }

    static class ViewHolder {
        TextView tv_num,tv_title,tv_remark,tv_value,tv_change;
    }

}
