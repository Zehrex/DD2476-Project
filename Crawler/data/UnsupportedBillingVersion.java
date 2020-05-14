12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/bgbilling/proto/model/UnsupportedBillingVersion.java
package ru.bgcrm.plugin.bgbilling.proto.model;

import ru.bgcrm.model.BGException;

public class UnsupportedBillingVersion
	extends BGException
{
	public UnsupportedBillingVersion( String versions )
	{
		super( "Данный функционал поддержан для версий BGBilling: " + versions );
	}
}
