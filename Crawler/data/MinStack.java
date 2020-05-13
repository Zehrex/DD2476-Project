1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/MinStack.java
public class MinStack {

    Node head;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x);
            head.min = x;
        } else {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
            head.min = Math.min(x, newNode.next.min);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

}
