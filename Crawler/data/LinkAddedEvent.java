12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/link/LinkAddedEvent.java
package ru.bgcrm.event.link;

import ru.bgcrm.model.CommonObjectLink;
import ru.bgcrm.struts.form.DynActionForm;

public class LinkAddedEvent extends LinkAddingEvent {
    public LinkAddedEvent(DynActionForm form, CommonObjectLink link) {
        super(form, link);
    }
}
