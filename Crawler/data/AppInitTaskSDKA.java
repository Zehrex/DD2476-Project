4
https://raw.githubusercontent.com/succlz123/StarDriver-APT/master/app/src/main/java/org/succlz123/stardriver/app/task/AppInitTaskSDKA.java
package org.succlz123.stardriver.app.task;

import android.content.Context;

import org.succlz123.stardriver.IStarDriver;
import org.succlz123.stardriver.StarDriverResult;

public class AppInitTaskSDKA extends IStarDriver {

    @Override
    public void initialize(Context context, StarDriverResult result) {
        try {
            Thread.sleep(166);
        } catch (InterruptedException e) {
            result.success = false;
            result.errorMessage = e.toString();
            return;
        }
        result.success = true;
    }
}
