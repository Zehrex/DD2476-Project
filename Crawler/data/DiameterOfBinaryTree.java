1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/DiameterOfBinaryTree.java
public class DiameterOfBinaryTree {

    private int maximum = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        depthFirst(root);
        return maximum - 1;
    }

    public int depthFirst(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = depthFirst(node.left);
        int right = depthFirst(node.right);

        maximum = Math.max(maximum, left + right + 1);
        return Math.max(left, right) + 1;
    }

}
