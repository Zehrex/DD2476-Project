2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/parser/security/SecurityTicketParser.java
package com.nitro.application.communication.messages.parser.security;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class SecurityTicketParser implements IMessageParser {

    private String ticket;

    public boolean flush() {
        this.ticket = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.ticket = wrapper.readString();

        return true;
    }

    public String getTicket() {
        return this.ticket;
    }
}
