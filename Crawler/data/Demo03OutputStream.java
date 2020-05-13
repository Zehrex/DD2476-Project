2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/16.%E3%80%90%E5%BC%82%E5%B8%B8%E3%80%81%E7%BA%BF%E7%A8%8B%E3%80%91-%E7%AC%94%E8%AE%B0/20.%E3%80%90%E5%AD%97%E8%8A%82%E6%B5%81%E3%80%81%E5%AD%97%E7%AC%A6%E6%B5%81%E3%80%91-%E7%AC%94%E8%AE%B0/code/09_IOAndProperties/src/com/itheima/demo01/OutputStream/Demo03OutputStream.java
package com.itheima.demo01.OutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

/*
    追加写/续写:使用两个参数的构造方法
        FileOutputStream(String name, boolean append)创建一个向具有指定 name 的文件中写入数据的输出文件流。
        FileOutputStream(File file, boolean append) 创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
        参数:
           String name,File file:写入数据的目的地
           boolean append:追加写开关
            true:创建对象不会覆盖源文件,继续在文件的末尾追加写数据
            false:创建一个新文件,覆盖源文件
    写换行:写换行符号
        windows:\r\n
        linux:/n
        mac:/r
 */
public class Demo03OutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("09_IOAndProperties\\c.txt",true);
        for (int i = 1; i <=10 ; i++) {
            fos.write("你好".getBytes());
            fos.write("\r\n".getBytes());
        }

        fos.close();
    }
}
