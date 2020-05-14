12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/ClientEventWithId.java
package ru.bgcrm.event.client;

public class ClientEventWithId
	extends ClientEvent
{
	private final int id;
	
	public ClientEventWithId( int id )
	{
		this.id = id;
	}

	public int getId()
    {
    	return id;
    }	
}
