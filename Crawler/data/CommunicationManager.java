2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/CommunicationManager.java
package com.nitro.core.communication;

import com.nitro.core.INitroCore;
import com.nitro.core.communication.events.server.ServerAddedEvent;
import com.nitro.core.communication.events.server.ServerRemovedEvent;
import com.nitro.core.communication.servers.IServer;

import java.util.HashMap;
import java.util.Map;

public class CommunicationManager implements ICommunicationManager {

    private INitroCore nitroCore;
    private Map<Integer, IServer> servers;

    private boolean isDisposed;

    public CommunicationManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.servers = new HashMap<>();

        this.isDisposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.isDisposed = true;

        this.removeAllServers();
    }

    public IServer addServer(IServer server) {
        if(server == null) return null;

        if(this.servers.containsValue(server)) return server;

        server.setContainer(this);

        if(this.nitroCore != null) {
            server.setEventDispatcher(this.nitroCore.getPluginManager().getEventDispatcher());

            ServerAddedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ServerAddedEvent(server));

            if(event.isCancelled()) return null;
        }

        this.servers.put(server.getId(), server);

        return server;
    }

    public void removeServer(IServer server) {
        if(server == null) return;

        if(this.nitroCore != null) {
            ServerRemovedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ServerRemovedEvent(server));

            if(event.isCancelled()) return;
        }

        IServer existing = this.servers.remove(server.getId());

        if(existing != null) existing.dispose();
    }

    public void removeAllServers() {
        if((this.servers == null) || (this.servers.size() < 1)) return;

        for(IServer server : this.servers.values()) this.removeServer(server);
    }

    public boolean isDisposed() {
        return this.isDisposed;
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public Map<Integer, IServer> getServers() {
        return this.servers;
    }
}
