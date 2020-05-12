2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/itcast/annotation/annotation.java
package java2.itcast.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 *  元注解：用于描述注解的注解
 *      @Target：描述注解能够作用的位置
 *          ElementType 取值：
 *              TYPE：可以作用于类上
 *              METHOD：可以作用于方法上
 *              FIELD：可以作用于成员变量上
 *      @Retention：描述注解被保留的阶段
 *          Retention(RetentionPolicy.RUNTIME)：当前被描述的注解，会保留到 class 字节码文件中，并被 JVM 读取到
 *      @Inherited：描述注解是否被子类继承
 *
 *
 *  在程序使用（解析）注解：获取注解中定义的属性值
 *      1、获取注解定义的位置的对象（Class，Method，Field）
 *      2、获取指定的注解
 *          getAnnotation(class)
 *      3、调用注解中的抽象方法获取配置的属性值
 */

@Pro(className = "java2.itcast.annotation.Demo1", methodName = "show")
public class annotation {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //可以创建任意类的对象，可以执行任意方法

        /*
         * 前提：不能改变该类的任何代码，可以创建任意类的对象，可以执行任意方法
         */

        //1、解析注解
        //1.1 获取该类的字节码对象
        Class<annotation> annotationClass = annotation.class;
        //2、获取上边的注解对象
        //其实就是在内存中生成了一个该注解接口的子类实现对象

        /**
         *  public class ProImpl implements Pro{
         *      public String className(){
         *          return "java2.itcast.annotation.Demo1";
         *      }
         *
         *      public String methodName(){
         *          return "show";
         *      }
         *  }
         */
        Pro an = annotationClass.getAnnotation(Pro.class);
        //3、调用注解对象中的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();

        System.out.println(className);
        System.out.println(methodName);


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
