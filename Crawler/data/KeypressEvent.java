12
https://raw.githubusercontent.com/Crystallinqq/Mercury-Client/master/src/main/java/fail/mercury/client/client/events/KeypressEvent.java
package fail.mercury.client.client.events;

import net.b0at.api.event.Event;

public class KeypressEvent extends Event {

    private final int key;

    public KeypressEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
