16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2028%20-%20RegEx%2C%20Patterns%2C%20and%20Intro%20to%20Databases.java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution 
{

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        String emailRegEx = ".+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegEx);

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = in.next();
            String email = in.next();
            Matcher matcher = pattern.matcher(email);

            if (matcher.find()) list.add(name);
        }

        Collections.sort(list);

        list.forEach(System.out::println);
       
    }
}
