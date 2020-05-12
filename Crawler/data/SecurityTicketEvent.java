2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/incoming/security/SecurityTicketEvent.java
package com.nitro.application.communication.messages.incoming.security;

import com.nitro.application.communication.messages.parser.security.SecurityTicketParser;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class SecurityTicketEvent extends MessageEvent {

    public SecurityTicketEvent(Method messageCallback) {
        super(messageCallback, SecurityTicketParser.class);
    }

    public SecurityTicketParser getParser() {
        return (SecurityTicketParser) this.parser;
    }
}
