2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/ray/This.java
package ray;

//this是一个引用，是一个变量，this中保存了内存地址并指向自身。this存储在JVM堆内存Java对象内部。
//当使用s1去访问对象的时候,整个过程中的this就s1.
//每一个对象都有一个this.
//this可以出现在“实例方法”当中，指向当前正在执行这个动作的对象。
//重点：没有static关键字的方法被称为“实例方法”
//重点：没有static关键字的变量被称为“实例变量”，实例变量：必须采用“引用.”的方式访问。
//注意：当一个行为或动作执行的过程中是需要对象参与的，那么这个方法一定要定义为“实例方法”，不要带static关键字
public class This {
	public static void main(String[] args)
	{
		Thisss s1 = new Thisss();
		s1.name = "zhangsan";
		
		s1.shopping();
		
		Thisss s2 = new Thisss();
		s2.name = "lisi";
		
		s2.shopping();
			
	}
}
