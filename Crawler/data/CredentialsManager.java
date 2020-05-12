1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/credentials/CredentialsManager.java
package com.agm.ipmanager.credentials;

import android.view.View;

import com.agm.ipmanager.API.APIConnector;
import com.agm.ipmanager.IPManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CredentialsManager {
    private static CredentialsManager fileManager;
    private static final String CREDENTIALS_FILE_NAME = "credentials.txt";
    private static final String SERVER_INFO_FILE_NAME = "serverinfo.txt";
    private View view;
    private Credentials credentials;
    private String serverName;

    private CredentialsManager() {}

    public static CredentialsManager getInstance() {
        if (fileManager == null) {
            fileManager = new CredentialsManager();
        }

        return fileManager;
    }

    public void setView(View view) {
        this.view = view;
        try {
            this.restoreCredentials();
            this.restoreServerName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }
    public void setToken(String token) { this.credentials.setToken(token);}

    public void saveCredentials(Credentials credentials) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = view.getContext().openFileOutput(CREDENTIALS_FILE_NAME, view.getContext().MODE_PRIVATE);
            String toWrite = credentials.hostname + "#" + credentials.username + "#" + credentials.password;
            fos.write(toWrite.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        this.credentials = credentials;
    }

    public void restoreCredentials() throws IOException {
        if (credentials == null) {
            FileInputStream fis = null;
            String text = "";

            try {
                fis = view.getContext().openFileInput(CREDENTIALS_FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                while ((text = br.readLine()) != null) {
                    sb.append(text);
                }

                text = sb.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }

            String[] c = text.split("#");

            if (c.length == 0) {
                credentials = null;
            }

            this.credentials = new Credentials(c[0], c[1], c[2]);

            APIConnector.getInstance().login();
        }
    }

    public boolean isOnline() {
        return this.credentials != null && !this.credentials.token.equals("");
    }

    public boolean hasCredentials() {
        return this.credentials != null;
    }

    public boolean hasServerName() {
        return this.serverName != null;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void saveServerName(String serverName) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = view.getContext().openFileOutput(SERVER_INFO_FILE_NAME, view.getContext().MODE_PRIVATE);
            String toWrite = serverName + "#";
            fos.write(toWrite.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        this.serverName = serverName;
    }

    public void restoreServerName() throws IOException {
        if (credentials == null) {
            FileInputStream fis = null;
            String text = "";

            try {
                fis = view.getContext().openFileInput(SERVER_INFO_FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                while ((text = br.readLine()) != null) {
                    sb.append(text);
                }

                text = sb.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }

            String[] c = text.split("#");

            if (c.length == 0) {
                serverName = null;
            }

            serverName = c[0];

            IPManager.getInstance().setServerName(serverName);
        }
    }
}
