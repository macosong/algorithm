输入一个链表，输出该链表中倒数第k个结点。

#### Solution

双指针，第二个指针在第一个指针走了k步之后再出发

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null)    return null;
        ListNode p1 = head, p2 = head;
        while(p1 != null){
            p1 = p1.next;
            if(k-- <= 0)    p2 = p2.next;
        }
        return k > 0 ? null : p2;
    }
}
```

