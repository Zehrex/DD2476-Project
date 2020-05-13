2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/messages/MessageClassManager.java
package com.nitro.core.communication.messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageClassManager {

    private Map<String, Integer> messageIdByEvent;
    private Map<String, Integer> messageIdByComposer;
    private Map<Integer, List<IMessageEvent>> messageInstancesById;

    public MessageClassManager() {
        this.messageIdByEvent = new HashMap<>();
        this.messageIdByComposer = new HashMap<>();
        this.messageInstancesById = new HashMap<>();
    }

    public void dispose() {
        this.messageIdByEvent.clear();
        this.messageIdByComposer.clear();
        this.messageInstancesById.clear();
    }

    public void registerMessageConfiguration(IMessageConfiguration configuration) {
        Map<Integer, Class<? extends IMessageEvent>> events = configuration.getEvents();

        if((events != null) && (events.size() > 0)) {
            for(int header : events.keySet()) {
                this.registerMessageEventClass(header, events.get(header));
            }
        }

        Map<Integer, Class<? extends IMessageComposer>> composers = configuration.getComposers();

        if((composers != null) && (composers.size() > 0)) {
            for(int header : composers.keySet()) {
                this.registerMessageComposerClass(header, composers.get(header));
            }
        }
    }

    private void registerMessageEventClass(int header, Class<? extends IMessageEvent> messageEvent) {
        if(messageEvent == null) return;

        String name = messageEvent.getName();

        if(this.messageIdByEvent.containsKey(name)) {
            System.out.println(messageEvent.getSimpleName() + " is already registered. Setting to header id: " + header);
        }

        this.messageIdByEvent.put(name, header);
    }

    private void registerMessageComposerClass(int header, Class<? extends IMessageComposer> messageComposer) {
        if(messageComposer == null) return;

        String name = messageComposer.getName();

        if(this.messageIdByComposer.containsKey(name)) {
            System.out.println(messageComposer.getSimpleName() + " is already registered. Setting to header id: " + header);
        }

        this.messageIdByComposer.put(name, header);
    }

    public void registerMessageEvent(IMessageEvent messageEvent) {
        if(messageEvent == null) return;

        int header = this.getEventId(messageEvent);

        if(header == -1) return;

        List<IMessageEvent> existing = this.messageInstancesById.get(header);

        if((existing == null) || (existing.size() == 0)) {
            existing = new ArrayList<>();

            this.messageInstancesById.put(header, existing);

            try {
                messageEvent.setParser(messageEvent.getParserClass().getDeclaredConstructor().newInstance());
            } catch(Exception e) {
                System.out.println("Error registering message event");
            }
        } else {
            messageEvent.setParser(existing.get(0).getParser());
        }

        existing.add(messageEvent);
    }

    public void removeMessageEvent(IMessageEvent messageEvent) {
        if(messageEvent == null) return;

        int header = this.getEventId(messageEvent);

        if(header == -1) return;

        List<IMessageEvent> existing = this.messageInstancesById.get(header);

        if((existing != null) && (existing.size() > 0)) {
            for(IMessageEvent event : existing) {
                if(event != messageEvent) continue;

                existing.remove(event);

                if(existing.size() == 0) this.messageInstancesById.remove(header);

                event.dispose();

                return;
            }
        }
    }

    public int getEventId(IMessageEvent messageEvent) {
        if(messageEvent == null) return -1;

        String name = messageEvent.getClass().getName();

        Integer header = this.messageIdByEvent.get(name);

        if(header == null) return -1;

        return header;
    }

    public int getComposerId(IMessageComposer messageComposer) {
        if(messageComposer == null) return -1;

        String name = messageComposer.getClass().getName();

        Integer header = this.messageIdByComposer.get(name);

        if(header == null) return -1;

        return header;
    }

    public List<IMessageEvent> getEvents(int header) {
        return this.messageInstancesById.get(header);
    }
}
