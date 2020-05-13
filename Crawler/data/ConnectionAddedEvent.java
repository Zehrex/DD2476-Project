2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/connection/ConnectionAddedEvent.java
package com.nitro.core.communication.events.connection;

import com.nitro.core.communication.connections.IConnection;

public class ConnectionAddedEvent extends ConnectionEvent {

    public ConnectionAddedEvent(IConnection connection) {
        super(connection);
    }
}
