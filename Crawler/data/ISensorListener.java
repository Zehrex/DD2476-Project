13
https://raw.githubusercontent.com/wix-incubator/rn-contact-tracing/master/lib/android/src/main/java/com/wix/specialble/listeners/ISensorListener.java
package com.wix.specialble.listeners;

import android.hardware.SensorEventListener;

public interface ISensorListener extends SensorEventListener {
    float[] getEvents();
    boolean isSensorAvailable();
}
