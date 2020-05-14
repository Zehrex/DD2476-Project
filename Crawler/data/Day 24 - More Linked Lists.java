16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2024%20-%20More%20Linked%20Lists.java
 public static Node removeDuplicates(Node head) 
    {
      Node curr = head;
        while ((curr != null) && (curr.next != null)) 
        {
            while ((curr.next != null) && (curr.data == curr.next.data)) 
            {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }