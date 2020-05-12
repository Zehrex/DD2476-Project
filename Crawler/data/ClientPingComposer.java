2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/outgoing/client/ClientPingComposer.java
package com.nitro.application.communication.messages.outgoing.client;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class ClientPingComposer implements IMessageComposer {

    private List<Object> data;

    public ClientPingComposer() {
        this.data = new ArrayList<>();
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
