4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/bottomdialog/src/main/java/net/neonteam/appbottomdialog/builders/AppBottomDialog.java
package net.neonteam.appbottomdialog.builders;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import net.neonteam.appbottomdialog.R;
import net.neonteam.appbottomdialog.values.Settings;
import net.neonteam.appbottomdialog.values.Theme;
import net.neonteam.appbottomdialog.widgets.MenuViewer;

import java.util.Objects;

public class AppBottomDialog {

    public static class Builder {
        private Activity activity;

        private BottomSheetDialog dialog;

        private View view;
        private LinearLayout root;

        private View contentView; // content in scrollview

        // dialog theme (very bad solution :D)
        private Theme theme;

        // Views in
        private TextView dialogTitle;
        private ScrollView scrollView;

        // dialog buttons
        private Button ok;
        private Button cancel;

        // dialog settings
        private Settings settings;

        public Builder(Activity activity) {
            this.activity = activity;
            init();
        }

        private void init() {
            // making dialog
            dialog = new BottomSheetDialog(activity, R.style.SheetDialog);
            // init view
            view = activity.getLayoutInflater().inflate(R.layout.dialog, null);
            // set view for dialog
            dialog.setContentView(view);
            // set dialog window background NULL
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(null);
            // inflating views
            root = view.findViewById(R.id.root);
            dialogTitle = view.findViewById(R.id.dialogTitle); // dialog title text view
            scrollView = view.findViewById(R.id.scrollview); // scrollview where contentView
            ok = view.findViewById(R.id.ok_button);
            cancel = view.findViewById(R.id.cancel_button);
            // setup default settings for dialog
            setSettings(new Settings());
            // set default theme
            setTheme(new Theme());
        }

        public Builder setTitle (@StringRes int res) {
            dialogTitle.setText(res);
            dialogTitle.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setTitle (String title) {
            dialogTitle.setText(title);
            return this;
        }

        public Builder setContentView (View contentView) {
            this.contentView = contentView;
            updateView();
            return this;
        }

        private void updateView () {
            scrollView.removeAllViews();
            scrollView.addView(contentView);
        }

        public Builder setSettings (Settings settings) {
            this.settings = settings;
            return this;
        }

        public Settings getSettings () {
            return settings;
        }

        public Builder show() {
            dialog.show();
            return this;
        }

        public Builder dismiss () {
            dialog.dismiss();
            return this;
        }

        // so fucking bad
        private void updateViewsInfo () {
            dialogTitle.setTextColor(theme.titleTextColor);
            ok.setTextColor(theme.buttonOkTextColor);
            cancel.setTextColor(theme.defaultButtonTextColor);
            root.setBackgroundTintList(ColorStateList.valueOf(theme.dialogBackground));
        }

        public Builder setTheme(Theme theme) {
            this.theme = theme;
            updateViewsInfo();
            return this;
        }

        public MenuViewer setupMenu (@MenuRes int res) {
            return new MenuViewer(activity, this)
                    .setMenu(res);
        }
    }

}
