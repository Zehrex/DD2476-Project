12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/bgbilling/proto/struts/action/ContractBalanceAction.java
package ru.bgcrm.plugin.bgbilling.proto.struts.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ru.bgcrm.model.BGException;
import ru.bgcrm.plugin.bgbilling.DBInfo;
import ru.bgcrm.plugin.bgbilling.DBInfoManager;
import ru.bgcrm.plugin.bgbilling.proto.dao.BalanceDAO;
import ru.bgcrm.plugin.bgbilling.proto.dao.CashCheckDAO;
import ru.bgcrm.plugin.bgbilling.proto.dao.ContractDAO;
import ru.bgcrm.plugin.bgbilling.proto.dao.DirectoryDAO;
import ru.bgcrm.plugin.bgbilling.proto.model.balance.ContractAccount;
import ru.bgcrm.plugin.bgbilling.proto.model.balance.ContractBalanceDetail;
import ru.bgcrm.plugin.bgbilling.proto.model.balance.ContractBalanceGeneral;
import ru.bgcrm.plugin.bgbilling.proto.model.balance.ContractCharge;
import ru.bgcrm.plugin.bgbilling.proto.model.balance.ContractPayment;
import ru.bgcrm.plugin.bgbilling.struts.action.BaseAction;
import ru.bgcrm.struts.form.DynActionForm;
import ru.bgcrm.util.ParameterMap;
import ru.bgcrm.util.TimeUtils;
import ru.bgcrm.util.Utils;
import ru.bgcrm.util.sql.ConnectionSet;

