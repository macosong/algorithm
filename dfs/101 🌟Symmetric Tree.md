#### Description

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree `[1,2,2,3,4,4,3]` is symmetric:

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

 

But the following `[1,2,2,null,3,null,3]` is not:

```
    1
   / \
  2   2
   \   \
   3    3
```

#### Solution

**recursively**

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
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        return dfs(root.left, root.right);
    }
    
    public boolean dfs(TreeNode left, TreeNode right){
        if(left == null && right == null)   return true;
        if(left == null || right == null)   return false;
        if(left.val != right.val)   return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
```

**iteratively**

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while(!stack.isEmpty()){
            TreeNode t1 = stack.pop();
            TreeNode t2 = stack.pop();
            if(t1 == null && t2 == null && stack.isEmpty()) break;
            if(t1 == null && t2 == null && !stack.isEmpty()) continue;
            if(t1 == null || t2 == null)    return false;
            if(t1.val != t2.val)    return false;
            stack.push(t1.left);
            stack.push(t2.right);
            stack.push(t1.right);
            stack.push(t2.left);
        }
        return true;
    }
}
```

