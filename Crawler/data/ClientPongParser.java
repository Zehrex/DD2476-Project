2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/parser/client/ClientPongParser.java
package com.nitro.application.communication.messages.parser.client;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class ClientPongParser implements IMessageParser {

    private String version;

    public boolean flush() {
        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        return true;
    }
}
