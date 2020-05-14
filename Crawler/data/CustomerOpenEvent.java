12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/CustomerOpenEvent.java
package ru.bgcrm.event.client;

/**
 * Сообщение о необходимости открыть вкладку контрагента,
 * либо обновить, если она уже открыта.
 */
public class CustomerOpenEvent
	extends ClientEventWithId
{
	public CustomerOpenEvent( int id )
	{
		super( id );
	}
}
