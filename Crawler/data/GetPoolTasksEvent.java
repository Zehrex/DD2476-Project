12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/GetPoolTasksEvent.java
package ru.bgcrm.event;

import ru.bgcrm.struts.form.DynActionForm;

/**
 * Событие пуллинга, генерируется для каждого работающего пользователя,
 * очень часто. Недопустима сложная логика в обработчике.
 */
public class GetPoolTasksEvent
	extends UserEvent
{
	public GetPoolTasksEvent( DynActionForm form )
	{
		super( form );
	}
}
