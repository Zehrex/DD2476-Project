1
https://raw.githubusercontent.com/kunal-kushwaha/Progate-Java-101/master/src/com/kunal/DataStructures.java
package com.kunal;

import java.util.Arrays;

public class DataStructures {
    public static void main(String[] args) {
        // arrays: collection of homogenous datatypes
        int[] first = {4,3,2,5,6};
        int[] second = first;

//        System.out.println(Arrays.toString(second));

        second[0] = 300;
        System.out.println(Arrays.toString(first));

        int[] third = {8,9,20};
        first = third;
        second = third;

    }
}
