12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/ClientEventWithParamMap.java
package ru.bgcrm.event.client;

import java.util.Map;

public class ClientEventWithParamMap
	extends ClientEvent
{
	private final Map<String, String> params;

	public ClientEventWithParamMap( Map<String, String> params )
	{
		this.params = params;
	}

	public Map<String, String> getParams()
	{
		return params;
	}
}
