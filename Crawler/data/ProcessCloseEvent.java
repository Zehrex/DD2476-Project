12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/ProcessCloseEvent.java
package ru.bgcrm.event.client;

/**
 * Сообщение о необходимости закрыть вкладку процесса.
 */
public class ProcessCloseEvent
	extends ClientEventWithId
{
	public ProcessCloseEvent( int id )
	{
		super( id );
	}
}
