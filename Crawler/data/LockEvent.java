12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/LockEvent.java
package ru.bgcrm.event.client;

import ru.bgcrm.model.Lock;

public class LockEvent
	extends ClientEvent
{
	private final Lock lock;
	
	public LockEvent( Lock lock )
	{
		this.lock = lock;
	}

	public Lock getLock()
	{
		return lock;
	}	
}
