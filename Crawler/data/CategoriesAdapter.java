4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/adapter/recyclerview/CategoriesAdapter.java
package com.sketch.code.two.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.api.item.Category;
import com.sketch.code.two.util.GlideUtil;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Category> data;

    private Context context;

    public CategoriesAdapter(ArrayList<Category> data) {
        this.data = data;
    }
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(data.get(position).getName());
        GlideUtil.set(data.get(position).getIconUrl(), holder.icon, context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;

        public TextView categoryName;

        ViewHolder(View view){
            super(view);
            icon = view.findViewById(R.id.itemAppCategory);
            categoryName = view.findViewById(R.id.itemCategoryName);
        }
    }
}
