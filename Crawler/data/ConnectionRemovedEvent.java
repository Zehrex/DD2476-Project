2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/connection/ConnectionRemovedEvent.java
package com.nitro.core.communication.events.connection;

import com.nitro.core.communication.connections.IConnection;

public class ConnectionRemovedEvent extends ConnectionEvent {

    public ConnectionRemovedEvent(IConnection connection) {
        super(connection);
    }
}
