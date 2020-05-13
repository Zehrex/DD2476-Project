2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/final_/FinalTest01.java
package final_;
/*
 * 关于Java语言中final关键字：
 *     1.final是一个关键字，表示最终的，不可变的。
 *     2.final修饰的类无法继承
 *     3.final修饰的方法无法覆盖
 *     4.final修饰的变量一旦赋值后，不可重新赋值
 *     5.final修饰的实例变量？
 *         -实例变量有默认值，所以实例变量使用final关键字修饰后，必须手动赋值。
 *     6.final修饰的引用？
 *         -final修饰的引用，一旦指向某个对象之后，不能再指向其他对象，那么被指向的对象无法被垃圾回收器回收
 *         -final修饰的引用虽然指向某个对象之后不能指向其他对象，但是所指向的对象内部的内存是可以被修改的
 *     7.final修饰的实例变量是不可变的，这种变量一般和static联合使用，被称为“量”
 *         -常量的定义语法格式：
 *             public static final 类型 常量名 = 值;
 *         -Java规范中要求所有常量名的名字全部大写，每个单词之间使用下划线连接。
 *关于eclipse怎么链接源码？
 *    打开某个.class字节码文件，当看到没有源码的时候：
 *      点击“Attached Source”
 *        -Workspace  【源码在当前的工作区当中】
 *        -External File  【源码在某个压缩包当中】
 *        -External Folder  【源码在某个目录当中 】
 *        
 * 以后尽量所有的程序员都链接源码，没有源码从网上找。
 * 养成看源代码的好习惯。
 * 
 * 对于以后所学的类库，一般都是包括三个部分的：
 *     -源码  【可以看源码来理解程序】
 *     -字节码  【程序的开发过程中使用的就是这部分】
 *     -帮助文档  【对开发提供帮助】
 *           注意使用时候的版本统一。
 */
public class FinalTest01 {

	public static void main(String[] args) {
		
	}

}
