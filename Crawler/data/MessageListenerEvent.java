2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/events/messages/MessageListenerEvent.java
package com.nitro.core.communication.events.messages;

import com.nitro.core.communication.events.CommunicationEvent;
import com.nitro.core.communication.messages.IMessageListener;

public class MessageListenerEvent extends CommunicationEvent {

    private IMessageListener messageListener;

    public MessageListenerEvent(IMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public IMessageListener getMessageListener() {
        return this.messageListener;
    }
}
