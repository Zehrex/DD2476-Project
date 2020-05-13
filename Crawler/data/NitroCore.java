2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/NitroCore.java
package com.nitro.core;

import com.nitro.core.communication.CommunicationManager;
import com.nitro.core.communication.ICommunicationManager;
import com.nitro.core.plugin.IPluginManager;
import com.nitro.core.plugin.PluginManager;

public class NitroCore implements INitroCore {

    private IPluginManager pluginManager;
    private ICommunicationManager communicationManager;

    private boolean isDisposed;

    public NitroCore() {
        this.pluginManager = new PluginManager(this);
        this.communicationManager = new CommunicationManager(this);

        this.isDisposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.isDisposed = true;

        if(this.communicationManager != null) {
            this.communicationManager.dispose();

            this.communicationManager = null;
        }

        if(this.pluginManager != null) {
            this.pluginManager.dispose();

            this.pluginManager = null;
        }
    }

    public boolean isDisposed() {
        return this.isDisposed;
    }

    public IPluginManager getPluginManager() {
        return this.pluginManager;
    }

    public ICommunicationManager getCommunicationManager() {
        return this.communicationManager;
    }
}
