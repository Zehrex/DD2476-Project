2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/duotai/Zhuren.java
package duotai;
/*
 * z主人
 */
/*
public class Zhuren {
	
	 //喂养宠物的方法
	 
	public void feed(Chongwu c) {
		c.eat();
	}
	public void eat(Dogcw d) {
		d.eat();
	}
}
//Zhuren和Chongwu、Dogcw这俩个类型的关联程度很强，耦合度很高，扩展力差。
*/

//降低程序的耦合度【解耦合】，提高程序扩展力【软件开发的一个很重要的目标】
public class Zhuren {

	//Zhuren主人类面向的是一个抽象的Pet,不再面向具体的宠物
	//提倡：面向抽象编程，不要面向具体编程。
	//面向抽象编程的好处是，耦合度低，扩展力强。
	public void feed(Pet pet) { //Pea pet 是一个父类型的引用
		pet.eat();
	}
}
