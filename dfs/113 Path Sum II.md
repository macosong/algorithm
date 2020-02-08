#### Description

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

**Note:** A leaf is a node with no children.

**Example:**

Given the below binary tree and `sum = 22`,

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
```

Return:

```
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, 0, new ArrayList<>(), sum);
        return res;
    }
    
    public void dfs(TreeNode node, int tmp, List<Integer> list, int sum){
        if(node == null) return;
        tmp += node.val;
        list.add(node.val);
        if(node.left == null && node.right == null){
            if(tmp == sum)  res.add(new ArrayList<>(list));
        }
        dfs(node.left, tmp, list, sum);
        dfs(node.right, tmp, list, sum);
        list.remove(list.size() - 1);
    }
}
```

