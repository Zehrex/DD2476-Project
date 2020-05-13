2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/Jicheng.java
package ray;

import java.util.Arrays;

/*1.继承是面向对象三大特征之一，三大特征分别是：封装、继承、多态。
 * 2.继承“基本”的作用是：代码复用。但是继承最重要的作用是：有了继承才有了以后的“方法的覆盖”和多态机制“。
 * 3.继承语法格式：
 *       [修饰符列表] class 类名 extends 父类名{
 *                    类体 = 属性 + 方法
 *       }
 *       4.Java语言当中的继承支支持单继承，一个类不能同时继承很多类，只能继承一个类。在c++中支持多继承。
 *       5.关于继承的一些术语：
 *       B类继承A类，其中：
 *           A类称为：父类、基类、超类、superclass
 *           B类成为：子类、派生类、subclass
 *       6.在Java语言当中子类继承父类都有哪些数据呢？
 *           *私有的不支持继承
 *           *构造方法不支持继承
 *           *其他数据都可以被继承
 *       7.虽然Java语言只支持单继承，但是一个类也可以间接其他类，例如：
 *               C extends B{
 *               }
 *               B extends A{
 *               }
 *               A extends T{
 *               }
 *               c类直接继承B类，但是C类间接T、A类。
 *        8.Java语言中假设一个类没有显示的继承任何类，该类默认继承JavaSE库当中提供的java.lang.Object类。
 *        Java语言中任何一个类中都有Object类的特征。
 * */
public class Jicheng {
//查找类快捷键CTRL+shift+t
	public static void main(String[] args) {
		/*继承Object类的特征的试验。
		Jicheng ji = new Jicheng();
		String s = ji.toString();
		System.out.println(s);
		*/
		
		//继承的实现，Jichengdemo继承Jichengtest。
		Jichengdemo j = new Jichengdemo();
		j.setActno("j-001");
		j.setBanlance(-1000.0);
		j.setCredit(0.99);
		 
		System.out.println(j.getActno() + "," + j.getBanlance() + "," + j.getCredit());
	}
}
