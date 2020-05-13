2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/duotai/Test2.java
package duotai;

public class Test2 {
	public static void main(String[] args)
	{
		//父类型引用指向子类型对象
		//向上转型
		Animal a1 = new Cat();
		Animal a2 = new Bird();
		
		//向下转型【只有当访问子类对象当中特有的方法】
		if (a1 instanceof Cat) {
			Cat ci = (Cat)a1;
		}
		if (a2 instanceof Bird) {
			Bird b1 = (Bird)a2;
		}
	}
}
