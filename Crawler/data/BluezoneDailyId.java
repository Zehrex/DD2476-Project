14
https://raw.githubusercontent.com/BluezoneGlobal/react-native-bluetooth-scan/master/lib/android/src/main/java/com/scan/bluezoneid/BluezoneDailyId.java
package com.scan.bluezoneid;

import android.util.Pair;

import java.util.ArrayList;

/**
 * @author khanhxu
 */
class BluezoneDailyId extends Pair<ArrayList<BluezoneId>, BluezoneDate> {

    /**
     * Constructor for a Pair.
     *
     * @param first  the first object in the Pair
     * @param second the second object in the pair
     */
    BluezoneDailyId(ArrayList<BluezoneId> first, BluezoneDate second) {
        super(first, second);
    }
}
