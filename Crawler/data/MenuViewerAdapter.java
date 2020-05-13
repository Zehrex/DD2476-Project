4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/bottomdialog/src/main/java/net/neonteam/appbottomdialog/adapters/MenuViewerAdapter.java
package net.neonteam.appbottomdialog.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.neonteam.appbottomdialog.R;
import net.neonteam.appbottomdialog.builders.AppBottomDialog;
import net.neonteam.appbottomdialog.interfaces.OnSelectMenuItem;
import net.neonteam.appbottomdialog.values.Settings;
import net.neonteam.appbottomdialog.values.Theme;

import org.jetbrains.annotations.NotNull;

public class MenuViewerAdapter extends RecyclerView.Adapter<MenuViewerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Menu menu;

    private Theme theme;
    private Settings settings;

    private OnSelectMenuItem selectMenuItemInterface;

    private Context context;

    private AppBottomDialog.Builder dialog;

    public MenuViewerAdapter(Context context, Menu menu, Theme theme, Settings settings, AppBottomDialog.Builder builder, OnSelectMenuItem onSelectMenuItem) {
        this.menu = menu;
        this.inflater = LayoutInflater.from(context);
        this.theme = theme;
        this.selectMenuItemInterface = onSelectMenuItem;
        this.settings = settings;
        this.dialog = builder;
        this.context = context;
    }


    @NotNull
    @Override
    public MenuViewerAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_menuitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MenuViewerAdapter.ViewHolder holder, final int position) {
        if (menu.getItem(position).getIcon() != null)
         holder.icon.setImageDrawable(menu.getItem(position).getIcon());
        holder.title.setText(menu.getItem(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(position);
            }
        });
        // setup theme
        holder.title.setTextColor(Color.parseColor("#ffffff"));
        holder.icon.setImageTintList(ColorStateList.valueOf(theme.menuItemIconTintColor));
        // setup ripple
        RippleDrawable ripple = new RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ theme.rippleBackground}), context.getDrawable(android.R.color.transparent), null);
        holder.itemView.setBackground(ripple);
    }

    public void selectItem (int position) {
        if (settings.closeOnItemClick)
            dialog.dismiss();
        selectMenuItemInterface.onSelected(menu.getItem(position));
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
        }
    }
}
