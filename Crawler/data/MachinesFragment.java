1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/machines/MachinesFragment.java
package com.agm.ipmanager.machines;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agm.ipmanager.R;

public class MachinesFragment extends Fragment {
    public MachinesFragment() {
    }

    public static MachinesFragment newInstance() {
        MachinesFragment fragment = new MachinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_machines, container, false);
    }
}
