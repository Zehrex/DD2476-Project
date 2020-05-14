16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2023%20-%20BST%20Level-Order%20Traversal.java
public static void levelOrder(Node root) 
{
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) 
    {
        Node curr = queue.remove();
        System.out.print(curr.data + " ");

        if (curr.left != null) queue.add(curr.left);
        if (curr.right != null) queue.add(curr.right);
    }
}