public class ContractBalanceAction
	extends BaseAction
{
	public ActionForward balanceEditor( ActionMapping mapping,
										DynActionForm form,
										HttpServletRequest request,
										HttpServletResponse response,
										ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		String item = form.getParam( "item" );

		DirectoryDAO directoryDAO = new DirectoryDAO( form.getUser(), billingId );
		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );
		if( "contractCharge".equals( item ) )
		{
			Set<Integer> allowedTypeIds = getTypePermission( form, billingId, "chargeTypeIds" );

			form.getResponse().setData( "itemTypes", directoryDAO.getContractChargeTypes( allowedTypeIds ) );
			if( form.getId() > 0 )
			{
				form.getResponse().setData( "balanceItem", balanceDAO.getContractCharge( form.getId() ) );
			}
		}
		else if( "contractPayment".equals( item ) )
		{
			DBInfo dbInfo = DBInfoManager.getDbInfo( billingId );
			if( dbInfo.getPluginSet().contains( CashCheckDAO.CASHCHECK_MODULE_ID ) )
			{
				form.getResponse().setData( "currentPrinter", new CashCheckDAO( form.getUser(), billingId ).getCurrentPrinter() );
			}
			
			Set<Integer> allowedTypeIds = getTypePermission( form, billingId, "paymentTypeIds" );

			form.getResponse().setData( "itemTypes", directoryDAO.getContractPaymentTypes( allowedTypeIds ) );
			if( form.getId() > 0 )
			{
				form.getResponse().setData( "balanceItem", balanceDAO.getContractPayment( form.getId() ) );
			}
		}
		form.getResponse().setData( "date", new SimpleDateFormat( "dd.MM.yyyy" ).format( Calendar.getInstance().getTime() ) );
		return data( conSet, mapping, form, "balanceEditor" );
	}

	private Set<Integer> getTypePermission( DynActionForm form, String billingId, String key )
	{
		Set<Integer> allowedTypeIds = new HashSet<Integer>();
		ParameterMap permission = form.getPermission();
		if( permission != null )
		{
			String paymentTypeConfig = permission.get( key );

			if( Utils.notBlankString( paymentTypeConfig ) )
			{
				for( String str : paymentTypeConfig.split( ";" ) )
				{
					if( str.startsWith( billingId ) )
					{
						allowedTypeIds.addAll( Utils.toIntegerSet( str.split( ":" )[1] ) );
					}
				}
			}
		}
		return allowedTypeIds;
	}

	public ActionForward balance( ActionMapping mapping,
	                              DynActionForm form,
	                              HttpServletRequest request,
	                              HttpServletResponse response,
	                              ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );
		
		Date[] period = getPeriod( form );

		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );

		List<ContractBalanceGeneral> balanceList = new ArrayList<ContractBalanceGeneral>();
		
		form.getResponse().setData( "list", balanceList );
		form.getResponse().setData( "summs", balanceDAO.getContractBalanceList( contractId, period[0], period[1], balanceList ) );
		form.getResponse().setData( "contractInfo", new ContractDAO( form.getUser(), billingId ).getContractInfo( contractId ) );
		
		return data( conSet, mapping, form, "balance" );
	}

	public ActionForward balanceDetail( ActionMapping mapping,
	                                    DynActionForm form,
	                                    HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );
		
		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );

		List<ContractBalanceDetail> balanceList = new ArrayList<ContractBalanceDetail>();
		form.getResponse().setData( "list", balanceList );
		
		Date[] period = getPeriod( form );
		form.getResponse().setData( "summa", balanceDAO.getContractBalanceDetailList( contractId, period[0], period[1], balanceList ) );
		form.getResponse().setData( "contractInfo", new ContractDAO( form.getUser(), billingId ).getContractInfo( contractId ) );

		return data( conSet, mapping, form, "balanceDetail" );
	}
	
	public ActionForward paymentList( ActionMapping mapping,
	                                  DynActionForm form,
	                                  HttpServletRequest request,
	                                  HttpServletResponse response,
	                                  ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );
		
		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );
		
		List<ContractPayment> paymentList = new ArrayList<ContractPayment>();
		form.getResponse().setData( "list", paymentList );
		
		List<ContractPayment> subPaymentList = new ArrayList<ContractPayment>();
		form.getResponse().setData( "subList", subPaymentList );
		
		Date[] period = getPeriod( form );
		
		form.getResponse().setData( "summa", balanceDAO.getContractPaymentList( contractId, period[0], period[1], paymentList, subPaymentList ) );
		form.getResponse().setData( "contractInfo", new ContractDAO( form.getUser(), billingId ).getContractInfo( contractId ) );

		return data( conSet, mapping, form, "paymentList" );
	}
	
	public ActionForward chargeList( ActionMapping mapping,
	                                 DynActionForm form,
	                                 HttpServletRequest request,
	                                 HttpServletResponse response,
	                                 ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );

		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );

		List<ContractCharge> chargeList = new ArrayList<ContractCharge>();
		form.getResponse().setData( "list", chargeList );
		
		List<ContractCharge> subChargeList = new ArrayList<ContractCharge>();
		form.getResponse().setData( "subList", subChargeList );
		
		Date[] period = getPeriod( form );

		form.getResponse().setData( "summa", balanceDAO.getContractChargeList( contractId, period[0], period[1], chargeList, subChargeList ) );
		form.getResponse().setData( "contractInfo", new ContractDAO( form.getUser(), billingId ).getContractInfo( contractId ) );
		
		return data( conSet, mapping, form, "chargeList" );
	}

	public ActionForward accountList( ActionMapping mapping,
	                                  DynActionForm form,
	                                  HttpServletRequest request,
	                                  HttpServletResponse response,
	                                  ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );
		
		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );

		Date[] period = getPeriod( form );
		
		List<ContractAccount> chargeList = new ArrayList<ContractAccount>();
		form.getResponse().setData( "list", chargeList );
		
		List<ContractAccount> subChargeList = new ArrayList<ContractAccount>();
		form.getResponse().setData( "subList", subChargeList );

		form.getResponse().setData( "summa", balanceDAO.getContractAccountList( contractId, period[0], period[1], chargeList, subChargeList ) );
		form.getResponse().setData( "contractInfo", new ContractDAO( form.getUser(), billingId ).getContractInfo( contractId ) );

		return data( conSet, mapping, form, "accountList" );
	}

	private Date[] getPeriod( DynActionForm form )
	{
		Calendar calendar = Calendar.getInstance();
    	calendar.setTime( new Date() );
    	calendar.set( Calendar.DAY_OF_MONTH, 1 );
    	Date dateFrom = TimeUtils.parse( form.getParam( "dateFrom", "" ), TimeUtils.PATTERN_DDMMYYYY, calendar.getTime());
    	calendar.set( Calendar.DAY_OF_MONTH, calendar.getActualMaximum( Calendar.DAY_OF_MONTH ) );
    	Date dateTo = TimeUtils.parse( form.getParam( "dateTo", "" ), TimeUtils.PATTERN_DDMMYYYY ,calendar.getTime());
    	
    	form.getResponse().setData( "dateFrom", dateFrom );
		form.getResponse().setData( "dateTo", dateTo );
		
		return new Date[]{ dateFrom, dateTo };
	}
	
	public ActionForward updateBalance( ActionMapping mapping,
										DynActionForm form,
										HttpServletRequest request,
										HttpServletResponse response,
										ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		int contractId = form.getParamInt( "contractId" );
		String item = form.getParam( "item" );
		Date date = TimeUtils.parse( form.getParam( "date", "" ), TimeUtils.PATTERN_DDMMYYYY );

		int id = form.getParamInt( "id", 0 );
		int typeId = form.getParamInt( "typeId" );
		BigDecimal summa = Utils.parseBigDecimal( form.getParam( "summa", "").replace(',', '.') );
		String comment = form.getParam( "comment" );

		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );

		if( "contractPayment".equals( item ) )
		{
			id = balanceDAO.updateContractPayment( id, contractId, summa, date, typeId, comment );
		}
		else if( "contractCharge".equals( item ) )
		{
			id = balanceDAO.updateContractCharge( id, contractId, summa, date, typeId, comment );
		}
		form.getResponse().setData( "id", id );

		return status( conSet, form );
	}
	
	public ActionForward deletePayment( ActionMapping mapping,
										DynActionForm form,
										HttpServletRequest request,
										HttpServletResponse response,
										ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		Integer paymentId = form.getParamInt( "paymentId" );
		Integer contractId = form.getParamInt( "contractId" );

		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );
		balanceDAO.deleteContractPayment( paymentId, contractId );

		return status( conSet, form);
	}

	public ActionForward deleteCharge( ActionMapping mapping,
									   DynActionForm form,
									   HttpServletRequest request,
									   HttpServletResponse response,
									   ConnectionSet conSet )
		throws BGException
	{
		String billingId = form.getParam( "billingId" );
		Integer chargeId = form.getParamInt( "chargeId" );
		Integer contractId = form.getParamInt( "contractId" );

		BalanceDAO balanceDAO = new BalanceDAO( form.getUser(), billingId );
		balanceDAO.deleteContractCharge( chargeId, contractId );

		return status( conSet, form );
	}
}