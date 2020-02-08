#### Description

Given a binary tree, find the leftmost value in the last row of the tree.

**Example 1:**

```
Input:

    2
   / \
  1   3

Output:
1
```



**Example 2:**

```
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
```



#### Solution

**DFS**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int depth = 1;
    int res = 0;
    public int findBottomLeftValue(TreeNode root) {
        if(root == null)    return 0;
        res = root.val;
        find(root, 1);
        return res;
    }
    
    public void find(TreeNode node, int d){
        if(node != null){
            find(node.left, d + 1);
            if(d > depth){
                depth = d;
                res = node.val;
            }
            find(node.right, d + 1);
        }
    }
}
```



**BFS**

```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = queue.poll();
                if(i == 0)  res = cur.val;
                if(cur.left != null)    queue.offer(cur.left);
                if(cur.right != null)   queue.offer(cur.right);
            }
        }
        return res;
    }
}
```

