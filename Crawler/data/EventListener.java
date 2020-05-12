2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/EventListener.java
package com.nitro.application;

import com.nitro.core.communication.events.connection.ConnectionAddedEvent;
import com.nitro.core.communication.events.connection.ConnectionRemovedEvent;
import com.nitro.core.communication.events.messages.MessageConfigurationEvent;
import com.nitro.core.communication.events.messages.MessageListenerEvent;
import com.nitro.core.communication.events.server.ServerAddedEvent;
import com.nitro.core.communication.events.server.ServerRemovedEvent;
import com.nitro.core.events.EventHandler;
import com.nitro.core.events.IEventListener;

public class EventListener implements IEventListener {

    @EventHandler
    public void onServerAddedEvent(ServerAddedEvent event) {
        System.out.println("The server will be added");
    }

    @EventHandler
    public void onServerRemovedEvent(ServerRemovedEvent event) {
        System.out.println("The server will be removed");
    }

    @EventHandler
    public void onConnectionAddedEvent(ConnectionAddedEvent event) {
        System.out.println("The connection will be added");
    }

    @EventHandler
    public void onConnectionRemovedEvent(ConnectionRemovedEvent event) {
        System.out.println("The connection will be removed");
    }

    @EventHandler
    public void onMessageConfigurationEvent(MessageConfigurationEvent event) {
        System.out.println("A configuration was registered");
    }

    @EventHandler
    public void onMessageListenerEvent(MessageListenerEvent event) {
        System.out.println("A listener was registered");
    }
}
