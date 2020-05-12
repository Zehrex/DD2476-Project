1
https://raw.githubusercontent.com/vijaykumbam/DrinkanddelightFinalVersion/master/DrinkandDelightsprintOracle/src/main/java/com/capgemini/drinkanddelight/controller/ProductController.java
package com.capgemini.drinkanddelight.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.drinkanddelight.entity.Distributor;
import com.capgemini.drinkanddelight.entity.ProductOrder;
import com.capgemini.drinkanddelight.exception.DistributorNotFoundException;
import com.capgemini.drinkanddelight.service.ProductOrderServiceI;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductOrderServiceI productorderservicei;

	/* Json Format
	 * This is a GetMethod(Http) by using "distributorId" is used to fetch the delivery status of particular distributorId.
	 * Method 	 : display1
	 * Type 	 : ResponseEntity<List<ProductOrderEntity>>
	 * parameters: id 
	 * Author 	 : VijayKumbam
	 * Date 	 : 21/04/2020
	 */
	
	@GetMapping("/displayDistributor/{distributorId}")
	public ResponseEntity<List<ProductOrder>> displayDistributor( @PathVariable String distributorId) throws DistributorNotFoundException 
	{
		List<ProductOrder> list = productorderservicei.displayId(distributorId);
		if(list == null)
			throw new DistributorNotFoundException(distributorId+" "+"distributorId Not Found In distributor Table");
		return new ResponseEntity<List<ProductOrder>>(list,HttpStatus.OK);
	}
	
	/* Json Format
	 * This is a GetMethod(Http) by using "distributorId" AND "deliveryStatus" it is used to fetch the product Entity.
	 * Method 	 : displayIdwithStatus
	 * Type 	 : ResponseEntity<List<ProductOrderEntity>>
	 * parameters: distribuotrId,status 
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */
	
	@GetMapping("/displayDistributor/{distributorId}/{status}")
	public ResponseEntity<List<ProductOrder>> displayIdwithStatus( @PathVariable String distributorId , @PathVariable String status) throws DistributorNotFoundException 
	{
		List<ProductOrder> list = productorderservicei.displayIdwithStatus(distributorId, status);
		if(list==null)
			throw new DistributorNotFoundException(distributorId+" with "+status+" Details Not Found in Database ");
		return new ResponseEntity<List<ProductOrder>>(list,HttpStatus.OK);
	}
	
	/* Json Format
	 * This is a GetMethod(Http) it will fetch the all the records from the Distributor Table 
	 * Method 	 : getDistributorList
	 * Type 	 : ResponseEntity<List<Distributor>>
	 * parameters: None
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */
	
	@GetMapping("/distributorList")
	public ResponseEntity<List<Distributor>> getDistributorList() throws DistributorNotFoundException 
	{
		List<Distributor> list = productorderservicei.getDistributor();
		if(list==null)
			throw new DistributorNotFoundException(" Details Not Found in Database ");
		return new ResponseEntity<List<Distributor>>(list,HttpStatus.OK);
	}
	
	/* Json Format
	 * This is a PutMethod(Http) by using the parameters the particular distributor has to update.
	 * Method 	 : updateDistributor
	 * Type 	 : ResponseEntity<String>
	 * parameters: distribuotrId,name,address,phonenumber 
	 * Author 	 : VijayKumbam
	 * Date 	 : 23/04/2020
	 */
	
	@PutMapping("/updateDistributor/{distributorId}/{name}/{address}/{phonenumber}")
	public ResponseEntity<String> updateDistributor(@PathVariable("distributorId") String distributorId,@PathVariable("name") String name,@PathVariable("address") String address,@PathVariable("phonenumber") long phonenumber ) throws DistributorNotFoundException
	{
		boolean result = productorderservicei.updateDistributor(distributorId, name, address, phonenumber);
		if(result == false)throw new DistributorNotFoundException(" Can't Update your data ");
		return new ResponseEntity<String>("Your details updated successfully",HttpStatus.OK);
	}
	
	
	

}


