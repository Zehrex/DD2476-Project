1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/service/ProductOrderServiceI.java
package com.capgemini.drinkanddelight.service;

import java.util.List;

import com.capgemini.drinkanddelight.entity.Distributor;
import com.capgemini.drinkanddelight.entity.ProductOrder;

public interface ProductOrderServiceI {

	public List<ProductOrder> displayId(String distributorId);
	
	public List<ProductOrder> displayIdwithStatus(String distributorId, String status); 
	
	
	public List<Distributor> getDistributor();
	
	
	public boolean updateDistributor(String distributorId,String name,String address,long phonenumber);

	//public boolean deleteDistributor(String distributorId);

}
