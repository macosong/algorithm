#### Description

We are given a binary tree (with root node `root`), a `target` node, and an integer value `K`.

Return a list of the values of all nodes that have a distance `K` from the `target` node. The answer can be returned in any order.

 



**Example 1:**

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
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
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if(root == null)    return res;
        buildMap(root, null);
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.offer(target);
        visited.add(target);
        while(!q.isEmpty()){
            int size = q.size();
            if(K == 0){
                for(int i = 0 ; i < size ; i++) res.add(q.poll().val);
                break;
            }
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = q.poll();
                List<TreeNode> next = map.get(cur);
                for(int j = 0 ; j < next.size() ; j++){
                    TreeNode tmp = next.get(j);
                    if(!visited.contains(tmp)){
                        q.offer(tmp);
                        visited.add(tmp);
                    }
                    
                }
            }
            K--;
        }
        return res;
        
    }
    
    public void buildMap(TreeNode node, TreeNode parent){
        if(node == null)    return;
        if(!map.containsKey(node)){
            map.put(node, new ArrayList<TreeNode>());
            if(parent != null){
                map.get(node).add(parent);
                map.get(parent).add(node);
            }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }
}
```

