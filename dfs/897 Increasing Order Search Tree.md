#### Description

Given a binary search tree, rearrange the tree in **in-order** so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

#### Solution

**Strainghtforward**

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
    public TreeNode increasingBST(TreeNode root) {
        if(root == null)    return null;
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        return rebuild(list);
    }
    
    public void inOrder(TreeNode node, List<TreeNode> list){
        if(node == null)    return;
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }
    
    public TreeNode rebuild(List<TreeNode> list){
        for(int i = 0 ; i < list.size() - 1 ; i++){
            list.get(i).right = list.get(i + 1);
            list.get(i).left = null;
        }
        list.get(list.size() - 1).left = null;
        list.get(list.size() - 1).right = null;
        return list.get(0);
    }
}
```

**smart solution from leetcode**

tail meas the next node in inorder, hard to understand

```java
public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

public TreeNode increasingBST(TreeNode root, TreeNode tail) {
    if (root == null) return tail;
    TreeNode res = increasingBST(root.left, root);
    root.left = null;
    root.right = increasingBST(root.right, tail);
    return res;
}
```

