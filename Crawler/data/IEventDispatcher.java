2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/events/IEventDispatcher.java
package com.nitro.core.events;

public interface IEventDispatcher {

    void dispose();
    void registerEventListener(IEventListener listener);
    <T extends NitroEvent> T dispatchEvent(T event);
}
