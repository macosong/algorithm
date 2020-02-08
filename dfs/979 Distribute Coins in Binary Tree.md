#### Description

Given the `root` of a binary tree with `N` nodes, each `node` in the tree has `node.val` coins, and there are `N` coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another. (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.



#### Intuition

加入一个dfs方法，返回当前节点应该给它的父节点多少个coin。



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
    int res = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }
    
    public int dfs(TreeNode node){
        if(node == null)    return 0;
        int leftCount = dfs(node.left);
        int rightCount = dfs(node.right);
        res += Math.abs(leftCount) + Math.abs(rightCount);
        return node.val + leftCount + rightCount - 1;
    }
}
```

