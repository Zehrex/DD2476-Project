12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/listener/DynamicEventListener.java
package ru.bgcrm.event.listener;

import ru.bgcrm.event.Event;
import ru.bgcrm.util.sql.ConnectionSet;

public abstract class DynamicEventListener implements EventListener<Event> {
    public abstract void notify(Event e, ConnectionSet connectionSet) throws Exception;
}
