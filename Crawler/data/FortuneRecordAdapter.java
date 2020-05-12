1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/adapter/FortuneRecordAdapter.java
package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.ui.activity.RecordInfoActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2020/5/5.
 */
public class FortuneRecordAdapter extends RecyclerView.Adapter<FortuneRecordAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvType;
        RelativeLayout mRLayoutRoot;

        public ViewHolder(View view) {
            super(view);
            mTvType = view.findViewById(R.id.id_tv_type);
            mRLayoutRoot = view.findViewById(R.id.id_rllayout_root);
        }

    }

    public FortuneRecordAdapter(Context context, List<String> dataList) {
        mContext = context;
        mList = dataList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fortune_record, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mList.get(position);
        holder.mTvType.setText(data);
        holder.mRLayoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecordInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
