#### Description

```java
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    public TreeNode dfs(int[] preOrder, int[] inOrder, int preStart, int inStart, int inEnd){
        if(preStart >= preOrder.length || inEnd < inStart)  return null;
        TreeNode node = new TreeNode(preOrder[preStart]);
        int index = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inOrder[i] == preOrder[preStart]){
                index = i;
                break;
            }
        }
        node.left = dfs(preOrder,inOrder, preStart + 1, inStart, index - 1);
        node.right = dfs(preOrder, inOrder, preStart + index - inStart + 1, index + 1, inEnd);
        return node;
    }
}
```

