1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/ReverseInteger.java
public class ReverseInteger {

    public int reverse(int y) {
        boolean isNegative = false;
        long x = y;

        if (x < 0) {
            isNegative = true;
            x = -x;
        }

        long reverse = 0;

        while (x > 0) {
            reverse *= 10;
            reverse += x % 10;
            x /= 10;
        }

        if (reverse >= Integer.MAX_VALUE) {
            return 0;
        }

        else {
            return (int) (isNegative ? -reverse : reverse);
        }
    }

}
