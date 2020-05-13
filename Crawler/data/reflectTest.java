2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/itcast/reflect/reflectTest.java
package java2.itcast.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 *  案例：
 *      需求：写一个"框架"，不能改变该类的任何代码的前提下，可以帮助我们创建任意类的对象，并且执行其中任意方法
 *          实现：
 *              1、配置文件
 *              2、反射
 *          步骤：
 *              1、将需要创建的对象的全类名和需要执行的方法定义在配置文件中
 *              2、在程序中加载读取配置文件
 *              3、使用反射技术来加载类文件进内存
 *              4、创建对象
 *              5、执行方法
 */
public class reflectTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        //可以创建任意类的对象，可以执行任意方法

        /*
         * 前提：不能改变该类的任何代码，可以创建任意类的对象，可以执行任意方法
         */
//        Person p = new Person();
//        p.eat();


        //1、加载配置文件
        //1.1 创建 properties 对象
        Properties pro = new Properties();

        //1.2 加载配置文件，转化为一个集合
        //1.2.1 获取当前目录下的配置文件
        ClassLoader classLoader = reflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("java2/itcast/pro.properties");
        System.out.println(is);
        pro.load(is);

        //2、获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //System.out.println(className);

        //3、加载进内存
        Class<?> cls = Class.forName(className);

        //4、创建对象
        Object obj = cls.newInstance();

        //5、获取对象方法
        Method method = cls.getMethod(methodName);

        //6、执行方法
        method.invoke(obj);
    }
}
