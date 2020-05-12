1
https://raw.githubusercontent.com/kunal-kushwaha/Progate-Java-101/master/src/com/kunal/LoopsAndConditionals.java
package com.kunal;

public class LoopsAndConditionals {
    public static void main(String[] args) {
        // print first 5 natural numbers
//        for (int i = 1; i <= 5; i++) {
//            System.out.println(i);
//        }

        // while loop : when you dont know how many times loop will run
//        int i = 1;
//        while(i <= 5){
//            System.out.println(i);
//            i++; // i = i + 1;
//        }

        // arrays
//        int[] arr = {4,3,5,7,8};
//        for(int j=0; j < arr.length; j++){
//            System.out.print(arr[j] + " ");
//        }

        // <, >, ==
        int a = 100;
        if (a > 10){
            System.out.println("a is more than 10");
        } else if(a < 10){
            System.out.println("a is less than 10");
        } else{
            System.out.println("a is equal to 10");
        }
    }
}
