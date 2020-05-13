1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/settings/SettingsFragment.java
package com.agm.ipmanager.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.agm.ipmanager.IPManager;
import com.agm.ipmanager.R;
import com.agm.ipmanager.credentials.Credentials;
import com.agm.ipmanager.credentials.CredentialsManager;

import java.io.IOException;

public class SettingsFragment extends Fragment {
    EditText serverNameInput;
    Button setServerNameButton;
    Button setServerCredentialsButton;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Server name
        serverNameInput = root.findViewById(R.id.serverNameInput);

        if (CredentialsManager.getInstance().hasServerName()) {
            serverNameInput.setText(CredentialsManager.getInstance().getServerName());
        }

        setServerNameButton = root.findViewById(R.id.setServerNameButton);
        setServerNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CredentialsManager.getInstance().saveServerName(serverNameInput.getText().toString());
                    IPManager.getInstance().setServerName(CredentialsManager.getInstance().getServerName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Credentials dialog
        setServerCredentialsButton = root.findViewById(R.id.setServerCredentialsButton);
        setServerCredentialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CredentialsDialog credentialsDialog = new CredentialsDialog();
                credentialsDialog.show(getActivity().getSupportFragmentManager(), "Credentials");
            }
        });

        return root;
    }
}
