12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/MessageOpenEvent.java
package ru.bgcrm.event.client;

/**
 * Сообщение о необходимости открыть сообщение для обработки. 
 */
public class MessageOpenEvent
	extends ClientEventWithId
{
	public MessageOpenEvent( int id )
	{
		super( id );
	}
}
