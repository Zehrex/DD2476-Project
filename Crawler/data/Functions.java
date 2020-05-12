1
https://raw.githubusercontent.com/kunal-kushwaha/Progate-Java-101/master/src/com/kunal/Functions.java
package com.kunal;

public class Functions {
    public static void main(String[] args) {
//        greeting();
        int a = 10;
        int b = 20;
        int ans = sum(a, b);
        String name = "Kunal";
        System.out.println("The sum is " + ans);
    }
    static int sum(int num1, int num2){
        return num1 + num2;
    }
    static void greeting() {
        System.out.println("Hello World");
    }
}
