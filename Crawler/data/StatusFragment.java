1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/status/StatusFragment.java
package com.agm.ipmanager.status;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agm.ipmanager.events.EventManager;
import com.agm.ipmanager.IPManager;
import com.agm.ipmanager.R;
import com.agm.ipmanager.Service;

import java.util.HashMap;

public class StatusFragment extends Fragment {
    RecyclerView statusRecyclerView;
    TextView serverStatusText;
    ImageView mongoStatusImage;
    ImageView dockerStatusImage;

    public static StatusFragment newInstance() {
        StatusFragment fragment = new StatusFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);

        // Events recyclerView
        statusRecyclerView = root.findViewById(R.id.eventsRecyclerView);
        statusRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EventsAdapter eventsAdapter = new EventsAdapter(getContext(), EventManager.getInstance().getEvents());
        statusRecyclerView.setAdapter(eventsAdapter);

        // Set server status
        serverStatusText = root.findViewById(R.id.serverStatusText);
        String serverName = IPManager.getInstance().getServerName();
        serverStatusText.setText(serverName.isEmpty() ? "Server status" : serverName);

        // Server online?
        boolean isOnline = IPManager.getInstance().isOnline();
        HashMap<Service, Boolean> servicesStatus = IPManager.getInstance().getServicesStatus();
        isOnline = servicesStatus == null ? false : true;

        // Mongo service status icon
        mongoStatusImage = root.findViewById(R.id.mongoStatusImage);
        if (isOnline) {
            mongoStatusImage.setImageResource(servicesStatus.get(Service.MONGO) ? R.drawable.up : R.drawable.down);
        } else {
            mongoStatusImage.setImageResource(R.drawable.offline);
        }

        // Docker service status icon
        dockerStatusImage = root.findViewById(R.id.dockerStatusImage);
        if (isOnline) {
            dockerStatusImage.setImageResource(servicesStatus.get(Service.DOCKER) ? R.drawable.up : R.drawable.down);
        } else {
            dockerStatusImage.setImageResource(R.drawable.offline);
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        statusRecyclerView.setAdapter(new EventsAdapter(getContext(), EventManager.getInstance().getEvents()));
    }
}
