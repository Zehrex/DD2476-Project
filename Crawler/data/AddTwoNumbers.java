1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/AddTwoNumbers.java
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode dummy = ans;

        int carry = 0, sum = 0;

        while(l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;

            if(sum >= 10) {
                carry = sum / 10;
                sum -= 10;
            } else {
                carry = 0;
            }

            dummy.next = new ListNode(sum);
            dummy = dummy.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            sum = l1.val + carry;

            if(sum >= 10) {
                carry = sum / 10;
                sum -= 10;
            } else {
                carry = 0;
            }

            dummy.next = new ListNode(sum);
            dummy = dummy.next;

            l1 = l1.next;
        }

        while(l2 != null) {
            sum = l2.val + carry;

            if(sum >= 10) {
                carry = sum / 10;
                sum -= 10;
            } else {
                carry = 0;
            }

            dummy.next = new ListNode(sum);
            dummy = dummy.next;

            l2 = l2.next;
        }

        if(carry > 0) {
            dummy.next = new ListNode(carry);
        }

        return ans.next;
    }

}
