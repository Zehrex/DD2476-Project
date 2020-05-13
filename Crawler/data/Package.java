2
https://raw.githubusercontent.com/hasaki-w-c/JAVA--/master/package_/Package.java
package package_;
/*
 * 关于Java语言当中的包机制：
 *     1.包又称为package。Java中引入package这种语法机制主要是为了方便管理程序。
 *              不同功能的类被分门别类地放到不同的软件包当中，查找比较方便，管理也比较方便，易维护。
 *     2.怎么定义package呢？
 *       -在Java源程序的第一行上编写package语句
 *       -package只能编写一个语句。
 *       -语法结构： package 包名;
 *     3.包的命名规范：公司域名倒序 + 项目名 + 模块名 + 功能名；
 *       - 例如：com.bjpowernode.oa.user.service;org.apache.tomcat.core;
 *     4.包名要求全部小写，包名也是标识符，必须遵守标识符的命名规则。
 *     5.一个包将来对应的是一个目录
 *     6.使用了package机制后，应该怎么编译？怎么运行呢？
 *       —使用了package机制之后，类名不再是Package了，类名是package_.Package
 *       -编译：javac java源文件路径（在硬盘上生成一个class文件：Package.class ）
 *       -手动方式创建目录，将Package.class字节码文件放到指定的目录下
 *       -运行：java package_.Package
 *       -另一种方式（编译 + 运行）：
 *           *编译：javac -d 编译之后存放路径 java源文件的路径
 *           *例如：将F；\Hello.java文件编译之后放到C:\目录下
 *               -javac -d C:\F:\Hello.java
 *           *java -d . *.java
 *               -将当前路径中*。java编译之后存放到当前目录下。
 *           *运行：JVM的类加载器ClassLoader默认从当前路径下载。
 *               -保证DOS命令窗口的路径先切换到package所在的路径，执行：
 *               package_.Package.
 *     7.当俩个类在同一个包下的时候不需要加包名。
 */
public class Package {

	public static void main(String[] args) {
		System.out.println("Package's main method execute!");
	}

}
