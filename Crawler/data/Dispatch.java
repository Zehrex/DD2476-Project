12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/dispatch/model/Dispatch.java
package ru.bgcrm.plugin.dispatch.model;

import ru.bgcrm.model.IdTitleComment;

public class Dispatch
	extends IdTitleComment
{
	private int accountCount;

	public int getAccountCount()
	{
		return accountCount;
	}

	public void setAccountCount( int accountCount )
	{
		this.accountCount = accountCount;
	}
}
