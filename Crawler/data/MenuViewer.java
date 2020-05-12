4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/bottomdialog/src/main/java/net/neonteam/appbottomdialog/widgets/MenuViewer.java
package net.neonteam.appbottomdialog.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.MenuRes;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.neonteam.appbottomdialog.adapters.MenuViewerAdapter;
import net.neonteam.appbottomdialog.builders.AppBottomDialog;
import net.neonteam.appbottomdialog.interfaces.OnSelectMenuItem;
import net.neonteam.appbottomdialog.values.Settings;
import net.neonteam.appbottomdialog.values.Theme;

public class MenuViewer {

    private Activity activity;

    private Menu menu;

    private RecyclerView recyclerView;

    private Theme theme;
    private Settings settings;

    private OnSelectMenuItem onSelectMenuItem;

    private AppBottomDialog.Builder appBottomDialog;

    @SuppressLint("RestrictedApi")
    public MenuViewer (Activity activity, Theme theme, Settings settings, AppBottomDialog.Builder bottomDialog, OnSelectMenuItem onSelectMenuItem) {
        this.activity = activity;
        this.theme = theme;
        this.settings = settings;
        this.onSelectMenuItem = onSelectMenuItem;
        this.appBottomDialog = bottomDialog;
        menu = new MenuBuilder(activity);
    }

    @SuppressLint("RestrictedApi")
    public MenuViewer (Activity activity, AppBottomDialog.Builder bottomDialog) {
        this.activity = activity;
        this.theme = new Theme();
        this.settings = new Settings();
        this.onSelectMenuItem = new OnSelectMenuItem() {
            @Override
            public void onSelected(MenuItem item) {

            }
        };
        this.appBottomDialog = bottomDialog;
        menu = new MenuBuilder(activity);
    }

    public MenuViewer setListener(OnSelectMenuItem onSelectMenuItem) {
        this.onSelectMenuItem = onSelectMenuItem;
        return this;
    }

    public MenuViewer setMenu (@MenuRes int res) {
        activity.getMenuInflater().inflate(res, this.menu);
        return this;
    }

    public AppBottomDialog.Builder build () {
        recyclerView = new RecyclerView(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new MenuViewerAdapter(activity.getApplicationContext(), menu, theme, settings, appBottomDialog,onSelectMenuItem));
        appBottomDialog.setContentView(recyclerView);
        return appBottomDialog;
    }

    public RecyclerView get () {
        return recyclerView;
    }

}
