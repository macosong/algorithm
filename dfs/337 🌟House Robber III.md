#### Description

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

**Example 1:**

```
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
```

#### Intuition

对于一个节点，它只存在两种情况，即偷或者不偷，如果偷，则会跳过它的孩子节点；如果不偷，则偷它的孩子节点。所以使用一个数组res[2]记录当前节点两种情况的最优解，res[0]表示偷当前节点，res[1]表示不偷当前节点。

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
    public int rob(TreeNode root) {
        int res[] = dfs(root);
        return Math.max(res[0], res[1]);
    }
    
    public int[] dfs(TreeNode node){
        if(node == null)    return new int[2];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = node.val + left[0] + right[0];
        return res;
    }
}
```



