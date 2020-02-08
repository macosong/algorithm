#### Descripiton

You need to find the largest value in each row of a binary tree.

**Example:**

```
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
```

#### Solution

**DFS**

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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int h = 1;
        dfs(root, res, h);
        return res;
    }
    
    public void dfs(TreeNode node, List<Integer> list, int h){
        if(node == null)    return;
        if(list.size() >= h){
            if(node.val > list.get(h - 1)){
                list.set(h - 1, node.val);
            }
        }else   list.add(node.val);
        dfs(node.left, list, h + 1);
        dfs(node.right, list, h + 1);
    }
    
}
```

**BFS**

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)    return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            int tmp = Integer.MIN_VALUE;
            for(int i = 0 ; i < size ; i++){
                TreeNode node = q.poll();
                tmp = Math.max(tmp, node.val);
                if(node.left != null)   q.offer(node.left);
                if(node.right != null)  q.offer(node.right);
            }
            res.add(tmp);   
        }
        return res;
    }
}
```

