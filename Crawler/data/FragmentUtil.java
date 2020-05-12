4
https://raw.githubusercontent.com/abdalmoniem/Movie-App/master/base/src/main/java/butter/droid/base/utils/FragmentUtil.java
package butter.droid.base.utils;

import androidx.fragment.app.Fragment;

public class FragmentUtil {

    public static boolean isNotAdded(Fragment fragment) {
        return !fragment.isAdded() || fragment.isDetached() || null == fragment.getActivity() || fragment.getActivity().isFinishing();
    }
}
