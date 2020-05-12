1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/entity/ProductOrder.java
package com.capgemini.drinkanddelight.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class ProductOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Size(min = 2, max = 10, message = "OrderId should be atleast 2 digits")
	private String orderId;
	private String name;
	private double quantityValue;
	private double quantityUnit;
	private Date dateOfOrder;
	private Date dateOfDelivery;
	private double price_per_unit;
	private double totalPrice;
	private String deliveryStatus;
	private String warehouseId;
	
	
	@ManyToOne(targetEntity = Distributor.class )
	@JoinColumn(name = "Distributor_Id",nullable = false)
	private Distributor distributor;
	
	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantityValue() {
		return quantityValue;
	}

	public void setQuantityValue(double quantityValue) {
		this.quantityValue = quantityValue;
	}

	public double getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(double quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public double getPrice_per_unit() {
		return price_per_unit;
	}

	public void setPrice_per_unit(double price_per_unit) {
		this.price_per_unit = price_per_unit;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	/*
	 * No args constructor for ProductEntity All Args constructor for ProductEntity
	 * 
	 * 
	 * 
	 */
	
	
	
	
	@Override
	public String toString() {
		return "ProductOrder [orderId=" + orderId + ", name=" + name + ", quantityValue=" + quantityValue
				+ ", quantityUnit=" + quantityUnit + ", dateOfOrder=" + dateOfOrder + ", dateOfDelivery="
				+ dateOfDelivery + ", price_per_unit=" + price_per_unit + ", totalPrice=" + totalPrice
				+ ", deliveryStatus=" + deliveryStatus + ", warehouseId=" + warehouseId + ", distributor=" + distributor
				+ "]";
	}
	
	
	
	
	
	
	

}
