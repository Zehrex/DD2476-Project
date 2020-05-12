1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/dao/ProductOrderDaoImpl.java
package com.capgemini.drinkanddelight.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.drinkanddelight.entity.Distributor;
import com.capgemini.drinkanddelight.entity.ProductOrder;


@Repository
public class ProductOrderDaoImpl implements ProductOrderDaoI{
	
	@PersistenceContext
	EntityManager entityManager;
	
	/* Json Format
	 * This method is used to give the list of deliveryStatus and OrderId by using the distributorId
	 * Method 	 : displayOrder1
	 * Type 	 : List<ProductOrderEntity> 
	 * parameters: distributorId 
	 * Author 	 : VijayKumbam
	 * Date 	 : 21/04/2020
	 */
	public List<ProductOrder> displayId(String distributorId)
	{
		String Qstr="SELECT productorderentity from ProductOrder productorderentity";
		TypedQuery<ProductOrder> query=entityManager.createQuery(Qstr,ProductOrder.class);
		List<ProductOrder>result = query.getResultList();

		List<ProductOrder> res = new ArrayList<ProductOrder>();
		
		Distributor de =  entityManager.find(Distributor.class, distributorId);
		if(de != null) 
		{
			Iterator<ProductOrder> itr =result.iterator();
			while(itr.hasNext()) 
				{
				ProductOrder element = itr.next();
				if(element.getDistributor().getDistributorId().contentEquals(distributorId))
					{
						//System.out.println("DistributorId ="+id+" " +"deliveryStatus="+element.getDeliveryStatus());
						res.add(element);
						//return element.getDeliveryStatus();
					}
				}
		}
		else
		{
			return null;
		}
		return res;	
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
public List<ProductOrder>  displayIdwithStatus(String id, String status) {
		
		String delivStatus = status.toUpperCase();
		String Qstr="SELECT productorderentity from ProductOrder productorderentity";
		TypedQuery<ProductOrder> query=entityManager.createQuery(Qstr,ProductOrder.class);
		List<ProductOrder>result = query.getResultList();

		List<ProductOrder> res = new ArrayList<ProductOrder>();
		
		Distributor de =  entityManager.find(Distributor.class, id);
		if(de != null) 
		{
			Iterator<ProductOrder> itr =result.iterator();
			while(itr.hasNext()) 
				{
				ProductOrder element = itr.next();
				if(element.getDistributor().getDistributorId().contentEquals(id) && element.getDeliveryStatus().contentEquals(delivStatus))
					{
						//System.out.println("DistributorId ="+id+" " +"deliveryStatus="+element.getDeliveryStatus());
						res.add(element);
						//return element.getDeliveryStatus();
					}
				}
		}
		else
		{
			return null;
		}
		return res;	
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
		String Qstr="SELECT productorderentity from Distributor productorderentity";
		TypedQuery<Distributor> query=entityManager.createQuery(Qstr,Distributor.class);
		return query.getResultList();
		
	}



//	@Override
//	public boolean deleteDistributor(String distributorId) {
//		Distributor result = entityManager.find(Distributor.class, distributorId);
//		if(result != null)
//			{
//			System.out.println("hello delte");
//				entityManager.remove(result);
//				return true;
//			}
//		return false;
//	}



	@Override
	public boolean updateDistributor(String distributorId,String name,String address,long phonenumber) {
		Distributor result = entityManager.find(Distributor.class, distributorId);
		if(result !=null)
		{
			result.setAddress(address);
			result.setPhoneNumber(phonenumber);
			result.setName(name);
			entityManager.flush();
			return true;
		}
		return false;
	}


}
