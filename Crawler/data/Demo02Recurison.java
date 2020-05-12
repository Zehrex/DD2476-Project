2
https://raw.githubusercontent.com/liuminchao123/JavaWeb_Learning/master/02.%20java/Java/%E9%BB%91%E9%A9%AC%E6%95%99%E7%A8%8B/19.%E3%80%90File%E7%B1%BB%E3%80%81%E9%80%92%E5%BD%92%E3%80%91-%E7%AC%94%E8%AE%B0/code/08_FileAndRecursion/src/com/itheima/demo02/Recursion/Demo02Recurison.java
package com.itheima.demo02.Recursion;
/*
    练习:
        使用递归计算1-n之间的和
 */
public class Demo02Recurison {
    public static void main(String[] args) {
        int s = sum(3);
        System.out.println(s);
    }

    /*
        定义一个方法,使用递归计算1-n之间的和
        1+2+3+...+n
        n+(n-1)+(n-2)+...+1
        已知:
            最大值:n
            最小值:1
        使用递归必须明确:
            1.递归的结束条件
                获取到1的时候结束
            2.递归的目的
                获取下一个被加的数字(n-1)
     */
    public static int sum(int n){
        //获取到1的时候结束
        if(n==1){
            return 1;
        }

        //获取下一个被加的数字(n-1)
        return n + sum(n-1);
    }
}
