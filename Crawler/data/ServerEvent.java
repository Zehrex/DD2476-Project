2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/server/ServerEvent.java
package com.nitro.core.communication.events.server;

import com.nitro.core.communication.events.CommunicationEvent;
import com.nitro.core.communication.servers.IServer;

public class ServerEvent extends CommunicationEvent {

    private final IServer server;

    public ServerEvent(IServer server) {
        this.server = server;
    }

    public IServer getServer() {
        return this.server;
    }
}
