2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowFor.java
package com.flow.javapractice;

/**
 * for练习
 */
public class FlowFor {

	public static void main(String[] args) {
		//1
		int[] ns = { 1, 4, 9, 16, 25 };
        for (int i = 4; i >= 0; i--) {
            System.out.println(ns[i]);
        }

        //2
        int sum = 0;
        for (int i : ns) {
            // TODO
        	sum += i;
        }
        System.out.println(sum); // 55
        
        //3
        double pi = 0;
        int n = 1;
        for (double i = 1; i < 1000000; i += 2) {//i若是int 结果输出为4.0 因为1/i=0,也可再后面做强制类型转换
            // TODO       	
        	if(n == 1) {
        		pi += 1/i;
        		n = 0;
        	}else {
        		pi -= 1/i;
        		n = 1;
        	}
        }
        System.out.println(pi*4);
	}

}
