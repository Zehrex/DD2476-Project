4
https://raw.githubusercontent.com/succlz123/StarDriver-APT/master/app/src/main/java/org/succlz123/stardriver/app/task/AppInitLogger.java
package org.succlz123.stardriver.app.task;

import android.content.Context;

import org.succlz123.stardriver.IStarDriver;
import org.succlz123.stardriver.StarDriverResult;

public class AppInitLogger extends IStarDriver {

    @Override
    public void initialize(Context context, StarDriverResult result) {
        try {
            // simulation initialization code
            Thread.sleep(23);
        } catch (InterruptedException e) {
            result.success = false;
            result.errorMessage = e.toString();
            return;
        }
        result.success = true;
    }
}
