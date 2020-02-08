#### Description

Given a binary tree with `N` nodes, each node has a different value from `{1, ..., N}`.

A node in this binary tree can be *flipped* by swapping the left child and the right child of that node.

Consider the sequence of `N` values reported by a preorder traversal starting from the root. Call such a sequence of `N` values the *voyage* of the tree.

(Recall that a *preorder traversal* of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the **least number** of nodes in the tree so that the voyage of the tree matches the `voyage` we are given.

If we can do so, then return a list of the values of all nodes flipped. You may return the answer in any order.

If we cannot do so, then return the list `[-1]`.

 

**Example 1:**

**![img](https://assets.leetcode.com/uploads/2019/01/02/1219-01.png)**

```
Input: root = [1,2], voyage = [2,1]
Output: [-1]
```

**Example 2:**

**![img](https://assets.leetcode.com/uploads/2019/01/02/1219-02.png)**

```
Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
```

**Example 3:**

**![img](https://assets.leetcode.com/uploads/2019/01/02/1219-02.png)**

```
Input: root = [1,2,3], voyage = [1,2,3]
Output: []
```

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
    List<Integer> res = new ArrayList<>();
    int i = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return dfs(root, voyage) ? res : Arrays.asList(-1);
    }
    
    public boolean dfs(TreeNode node, int[] v){
        if(node == null)    return true;
        if(node.val != v[i++])  return false;
        if(node.left != null && node.left.val != v[i]){
            res.add(node.val);
            return dfs(node.right, v) && dfs(node.left, v);
        }
        return dfs(node.left, v) && dfs(node.right, v);
    }
        
}
```

