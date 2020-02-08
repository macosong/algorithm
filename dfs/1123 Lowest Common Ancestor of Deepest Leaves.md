#### Description

Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:

- The node of a binary tree is a *leaf* if and only if it has no children
- The *depth* of the root of the tree is 0, and if the depth of a node is `d`, the depth of each of its children is `d+1`.
- The *lowest common ancestor* of a set `S` of nodes is the node `A` with the largest depth such that every node in S is in the subtree with root `A`.



#### Solution

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
    int maxDepth = 0;
    TreeNode res;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    
    public int dfs(TreeNode node, int depth){
        maxDepth = Math.max(depth, maxDepth);
        if(node == null)    return depth;
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);
        if(left == maxDepth && right == maxDepth)   res = node;
        return Math.max(left, right);
        
    }
}
```

