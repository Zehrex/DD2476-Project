1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/IPManager.java
package com.agm.ipmanager;

import com.agm.ipmanager.API.APIConnector;
import com.agm.ipmanager.credentials.CredentialsManager;

import java.util.HashMap;

public class IPManager {
    private static IPManager ipManager;
    private String serverName = "";

    private HashMap<Service, Boolean> servicesStatus;

    private IPManager() {}

    public static IPManager getInstance() {
        if (ipManager == null) {
            ipManager = new IPManager();
        }

        return ipManager;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isOnline() {
        return CredentialsManager.getInstance().isOnline();
    }

    public HashMap<Service, Boolean> getServicesStatus() {
        return this.servicesStatus;
    }

    public void recalculateServicesStatus() {
        this.servicesStatus = APIConnector.getInstance().getServiceStatus();
    }
}
