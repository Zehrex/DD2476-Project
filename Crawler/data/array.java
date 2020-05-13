2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/cc1021/fan/array.java
package cc1021.fan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class array {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> array = new ArrayList<>();

        Class<? extends ArrayList> c = array.getClass();
        Method m = c.getMethod("add", Object.class);

        m.invoke(array, "hello");
        m.invoke(array, "world");
        m.invoke(array, "java");

        System.out.println(array);
    }
}
