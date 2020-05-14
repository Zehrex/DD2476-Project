8
https://raw.githubusercontent.com/nataraz123/Spring/master/IOCProj42-NullIInjection-AC-Pre-instantiation/src/main/java/com/nt/beans/PersonProfile.java
package com.nt.beans;

import java.util.Date;

public class PersonProfile {
	private long aadharNo;
	private String name;
	private float age;
	private Date dob,doj,dom;
	
	
	public PersonProfile(long aadharNo, String name, float age, Date dob, Date doj, Date dom) {
		System.out.println("PersonProfile:: 6-param constructor");
		this.aadharNo = aadharNo;
		this.name = name;
		this.age = age;
		this.dob = dob;
		this.doj = doj;
		this.dom = dom;
	}


	@Override
	public String toString() {
		return "PersonProfile [aadharNo=" + aadharNo + ", name=" + name + ", age=" + age + ", dob=" + dob + ", doj="
				+ doj + ", dom=" + dom + "]";
	}
	
	
	
	

}
