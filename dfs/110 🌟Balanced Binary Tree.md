#### Description

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

> a binary tree in which the left and right subtrees of *every* node differ in height by no more than 1.

 #### Solution

**top-down**解法，time complexity is O(N ^ 2)

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
    public boolean isBalanced(TreeNode root) {
        if(root == null)    return true;
        if(root.left == null && root.right == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }
    
    public int depth(TreeNode node){
        if(node == null)    return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }
}
```

**botom-up**解法，即dfs，time complexity is O(N)

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }
    
    public int dfs(TreeNode node){
        if(node == null)    return 0;
        int leftDepth = dfs(node.left);
        if(leftDepth == -1) return -1;
        int rightDepth = dfs(node.right);
        if(rightDepth == -1)    return -1;
        if(Math.abs(leftDepth - rightDepth) > 1)    return -1;
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
```

