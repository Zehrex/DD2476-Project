1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/dao/ProductOrderDaoI.java
package com.capgemini.drinkanddelight.dao;

import java.util.List;

import com.capgemini.drinkanddelight.entity.Distributor;
import com.capgemini.drinkanddelight.entity.ProductOrder;

public interface ProductOrderDaoI {

	public List<ProductOrder> displayId(String distributorId);
	
	public List<ProductOrder>  displayIdwithStatus(String id, String status);
	
	public List<Distributor> getDistributor();
	
	public boolean updateDistributor(String distributorId,String name,String address,long phonenumber);
}
