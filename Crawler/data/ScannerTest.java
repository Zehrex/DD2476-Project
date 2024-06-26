2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab1/src/test/java/com/demkom58/lab1/test/ScannerTest.java
package com.demkom58.lab1.test;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("Введіть дані за таким шаблоном:\n");
        builder.append("Прізвище та ім'я/кільк.незадов./середній бал/\n");
        builder.append("Приклад: \n\tКіт Базіліо/2/2,4/");

        System.out.println(builder);

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("/");

        String name = scanner.next();
        int n = scanner.nextInt();
        float b = scanner.nextFloat();

        scanner.close();

        System.out.println(name + ": двійок " + n + "; ср.бал=" + b);
    }
}
