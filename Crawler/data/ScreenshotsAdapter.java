4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/ScreenshotsAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.activity.PhotoViewActivity;
import com.sketch.code.two.util.GlideUtil;

import java.util.ArrayList;

public class ScreenshotsAdapter extends RecyclerView.Adapter<ScreenshotsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Integer> data;

    private Context context;

    public ScreenshotsAdapter(ArrayList<Integer> data) {
        this.data = data;
    }

    @Override
    public ScreenshotsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_screenshot, parent, false);
        return new ScreenshotsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenshotsAdapter.ViewHolder holder, int position) {
        GlideUtil.set(data.get(position), holder.itemImage, context);
        holder.itemImage.setOnClickListener(v -> {
            Intent i = new Intent(context, PhotoViewActivity.class);
            i.putExtra("page", position);
            i.putExtra("photos", data);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;

        ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.itemImage);
        }
    }
}