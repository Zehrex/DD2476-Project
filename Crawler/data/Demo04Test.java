2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/17.%E3%80%90%E7%BA%BF%E7%A8%8B%E3%80%81%E5%90%8C%E6%AD%A5%E3%80%91-%E7%AC%94%E8%AE%B0/21.%E3%80%90%E7%BC%93%E5%86%B2%E6%B5%81%E3%80%81%E8%BD%AC%E6%8D%A2%E6%B5%81%E3%80%81%E5%BA%8F%E5%88%97%E5%8C%96%E6%B5%81%E3%80%81%E6%89%93%E5%8D%B0%E6%B5%81%E3%80%91-%E7%AC%94%E8%AE%B0/code/10_IO/src/com/itheima/demo03/ReverseStream/Demo04Test.java
package com.itheima.demo03.ReverseStream;

import java.io.*;

/*
    练习：转换文件编码
        将GBK编码的文本文件，转换为UTF-8编码的文本文件。

    分析:
        1.创建InputStreamReader对象,构造方法中传递字节输入流和指定的编码表名称GBK
        2.创建OutputStreamWriter对象,构造方法中传递字节输出流和指定的编码表名称UTF-8
        3.使用InputStreamReader对象中的方法read读取文件
        4.使用OutputStreamWriter对象中的方法write,把读取的数据写入到文件中
        5.释放资源
 */
public class Demo04Test {
    public static void main(String[] args) throws IOException {
        //1.创建InputStreamReader对象,构造方法中传递字节输入流和指定的编码表名称GBK
        InputStreamReader isr = new InputStreamReader(new FileInputStream("10_IO\\我是GBK格式的文本.txt"),"GBK");
        //2.创建OutputStreamWriter对象,构造方法中传递字节输出流和指定的编码表名称UTF-8
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("10_IO\\我是utf_8格式的文件.txt"),"UTF-8");
        //3.使用InputStreamReader对象中的方法read读取文件
        int len = 0;
        while((len = isr.read())!=-1){
            //4.使用OutputStreamWriter对象中的方法write,把读取的数据写入到文件中
            osw.write(len);
        }
        //5.释放资源
        osw.close();
        isr.close();
    }
}
