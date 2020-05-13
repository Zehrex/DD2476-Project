2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/connection/ConnectionEvent.java
package com.nitro.core.communication.events.connection;

import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.events.CommunicationEvent;

public class ConnectionEvent extends CommunicationEvent {

    private IConnection connnection;

    public ConnectionEvent(IConnection connection) {
        this.connnection = connection;
    }

    public IConnection getConnnection() {
        return this.connnection;
    }
}
