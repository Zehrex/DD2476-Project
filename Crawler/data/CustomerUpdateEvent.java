12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/CustomerUpdateEvent.java
package ru.bgcrm.event;

import ru.bgcrm.event.customer.CustomerRemovedEvent;
import ru.bgcrm.struts.form.DynActionForm;

@Deprecated
public class CustomerUpdateEvent extends CustomerRemovedEvent {
    public CustomerUpdateEvent(DynActionForm form, int customerId) {
        super(form, customerId);
    }
}
