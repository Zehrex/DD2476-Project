2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/itcast/test/TestCheck.java
package java2.itcast.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  简单的测试框架
 *
 *  当主方法执行后，会自动自行被检测的所有方法（加了Check注解的方法），判断方法是否有异常，记录到文件中
 *
 *
 *
 *  【小结】：
 *      1、以后大多数时候，我们会使用注解，而不是自定义注解
 *      2、注解给谁用？
 *          1、编译器
 *          2、给解析程序用
 *      3、注解不是程序的一部分，可以理解为注解就是一个标签
 */
public class TestCheck {

    public static void main(String[] args) throws IOException {
        //1、创建计算器对象
        Calculator c = new Calculator();

        //2、获取字节码文件对象
        Class<? extends Calculator> cls = c.getClass();

        //3、获取所有方法
        Method[] methods = cls.getMethods();

        int number = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        //4、判断方法上是否有Check注解
        for (Method method: methods) {
            if(method.isAnnotationPresent(Check.class)){
                //5、有，执行
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    //6、捕获异常

                    //记录到文件中
                    number++;

                    bw.write(method.getName() + "方法出异常了");
                    bw.newLine();
                    bw.write("异常的名称：" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因：" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("----------------------------------");
                    bw.newLine();
                }
            }
        }


        bw.write("本次测试一共出现" + number + "次异常");
        bw.flush();
        bw.close();
    }
}
