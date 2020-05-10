74
https://raw.githubusercontent.com/harshalbenake/hbworkspace1-100/master/pulltorefresh%20and%20dragndrop%20to%20gridview/library/src/com/handmark/pulltorefresh/library/internal/Utils.java
package com.handmark.pulltorefresh.library.internal;

import android.util.Log;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";

	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

}
