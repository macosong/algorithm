#### Description

Consider all the leaves of a binary tree. From left to right order, the values of those leaves form a *leaf value sequence.*

Two binary trees are considered *leaf-similar* if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

#### Intuition

使用两个stack分别存储两棵树的节点，每次找到下一个叶子节点，比较它们的值是否相等。

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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> a = new Stack<>();
        Stack<TreeNode> b = new Stack<>();
        a.push(root1);
        b.push(root2);
        while(!a.isEmpty() && !b.isEmpty()) if(dfs(a) != dfs(b))    return false;
        return a.isEmpty() && b.isEmpty();
    }
    
    public int dfs(Stack<TreeNode> s){
        while(true){
            TreeNode cur = s.pop();
            if(cur.left != null)    s.push(cur.left);
            if(cur.right != null)   s.push(cur.right);
            if(cur.left == null && cur.right == null)   return cur.val;
        }
    }
}
```

