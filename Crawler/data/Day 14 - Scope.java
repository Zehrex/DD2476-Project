16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2014%20-%20Scope.java
class Difference {
    private int[] elements;
    public int maximumDifference;

    Difference(int[] elements) {
        this.elements = elements;
    }

    public void computeDifference() {
        int max = 0;

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {
                int abs = Math.abs(elements[i] - elements[j]);
                if (abs > max) max = abs;
            }
        }

        maximumDifference = max;
    }
}