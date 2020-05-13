1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/service/ProductOrderServiceImpl.java
package com.capgemini.drinkanddelight.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.drinkanddelight.dao.ProductOrderDaoI;
import com.capgemini.drinkanddelight.entity.Distributor;
import com.capgemini.drinkanddelight.entity.ProductOrder;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderServiceI {

	@Autowired 
	private ProductOrderDaoI productorderdaoi;

	/* Json Format
	 * This method is used to give the list of deliveryStatus and OrderId by using the distributorId
	 * Method 	 : displayId
	 * Type 	 : List<ProductOrderEntity> 
	 * parameters: distributorId 
	 * Author 	 : VijayKumbam
	 * Date 	 : 21/04/2020
	 */
	@Override
	public List<ProductOrder> displayId(String distributorId) {
		return productorderdaoi.displayId(distributorId);
	}
	

	/* Json Format
	 * This method is used to give the list of Product orders by using the distributorId and Status
	 * Method 	 : displayIdwithStatus
	 * Type 	 : List<ProductOrderEntity>
	 * parameters: distribuotrId,status 
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */
	@Override
	public List<ProductOrder> displayIdwithStatus(String distributorId, String status) {
		return productorderdaoi.displayIdwithStatus(distributorId, status);
	}
	
	/* Json Format
	 * This method is used to give the list ofDistributors
	 * Method 	 : getDistributor
	 * Type 	 : List<Distributor>
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */
	@Override
	public List<Distributor> getDistributor() {
		return productorderdaoi.getDistributor();
	}
	
	/* Json Format
	 * This method is used to update the distributorTable by using DistributorId
	 * Method 	 : updateDistributor
	 * Type 	 : boolean
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */

	@Override
	public boolean updateDistributor(String distributorId,String name,String address,long phonenumber) {
		return productorderdaoi.updateDistributor(distributorId, name, address, phonenumber);
	}


//	@Override
//	public boolean deleteDistributor(String distributorId) {
//		System.out.println("hello in service");
//		return productorderdaoi.deleteDistributor(distributorId);
//	}






	
	
}
