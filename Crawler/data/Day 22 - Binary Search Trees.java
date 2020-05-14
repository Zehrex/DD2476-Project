16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2022%20-%20Binary%20Search%20Trees.java
public static int getHeight(Node root) 
{

    return root == null ? -1 : 1 + Math.max(getHeight(root.left), getHeight(root.right));
}