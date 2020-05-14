12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/UrlOpenEvent.java
package ru.bgcrm.event.client;

public class UrlOpenEvent
	extends ClientEvent
{
	private final String url;
	
	public UrlOpenEvent( String url )
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}
}
