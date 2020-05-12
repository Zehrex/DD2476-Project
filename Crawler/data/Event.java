1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/events/Event.java
package com.agm.ipmanager.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Event {
    public EventType type;
    public String message;
    public String timestamp;

    public Event(EventType type, String message) {
        this.type = type;
        this.message = message;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.FRENCH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        this.timestamp = sdf.format(new Date());
    }
}
