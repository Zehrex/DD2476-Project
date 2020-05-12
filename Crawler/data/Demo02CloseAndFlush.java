2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/16.%E3%80%90%E5%BC%82%E5%B8%B8%E3%80%81%E7%BA%BF%E7%A8%8B%E3%80%91-%E7%AC%94%E8%AE%B0/20.%E3%80%90%E5%AD%97%E8%8A%82%E6%B5%81%E3%80%81%E5%AD%97%E7%AC%A6%E6%B5%81%E3%80%91-%E7%AC%94%E8%AE%B0/code/09_IOAndProperties/src/com/itheima/Demo05Writer/Demo02CloseAndFlush.java
package com.itheima.Demo05Writer;

import java.io.FileWriter;
import java.io.IOException;

/*
    flush方法和close方法的区别
        - flush ：刷新缓冲区，流对象可以继续使用。
        - close:  先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。
 */
public class Demo02CloseAndFlush {
    public static void main(String[] args) throws IOException {
        //1.创建FileWriter对象,构造方法中绑定要写入数据的目的地
        FileWriter fw = new FileWriter("09_IOAndProperties\\e.txt");
        //2.使用FileWriter中的方法write,把数据写入到内存缓冲区中(字符转换为字节的过程)
        //void write(int c) 写入单个字符。
        fw.write(97);
        //3.使用FileWriter中的方法flush,把内存缓冲区中的数据,刷新到文件中
        fw.flush();
        //刷新之后流可以继续使用
        fw.write(98);

        //4.释放资源(会先把内存缓冲区中的数据刷新到文件中)
        fw.close();

        //close方法之后流已经关闭了,已经从内存中消失了,流就不能再使用了
        fw.write(99);//IOException: Stream closed
    }
}
