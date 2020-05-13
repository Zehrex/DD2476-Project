12
https://raw.githubusercontent.com/Crystallinqq/Mercury-Client/master/src/main/java/fail/mercury/client/client/events/Render2DEvent.java
package fail.mercury.client.client.events;

import net.b0at.api.event.Event;

public class Render2DEvent extends Event {
    private final float partialTicks;

    public Render2DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}

