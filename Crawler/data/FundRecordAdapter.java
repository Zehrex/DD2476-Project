1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/ui/adapter/otc/FundRecordAdapter.java
package com.hjq.demo.ui.adapter.otc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.hjq.demo.R;
import com.hjq.demo.bean.FundRecordBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * project name RecyclerText
 * package name com.text.recyclertext
 * 相册
 */
public class FundRecordAdapter extends RecyclerView.Adapter<FundRecordAdapter.InfoViewHolder> {

    private List<FundRecordBean> mCommentList;
    private Context mContext;

    private AdapterClickListener onClickListener;


    public FundRecordAdapter(Context context, List<FundRecordBean> list) {
        mContext = context;
        mCommentList = list;
    }

    public void setUpdate(List<FundRecordBean> list) {
        mCommentList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_record_item, parent, false));

    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {

        if (mCommentList.get(position).getStatus() == 0) {
            holder.status.setText("完成");
            holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.color_000000));
        } else if (mCommentList.get(position).getStatus() == 1) {
            holder.status.setText("进行中");
            holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.color_00C185));
        } else {
            holder.status.setText("失败");
            holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.color_FF4742));
        }


        Date currentTime = new Date(mCommentList.get(position).getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        holder.timeTv.setText(dateString);
        holder.numTv.setText(mCommentList.get(position).getNum()+"");
        holder.message.setText(mCommentList.get(position).getMessage());
        holder.titleTv.setText(mCommentList.get(position).getTitle());
        if (position > 0 && mCommentList.get(position).getTitle().equals(mCommentList.get(position-1).getTitle())) {
            holder.titleTv.setVisibility(View.GONE);
        } else {
            holder.titleTv.setVisibility(View.VISIBLE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ProductInfoActivity.class);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public void setOnClickListener(AdapterClickListener adapterClickListener) {
        onClickListener = adapterClickListener;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;
        public TextView message;
        public TextView numTv;
        public TextView timeTv;
        public TextView status;
        public View view;

        public InfoViewHolder(View itemView) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            message = (TextView) itemView.findViewById(R.id.message);
            numTv = (TextView) itemView.findViewById(R.id.num_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            status = (TextView) itemView.findViewById(R.id.status);
            view = (View) itemView.findViewById(R.id.item_listen);
        }
    }

    public interface AdapterClickListener{
        void onClickListenerSelected(int position);
    }
}