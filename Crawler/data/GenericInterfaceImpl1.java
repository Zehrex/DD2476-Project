2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/13.%E3%80%90Collection%E3%80%81%E6%B3%9B%E5%9E%8B%E3%80%91-%E7%AC%94%E8%AE%B0/code/02_CollectionAndReflect/src/com/itheima/demo03/Generic/GenericInterfaceImpl1.java
package com.itheima.demo03.Generic;
/*
    含有泛型的接口,第一种使用方式:定义接口的实现类,实现接口,指定接口的泛型
    public interface Iterator<E> {
        E next();
    }
    Scanner类实现了Iterator接口,并指定接口的泛型为String,所以重写的next方法泛型默认就是String
    public final class Scanner implements Iterator<String>{
        public String next() {}
    }
 */
public class GenericInterfaceImpl1 implements GenericInterface<String>{
    @Override
    public void method(String s) {
        System.out.println(s);
    }
}
