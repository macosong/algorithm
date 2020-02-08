#### Description

Given the `root` of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in `to_delete`, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

 

**Example 1:**

**![img](https://assets.leetcode.com/uploads/2019/07/01/screen-shot-2019-07-01-at-53836-pm.png)**

```
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
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
    List<TreeNode> res = new ArrayList<>();
    Set<Integer> delete = new HashSet<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for(int i = 0 ; i < to_delete.length ; i++){
            delete.add(to_delete[i]);
        }
        preAndDel(root, true);
        return res;
        
    }
    
    public TreeNode preAndDel(TreeNode root, boolean isRoot){
        if(root != null){
            boolean deleted = delete.contains(root.val);
            if(!deleted && isRoot)  res.add(root);
            root.left = preAndDel(root.left, deleted);
            root.right = preAndDel(root.right, deleted);
            return deleted ? null : root;
        }
        return null;
    }
}
```

