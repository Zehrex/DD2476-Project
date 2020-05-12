1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/SingleNumber.java
public class SingleNumber {

    public int singleNumber(int[] arr) {
        int xor = 0;

        for(int i = 0; i < arr.length; i ++) {
            xor ^= arr[i];
        }

        return xor;
    }

}
