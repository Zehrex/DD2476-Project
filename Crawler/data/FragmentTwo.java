74
https://raw.githubusercontent.com/harshalbenake/hbworkspace1-100/master/FragmentsTest/src/com/javacodegeeks/android/fragmentstest/FragmentTwo.java
package com.javacodegeeks.android.fragmentstest;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentTwo extends Fragment{
   @Override
   public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
      /**
       * Inflate the layout for this fragment
       */
      return inflater.inflate(
      R.layout.fragment_two, container, false);
   }
}
