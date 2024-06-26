12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/mobile/struts/action/open/AuthAction.java
package ru.bgcrm.plugin.mobile.struts.action.open;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ru.bgcrm.cache.UserCache;
import ru.bgcrm.model.BGException;
import ru.bgcrm.model.BGMessageException;
import ru.bgcrm.model.Customer;
import ru.bgcrm.model.user.User;
import ru.bgcrm.plugin.mobile.Plugin;
import ru.bgcrm.plugin.mobile.dao.MobileDAO;
import ru.bgcrm.plugin.mobile.model.Account;
import ru.bgcrm.struts.form.DynActionForm;
import ru.bgcrm.util.sql.ConnectionSet;

public class AuthAction
	extends BaseAction
{
	@Override
	public ActionForward unspecified( ActionMapping mapping, DynActionForm form, ConnectionSet conSet ) throws BGException {
		form.setResponseType( DynActionForm.RESPONSE_TYPE_JSON );
		
		String login = form.getParam( "login", "" );
		String pswd = form.getParam( "pswd", "" );
		String role = form.getParam( "role", "" );
		String key = form.getParam( "key", "" );
		String serverId = form.getParam( "serverId", "" );
		
		if (!Plugin.getServerId().equals( serverId ))
			throw new BGException( "Wrong serverId." );
		
		Account account = new Account();
		account.setObjectType( role );
		account.setKey( key );
		
		switch( role ) {
			case User.OBJECT_TYPE:
				User user = UserCache.getUser( login );
				if( user == null ) throw new BGMessageException( "Incorrect login" );
				if( !user.getPassword().equals( pswd ) ) throw new BGMessageException( "Incorrect password" );
				account.setObjectId( user.getId() );
				break;
			case Customer.OBJECT_TYPE:
				break;
			//TODO: Авторизация через договоры биллинга.
		}
		
		new MobileDAO( conSet.getConnection() ).registerAccount( account );
		
		return status( conSet, form );
	}
	
}
