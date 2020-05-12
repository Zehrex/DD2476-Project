2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/events/NitroEvent.java
package com.nitro.core.events;

public class NitroEvent {

    private boolean cancelled;

    public NitroEvent() {
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }
}
