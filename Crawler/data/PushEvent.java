12
https://raw.githubusercontent.com/Crystallinqq/Mercury-Client/master/src/main/java/fail/mercury/client/client/events/PushEvent.java
package fail.mercury.client.client.events;

import net.b0at.api.event.Event;

public class PushEvent extends Event {

    public Type type;

    public PushEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {
        BLOCK,
        LIQUID
    }

}
