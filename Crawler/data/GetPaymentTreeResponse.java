12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/plugin/bgbilling/ws/contract/balance/paymcharge/GetPaymentTreeResponse.java

package ru.bgcrm.plugin.bgbilling.ws.contract.balance.paymcharge;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPaymentTreeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPaymentTreeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://common.balance.contract.kernel.bgbilling.bitel.ru/}paymentTypeItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPaymentTreeResponse", propOrder = {
    "_return"
})
public class GetPaymentTreeResponse {

    @XmlElement(name = "return")
    protected PaymentTypeItem _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeItem }
     *     
     */
    public PaymentTypeItem getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeItem }
     *     
     */
    public void setReturn(PaymentTypeItem value) {
        this._return = value;
    }

}
