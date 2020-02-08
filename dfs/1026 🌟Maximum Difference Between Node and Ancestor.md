#### Description

Given the `root` of a binary tree, find the maximum value `V` for which there exists **different** nodes `A` and `B` where `V = |A.val - B.val|` and `A` is an ancestor of `B`.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

**Example 1:**

![img](https://assets.leetcode.com/uploads/2019/09/09/2whqcep.jpg)

```
Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
```



#### Solution

**my foolish solution**

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
    int maxDiff = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if(root == null)    return 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        dfs(root, list);
        return maxDiff;
    }
    
    public void dfs(TreeNode node, List<TreeNode> list){
        if(node == null)    return;
        for(TreeNode tn: list)   maxDiff = Math.max(maxDiff, Math.abs(node.val - tn.val));
        list.add(node);
        dfs(node.left, new ArrayList<>(list));
        dfs(node.right, new ArrayList<>(list));
    }
}
```

方法一虽然也能AC但是时间、空间复杂度都太高。

可优化的点：

1. `空间优化：`不需要用list保存每条路径上的所有点，因为计算最大值实际上只与最大值和最小值有关，这种方法使用了大量的ArrayList，在时间和空间上都是效率很低的。
2. `时间优化:`每到达一个点，都会计算一次max value，这是没有必要的，不需要中间值的产生。

**perfect solution**

```java
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    
    public int dfs(TreeNode node, int max, int min){
        if(node == null)    return max - min;
        max = Math.max(node.val, max);
        min = Math.min(node.val, min);
        return Math.max(dfs(node.left, max, min), dfs(node.right, max, min));
    }
}
```

