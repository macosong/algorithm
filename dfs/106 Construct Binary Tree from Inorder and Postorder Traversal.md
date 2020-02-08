#### Description

Given inorder and postorder traversal of a tree, construct the binary tree.

**Note:**
You may assume that duplicates do not exist in the tree.

For example, given

```
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
```

Return the following binary tree:

```
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }
    
    public TreeNode build(int[] inOrder, int[] postOrder, int postEnd, int inStart, int inEnd){
        if(inStart > inEnd || postEnd < 0)  return null;
        TreeNode node = new TreeNode(postOrder[postEnd]);
        int index = 0;
        for(int i = inStart ; i <= inEnd ; i++){
            if(inOrder[i] == postOrder[postEnd]){
                index = i;
                break;
            }
        }
        node.left = build(inOrder, postOrder, postEnd - (inEnd - index) - 1, inStart, index - 1);
        node.right = build(inOrder, postOrder, postEnd - 1, index + 1, inEnd);
        return node;
    }
}
```

