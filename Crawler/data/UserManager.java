14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_common/src/main/java/com/hl/base_module/appcomponent/UserManager.java
package com.hl.base_module.appcomponent;

import android.text.TextUtils;

import com.hl.base_module.CommonApi;
import com.hl.base_module.util.storage.SharedPreferencesUtil;

/**
 * 用户本地信息管理
 */
public class UserManager {
    /**
     * 是否登录了
     *
     * @return
     */
    public static boolean bIsLogin() {
        if (TextUtils.isEmpty(getName())) {
            return false;
        }
        return true;
    }

    public static String getName() {
        return SharedPreferencesUtil.getInstance(CommonApi.getApplication()).getSP(userKey());
    }

    public static void saveUser(String name) {
        SharedPreferencesUtil.getInstance(CommonApi.getApplication()).putSP(userKey(), name);
    }

    public static void clearCount() {
        SharedPreferencesUtil.getInstance(CommonApi.getApplication()).putSP(userKey(), "");
    }

    private static String userKey() {
        return "user_key";
    }
}
