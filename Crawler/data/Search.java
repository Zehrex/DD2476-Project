2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/File/Search.java
package java2.File;

import java.io.File;

/**
 * 练习：
 *      递归打印多级目录
 * 需求：
 *      遍历abc文件夹，及abc文件夹的子文件夹
 *      只要java结尾的文件
 *      abc
 *      abc/abc.txt
 *      abc/abc.java
 *      abc/a
 *      abc/a/a.jpg
 *      abc/a/a.java
 *      abc/b
 *      abc/b/b.java
 *      abc/b/b.txt
 */
public class Search {
    public static void main(String[] args) {
        File file = new File("/Users/chengcheng/Desktop/test/abc");
        getAllFile(file);
    }

    /*
        定义一个方法，参数传递File类型的目录
        方法中对目录进行遍历
     */
    public static void getAllFile(File dir){
        //System.out.println(dir);//打印被遍历的目录结构

        File[] files = dir.listFiles();
        for (File f : files){
            //判断是否文件夹
            if(f.isDirectory()){
                //是一个文件夹，调用自己
                getAllFile(f);
            }else{
                //f是一个文件，直接打印
                /**
                 * 只要java结尾的文件
                 * 1、把File对象f，转换字符串对象
                 */
                //String name = f.getName();//abc.java
                //String path = f.getPath();//abc/abc.java
                String s = f.toString();//abc/abc.java

                s = s.toLowerCase();

                //2、调用String类重的方法，endsWith判断字符串是否以.java结尾
                boolean b = s.endsWith(".java");

                //3、如果是，则输出
                if(b){
                    System.out.println(f);
                }

                //等同于上面那段
                if(f.getName().toLowerCase().endsWith(".java")){
                    System.out.println(f);
                }
            }
        }
    }


}
