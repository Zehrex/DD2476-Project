2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/listeners/SecurityMessagesListener.java
package com.nitro.application.communication.messages.listeners;

import com.nitro.application.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.application.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.application.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.application.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;

public class SecurityMessagesListener implements IMessageListener {

    @MessageHandler
    public void onSecurityMachineEvent(SecurityMachineEvent event) {
        event.getConnection().processComposer(new SecurityMachineComposer(event.getParser().getMachineId()));
    }

    @MessageHandler
    public void onSecurityTicketEvent(SecurityTicketEvent event) {
        event.getConnection().processComposer(new SecurityAuthenticatedComposer());
    }
}
