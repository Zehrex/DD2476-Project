2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/NitroMessages.java
package com.nitro.application.communication.messages;

import com.nitro.application.communication.messages.incoming.IncomingHeaders;
import com.nitro.application.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.application.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.application.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.application.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.application.communication.messages.outgoing.OutgoingHeaders;
import com.nitro.application.communication.messages.outgoing.client.ClientPingComposer;
import com.nitro.application.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.application.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.core.communication.messages.MessageConfiguration;

public class NitroMessages extends MessageConfiguration {

    protected void registerEvents() {
        this.events.put(IncomingHeaders.CLIENT_PONG, ClientPongEvent.class);
        this.events.put(IncomingHeaders.CLIENT_RELEASE_VERSION, ClientReleaseVersionEvent.class);

        this.events.put(IncomingHeaders.SECURITY_MACHINE, SecurityMachineEvent.class);
        this.events.put(IncomingHeaders.SECURITY_TICKET, SecurityTicketEvent.class);
    }

    protected void registerComposers() {
        this.composers.put(OutgoingHeaders.CLIENT_PING, ClientPingComposer.class);

        this.composers.put(OutgoingHeaders.AUTHENTICATED, SecurityAuthenticatedComposer.class);
        this.composers.put(OutgoingHeaders.SECURITY_MACHINE, SecurityMachineComposer.class);
    }
}
