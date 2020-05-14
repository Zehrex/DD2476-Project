8
https://raw.githubusercontent.com/nataraz123/Spring/master/IOCProj39-LayeredApp-NestedBeanFactory/src/main/java/com/nt/dao/EmployeeDAO.java
package com.nt.dao;

import java.util.List;

import com.nt.bo.EmployeeBO;

public interface EmployeeDAO {
	public  List<EmployeeBO>  getEmpsByDesgs(String desg1,String desg2)throws Exception;

}
