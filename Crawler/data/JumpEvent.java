12
https://raw.githubusercontent.com/Crystallinqq/Mercury-Client/master/src/main/java/fail/mercury/client/client/events/JumpEvent.java
package fail.mercury.client.client.events;

import fail.mercury.client.api.util.Location;
import net.b0at.api.event.Event;

public class JumpEvent extends Event {

    private Location location;

    public JumpEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
