1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/SubtreeOfAnotherTree.java
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null || t == null) {
            return false;
        }

        if(s.val == t.val && isEqual(s, t)) {
            return true;
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isEqual(TreeNode s, TreeNode t) {
        if(s == null && t == null) {
            return true;
        }

        if(s == null || t == null) {
            return false;
        }

        return s.val == t.val && isEqual(s.left, t.left) && isEqual(s.right, t.right);
    }

}
