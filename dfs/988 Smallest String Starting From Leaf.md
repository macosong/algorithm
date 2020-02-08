#### Description

Given the `root` of a binary tree, each node has a value from `0` to `25` representing the letters `'a'` to `'z'`: a value of `0` represents `'a'`, a value of `1` represents `'b'`, and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

*(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, `"ab"` is lexicographically smaller than `"aba"`. A leaf of a node is a node that has no children.)*



**Example 1:**

**![img](https://assets.leetcode.com/uploads/2019/01/30/tree1.png)**

```
Input: [0,1,2,3,4,3,4]
Output: "dba"
```

**Example 2:**

**![img](https://assets.leetcode.com/uploads/2019/01/30/tree2.png)**

```
Input: [25,1,3,1,3,0,2]
Output: "adz"
```

**Example 3:**

**![img](https://assets.leetcode.com/uploads/2019/02/01/tree3.png)**

```
Input: [2,2,1,null,1,0,null,0]
Output: "abc"
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
    List<String> col = new ArrayList<>();
    public String smallestFromLeaf(TreeNode root) {
        if(root == null)    return "";
        dfs(root, new StringBuilder());
        Collections.sort(col);
        return col.get(0);
    }
    
    public void dfs(TreeNode node, StringBuilder sb){
        if(node == null)    return;
        int size = sb.length();
        sb.append(String.valueOf((char)(node.val + 'a')));
        if(node.left == null && node.right == null){
            StringBuilder tmp = new StringBuilder(sb);
            col.add(tmp.reverse().toString());
        }
        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.setLength(size);
    }
}
```

