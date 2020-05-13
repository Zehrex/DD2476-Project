4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/viewpager/PhotoViewAdapter.java
package com.sketch.code.two.adapter.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.util.GlideUtil;

import java.util.ArrayList;

public class PhotoViewAdapter extends RecyclerView.Adapter<PhotoViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Integer> data;

    private Context context;

    public PhotoViewAdapter(ArrayList<Integer> data,Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public PhotoViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_photo_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewAdapter.ViewHolder holder, int position) {
        GlideUtil.set(data.get(position), holder.photo, context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photo;

        ViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo);
        }
    }
}
