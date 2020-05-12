2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/messages/MessageConfiguration.java
package com.nitro.core.communication.messages;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageConfiguration implements IMessageConfiguration {

    protected Map<Integer, Class<? extends IMessageEvent>> events;
    protected Map<Integer, Class<? extends IMessageComposer>> composers;

    public MessageConfiguration() {
        this.events = new HashMap<>();
        this.composers = new HashMap<>();

        this.registerEvents();
        this.registerComposers();
    }

    protected abstract void registerEvents();

    protected abstract void registerComposers();

    public Map<Integer, Class<? extends IMessageEvent>> getEvents() {
        return this.events;
    }

    public Map<Integer, Class<? extends IMessageComposer>> getComposers() {
        return this.composers;
    }
}
