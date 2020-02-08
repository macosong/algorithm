#### Description

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its minimum depth = 2.

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
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        if(root.left == null)   return minDepth(root.right) + 1;
        if(root.right == null)  return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
```

**BFS**

这道题其实更适合用`BFS`来做

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = q.poll();
                if(cur.left == null && cur.right == null)   return depth;
                if(cur.left != null)    q.offer(cur.left);
                if(cur.right != null)   q.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }
}
```

