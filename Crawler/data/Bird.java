2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/duotai/Bird.java
package duotai;

public class Bird extends Animal{
	public void move() {
		System.out.println("鸟儿在飞翔！"); 
	}
	
	//子类对象特有的行为
	public void fly() {
		System.out.println("Bird fly!");
	}
}
