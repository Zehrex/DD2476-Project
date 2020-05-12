2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/File/FileFilter.java
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
 * 我们可以使用过滤器来实现
 * 在 File 类中有两个和 listFiles 重载的方法，方法的参数传递的就是过滤器
 *
 * File[] listFiles(FileFilter filter)
 * java.io.FileFilter 接口：用于抽象路径名（File对象）的过滤器
 *      作用：用来过滤文件（File对象）
 *      抽象方法：用来过滤文件的方法
 *              boolean accept(File pathname) 测试指定抽象路径名是否应该包含在某个路径名列表中。
 *              参数：
 *                  File pathname：使用 ListFiles 方法遍历目录，得到的每一个文件对象
 *
 * File[] listFiles(FilenameFilter filter)
 * java.io.FilenameFilter 接口：实现接口的类实例可用于过滤器文件名
 *      作用：用于过滤文件名称
 *      抽象方法：用来过滤文件的方法
 *              boolean accept(File dir, String name) 测试指定文件是否应该包含在某一文件列表中
 *              参数：
 *                  File dir：构造方法中传递的被遍历的目录
 *                  String name：使用 ListFiles 方法遍历目录，获取的每一个文件/文件夹的名称
 *
 * 注意：
 *      两个过滤接口是没有实现类的，需要我们自己写实现类，重写过滤的方法 accept，在方法中自己定义过滤的规则
 */
public class FileFilter {
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

        File[] files = dir.listFiles(new FileFilterImpl());
        for (File f : files){
            //判断是否文件夹
            if(f.isDirectory()){
                //是一个文件夹，调用自己
                getAllFile(f);
            }else{
                System.out.println(f);
            }
        }
    }
}
