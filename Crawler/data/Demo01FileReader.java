2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/17.%E3%80%90%E7%BA%BF%E7%A8%8B%E3%80%81%E5%90%8C%E6%AD%A5%E3%80%91-%E7%AC%94%E8%AE%B0/21.%E3%80%90%E7%BC%93%E5%86%B2%E6%B5%81%E3%80%81%E8%BD%AC%E6%8D%A2%E6%B5%81%E3%80%81%E5%BA%8F%E5%88%97%E5%8C%96%E6%B5%81%E3%80%81%E6%89%93%E5%8D%B0%E6%B5%81%E3%80%91-%E7%AC%94%E8%AE%B0/code/10_IO/src/com/itheima/demo03/ReverseStream/Demo01FileReader.java
package com.itheima.demo03.ReverseStream;

import java.io.FileReader;
import java.io.IOException;

/*
    FileReader可以读取IDE默认编码格式(UTF-8)的文件
    FileReader读取系统默认编码(中文GBK)会产生乱码���
 */
public class Demo01FileReader {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("10_IO\\我是GBK格式的文本.txt");
        int len = 0;
        while((len = fr.read())!=-1){
            System.out.print((char)len);
        }
        fr.close();
    }
}
