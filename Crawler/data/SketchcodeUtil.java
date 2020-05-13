4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/SketchcodeUtil.java
package com.sketch.code.two.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SketchcodeUtil {
    public static class User {
        private SharedPreferences preferences;

        public User(Context context) {
            preferences = context.getSharedPreferences("user", Activity.MODE_PRIVATE);
        }

        public String getToken () {
            return preferences.getString("token", null);
        }

        public boolean isAuthorized () {
            return preferences.getString("token", null) != null;
        }

        public void setToken (String token) {
            preferences.edit().putString("token", token).apply();
        }

        public void deleteData() {
            preferences.edit().clear().apply();
        }

    }
}
