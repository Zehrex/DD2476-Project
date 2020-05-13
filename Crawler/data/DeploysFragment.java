1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/deploys/DeploysFragment.java
package com.agm.ipmanager.deploys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agm.ipmanager.R;

public class DeploysFragment extends Fragment {
    public DeploysFragment() {
    }

    public static DeploysFragment newInstance() {
        DeploysFragment fragment = new DeploysFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deploys, container, false);
    }
}
