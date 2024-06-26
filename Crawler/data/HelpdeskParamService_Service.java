12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/bgbilling/ws/helpdesk/param/HelpdeskParamService_Service.java

package ru.bgcrm.plugin.bgbilling.ws.helpdesk.param;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HelpdeskParamService", targetNamespace = "http://service.common.helpdesk.plugins.bgbilling.bitel.ru/", wsdlLocation = "http://billing.office.bitel.ru:8081/executer/ru.bitel.bgbilling.plugins.helpdesk/HelpdeskParamService?wsdl")
public class HelpdeskParamService_Service
    extends Service
{

    private final static URL HELPDESKPARAMSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELPDESKPARAMSERVICE_EXCEPTION;
    private final static QName HELPDESKPARAMSERVICE_QNAME = new QName("http://service.common.helpdesk.plugins.bgbilling.bitel.ru/", "HelpdeskParamService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://billing.office.bitel.ru:8081/executer/ru.bitel.bgbilling.plugins.helpdesk/HelpdeskParamService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELPDESKPARAMSERVICE_WSDL_LOCATION = url;
        HELPDESKPARAMSERVICE_EXCEPTION = e;
    }

    public HelpdeskParamService_Service() {
        super(__getWsdlLocation(), HELPDESKPARAMSERVICE_QNAME);
    }

    public HelpdeskParamService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELPDESKPARAMSERVICE_QNAME, features);
    }

    public HelpdeskParamService_Service(URL wsdlLocation) {
        super(wsdlLocation, HELPDESKPARAMSERVICE_QNAME);
    }

    public HelpdeskParamService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELPDESKPARAMSERVICE_QNAME, features);
    }

    public HelpdeskParamService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelpdeskParamService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HelpdeskParamService
     */
    @WebEndpoint(name = "HelpdeskParamService")
    public HelpdeskParamService getHelpdeskParamService() {
        return super.getPort(new QName("http://service.common.helpdesk.plugins.bgbilling.bitel.ru/", "HelpdeskParamService"), HelpdeskParamService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelpdeskParamService
     */
    @WebEndpoint(name = "HelpdeskParamService")
    public HelpdeskParamService getHelpdeskParamService(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.common.helpdesk.plugins.bgbilling.bitel.ru/", "HelpdeskParamService"), HelpdeskParamService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELPDESKPARAMSERVICE_EXCEPTION!= null) {
            throw HELPDESKPARAMSERVICE_EXCEPTION;
        }
        return HELPDESKPARAMSERVICE_WSDL_LOCATION;
    }

}
