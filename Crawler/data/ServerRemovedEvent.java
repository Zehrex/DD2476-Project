2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/server/ServerRemovedEvent.java
package com.nitro.core.communication.events.server;

import com.nitro.core.communication.servers.IServer;

public class ServerRemovedEvent extends ServerEvent {

    public ServerRemovedEvent(IServer server) {
        super(server);
    }
}
