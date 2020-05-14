12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/GetPluginEntitiesEvent.java
package ru.bgcrm.event;

import ru.bgcrm.struts.form.DynActionForm;

public class GetPluginEntitiesEvent
    extends UserEvent
{
	public GetPluginEntitiesEvent( DynActionForm user )
    {
	    super( user );
    }
}
