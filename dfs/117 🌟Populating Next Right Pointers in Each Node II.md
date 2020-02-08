#### Description

Given a binary tree

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

 

**Follow up:**

- You may only use constant extra space.
- Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

 

**Example 1:**

<img src="https://assets.leetcode.com/uploads/2019/02/15/117_sample.png" alt="img" style="zoom:50%;" />

```
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
```

#### Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if(root == null)    return null;
        Node cur = root;
        Node start = new Node(0);
        Node p =start;
        while(cur != null){
            if(cur.left != null){
                p.next = cur.left;
                p = p.next;
            }
            if(cur.right != null){
                p.next = cur.right;
                p = p.next;
            }
            cur = cur.next;
            if(cur == null){
                p = start;
                cur = start.next;
                //访问前面的节点时没有start.next也没有影响，关键是访问最后一行
                //访问最后一个节点（比如这里的7）期间，cur=cur.next将cur赋值为null，进入这个if之后还将对cu进行赋为start.next，如果上一步不将其设为null，则最后一行将会被无限循环访问，出现TLE
                start.next = null;
            }
        }
        
        return root;
    }
}
```

