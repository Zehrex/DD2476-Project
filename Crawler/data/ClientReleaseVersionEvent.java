2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/incoming/client/ClientReleaseVersionEvent.java
package com.nitro.application.communication.messages.incoming.client;

import com.nitro.application.communication.messages.parser.client.ClientReleaseVersionParser;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class ClientReleaseVersionEvent extends MessageEvent implements IMessageEvent {

    public ClientReleaseVersionEvent(Method messageCallback) {
        super(messageCallback, ClientReleaseVersionParser.class);
    }

    public ClientReleaseVersionParser getParser() {
        return (ClientReleaseVersionParser) this.parser;
    }
}
