2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/leiTest/EmployeeTest.java
package leiTest;

import java.time.LocalDate;
//用户自定义类
public class EmployeeTest {    //把每个员工工资提高5%，并打印出出员信息
	public static void main(String[] args)
	{
		//fill the staff array with three Employee objects
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Wang", 60000, 2015, 12, 15);
		staff[1] = new Employee("Yang", 50000, 2017, 10, 1);
		staff[2] = new Employee("Liu", 40000, 2019, 3, 15);
		
		//raise everyone's salary by 5%
		for (Employee e : staff)
			e.raiseSalary(5);
		
		//print out information about all Employee object
		for (Employee e : staff)
			System.out.println("name=" + e.getName() + ",salary" + e.getSalary() +",hireDay=" + e.gethireDay());		
	}
}

class Employee    //构造器
{
	private String name;
	private double salary;
	private LocalDate hireDay;
	
	//构造器与类同名
	public Employee(String n, double s , int year, int month, int day) {
		name = n;
		salary = s;
		hireDay = LocalDate.of(year, month, day);
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
	
	public LocalDate gethireDay()
	{
		return hireDay;
	}
	
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}