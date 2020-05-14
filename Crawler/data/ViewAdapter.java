15
https://raw.githubusercontent.com/Florizt/RxMVVM/master/rxmvvmlib/src/main/java/com/rx/rxmvvmlib/binding/viewadapter/webview/ViewAdapter.java
package com.rx.rxmvvmlib.binding.viewadapter.webview;

import android.text.TextUtils;
import android.webkit.WebView;

import androidx.databinding.BindingAdapter;

/**
 * Created by wuwei
 * 2019/12/6
 * 佛祖保佑       永无BUG
 */
public class ViewAdapter {
    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
}
