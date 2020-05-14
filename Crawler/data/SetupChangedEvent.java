12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/SetupChangedEvent.java
package ru.bgcrm.event;

import ru.bgcrm.struts.form.DynActionForm;

public class SetupChangedEvent
	extends UserEvent
{
	public SetupChangedEvent( DynActionForm form )
    {
	    super( form );
    }
}
