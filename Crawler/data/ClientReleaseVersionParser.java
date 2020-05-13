2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/parser/client/ClientReleaseVersionParser.java
package com.nitro.application.communication.messages.parser.client;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class ClientReleaseVersionParser implements IMessageParser {

    private String version;

    public boolean flush() {
        this.version = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.version = wrapper.readString();

        return true;
    }

    public String getVersion() {
        return this.version;
    }
}
