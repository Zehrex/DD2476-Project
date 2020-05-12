2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/listeners/ClientMessagesListener.java
package com.nitro.application.communication.messages.listeners;

import com.nitro.application.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.application.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;

public class ClientMessagesListener implements IMessageListener {

    @MessageHandler
    public void onClientPongEvent(ClientPongEvent event) {
    }

    @MessageHandler
    public void onClientReleaseVersionEvent(ClientReleaseVersionEvent event) {
    }
}
