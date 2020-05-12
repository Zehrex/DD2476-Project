2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/incoming/client/ClientPongEvent.java
package com.nitro.application.communication.messages.incoming.client;

import com.nitro.application.communication.messages.parser.client.ClientPongParser;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class ClientPongEvent extends MessageEvent {

    public ClientPongEvent(Method messageCallback) {
        super(messageCallback, ClientPongParser.class);
    }

    public ClientPongParser getParser() {
        return (ClientPongParser) this.parser;
    }
}
