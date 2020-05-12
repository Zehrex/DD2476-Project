1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/BinaryTreeLevelOrderTraversal.java
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) {
            return ans;
        }
        ans.add(new LinkedList<>());
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();
        queue.offer(root);
        depths.offer(0);
        int depth = 0;

        while(!queue.isEmpty()) {
            TreeNode tree = queue.poll();
            int newDepth = depths.poll();

            if(newDepth != depth) {
                depth ++;
                ans.add(new LinkedList<>());
            }

            ans.get(ans.size() - 1).add(tree.val);

            if(tree.left != null) {
                queue.offer(tree.left);
                depths.offer(depth + 1);
            }

            if(tree.right != null) {
                queue.offer(tree.right);
                depths.offer(depth + 1);
            }
        }

        return ans;
    }

}
