74
https://raw.githubusercontent.com/harshalbenake/hbworkspace1-100/master/ViewPagerDemo/src/pl/looksok/viewpagerdemo/FragmentPink.java
package pl.looksok.viewpagerdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

public class FragmentPink extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_pink, container, false);
		return view;
	}
}
