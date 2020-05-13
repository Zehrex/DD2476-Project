2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/duotai/Cat.java
package duotai;

//猫类
public class Cat extends Animal{
	
	//重写父类中继承过来的方法
	public void move() {
		System.out.println("猫在走猫步！"); 
	}
	public void catchMouse() {
		System.out.println("猫抓老鼠！");
	}
}
