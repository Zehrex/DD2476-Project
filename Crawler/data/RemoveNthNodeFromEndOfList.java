1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/RemoveNthNodeFromEndOfList.java
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return null;
        }

        ListNode first = head, second = head;

        for(int i = 0; i <= n; i ++) {
            if(first == null) {
                return head.next;
            }
            first = first.next;
        }

        while(first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return head;
    }

}
