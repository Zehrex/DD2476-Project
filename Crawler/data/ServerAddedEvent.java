2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/server/ServerAddedEvent.java
package com.nitro.core.communication.events.server;

import com.nitro.core.communication.servers.IServer;

public class ServerAddedEvent extends ServerEvent {

    public ServerAddedEvent(IServer server) {
        super(server);
    }
}
