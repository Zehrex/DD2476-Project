2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/incoming/security/SecurityMachineEvent.java
package com.nitro.application.communication.messages.incoming.security;

import com.nitro.application.communication.messages.parser.security.SecurityMachineParser;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class SecurityMachineEvent extends MessageEvent {

    public SecurityMachineEvent(Method messageCallback) {
        super(messageCallback, SecurityMachineParser.class);
    }

    public SecurityMachineParser getParser() {
        return (SecurityMachineParser) this.parser;
    }
}
