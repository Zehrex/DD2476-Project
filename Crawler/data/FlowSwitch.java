2
https://raw.githubusercontent.com/IzzyPrime/JavaWorkspace/master/JavaPractice/part1/src/com/flow/javapractice/FlowSwitch.java
package com.flow.javapractice;

import java.util.Scanner;
import java.util.Random;

/**
 * switch实现石头/剪子/布并判断胜负
 */
public class FlowSwitch {

	public static void main(String[] args) {
		System.out.println("please choice:");
		System.out.println(" 1: Rock");//石頭
		System.out.println(" 2: Scis1sors");//剪刀
		System.out.println(" 3: Paper");//布
		// 用户输入:
		Scanner scanner = new Scanner (System.in);
		int choice = scanner.nextInt();
		// 计算机随机数 1, 2, 3:
		//int random = 1 + (int) Math.random() * 3;
		//解决了radom的值一直不变的问题
		Random rd = new Random();
		int random = 1 + rd.nextInt(3);
		switch (choice) {
		// TODO:
		case 1://石头
			if(random == 1) {//石头
				System.out.println("Rock");
				System.out.println("!");
			}else if(random == 2) {//剪刀
				System.out.println("Scissors");
				System.out.println("win!");
			}else {//布
				System.out.println("Paper");
				System.out.println("lose!");
			}
			break;
		case 2://剪刀
			if(random == 1) {//石头
				System.out.println("Rock");
				System.out.println("lose!");
			}else if(random == 2) {//剪刀
				System.out.println("Scissors");
				System.out.println("!");
			}else {//布
				System.out.println("Paper");
				System.out.println("win!");
			}
			break;
		case 3://布
			if(random == 1) {//石头
				System.out.println("Rock");
				System.out.println("win!");
			}else if(random == 2) {//剪刀
				System.out.println("lose!");
				System.out.println("Scissors");
			}else {//布
				System.out.println("Paper");
				System.out.println("!");
			break;
		}
		
		/*java12_update
		String fruit = "apple";
        int opt = switch (fruit) {
            case "apple" -> 1;
            case "pear", "mango" -> 2;
            default -> 0;
        }; // 注意赋值语句要以;结束
        System.out.println("opt = " + opt);
        */
		}
	}

}
