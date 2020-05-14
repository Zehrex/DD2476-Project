8
https://raw.githubusercontent.com/nataraz123/Spring/master/IOCProj39-LayeredApp-NestedBeanFactory/src/main/java/com/nt/service/EmployeeMgmtService.java
package com.nt.service;

import java.util.List;

import com.nt.dto.EmployeeDTO;

public interface EmployeeMgmtService {
	
	public  List<EmployeeDTO> fetchEmpsByDesgs(String desg1,String desg2)throws Exception;

}
