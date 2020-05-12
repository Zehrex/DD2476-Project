2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/messages/MessageConfigurationEvent.java
package com.nitro.core.communication.events.messages;

import com.nitro.core.communication.events.CommunicationEvent;
import com.nitro.core.communication.messages.IMessageConfiguration;

public class MessageConfigurationEvent extends CommunicationEvent {

    private IMessageConfiguration messageConfiguration;

    public MessageConfigurationEvent(IMessageConfiguration messageConfiguration) {
        this.messageConfiguration = messageConfiguration;
    }

    public IMessageConfiguration getMessageConfiguration() {
        return this.messageConfiguration;
    }
}
