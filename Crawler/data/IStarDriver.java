4
https://raw.githubusercontent.com/succlz123/StarDriver-APT/master/stardriver-apt-lib/src/main/java/org/succlz123/stardriver/IStarDriver.java
package org.succlz123.stardriver;

import android.content.Context;

import androidx.annotation.Keep;

@Keep
public abstract class IStarDriver {

    public abstract void initialize(Context context, StarDriverResult result);
}
