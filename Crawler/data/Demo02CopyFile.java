2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/17.%E3%80%90%E7%BA%BF%E7%A8%8B%E3%80%81%E5%90%8C%E6%AD%A5%E3%80%91-%E7%AC%94%E8%AE%B0/21.%E3%80%90%E7%BC%93%E5%86%B2%E6%B5%81%E3%80%81%E8%BD%AC%E6%8D%A2%E6%B5%81%E3%80%81%E5%BA%8F%E5%88%97%E5%8C%96%E6%B5%81%E3%80%81%E6%89%93%E5%8D%B0%E6%B5%81%E3%80%91-%E7%AC%94%E8%AE%B0/code/10_IO/src/com/itheima/demo02/CopyFile/Demo02CopyFile.java
package com.itheima.demo02.CopyFile;

import java.io.*;

/*
    文件复制练习:一读一写

    明确:
        数据源: c:\\1.jpg
        数据的目的地: d:\\1.jpg
    文件复制的步骤:
        1.创建字节缓冲输入流对象,构造方法中传递字节输入流
        2.创建字节缓冲输出流对象,构造方法中传递字节输出流
        3.使用字节缓冲输入流对象中的方法read,读取文件
        4.使用字节缓冲输出流中的方法write,把读取的数据写入到内部缓冲区中
        5.释放资源(会先把缓冲区中的数据,刷新到文件中)

    文件的大小:780,831 字节
    一次读写一个字节:32毫秒
    使用数组缓冲读取多个字节,写入多个字节:5毫秒
 */
public class Demo02CopyFile {
    public static void main(String[] args) throws IOException {
        long s = System.currentTimeMillis();
        //1.创建字节缓冲输入流对象,构造方法中传递字节输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("c:\\1.jpg"));
        //2.创建字节缓冲输出流对象,构造方法中传递字节输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:\\1.jpg"));
        //3.使用字节缓冲输入流对象中的方法read,读取文件
        //一次读取一个字节写入一个字节的方式
        /*int len = 0;
        while((len = bis.read())!=-1){
            bos.write(len);
        }*/

        //使用数组缓冲读取多个字节,写入多个字节
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();

        long e = System.currentTimeMillis();
        System.out.println("复制文件共耗时:"+(e-s)+"毫秒");
    }
}
