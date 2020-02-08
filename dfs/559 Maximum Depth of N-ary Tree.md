#### Description

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

*Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).*

#### Solution

**DFS**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    int maxDepth = 0;
    public int maxDepth(Node root) {
        dfs(root, 1);
        return maxDepth;
    }
    
    public void dfs(Node node, int depth){
        if(node == null)    return;
        maxDepth = Math.max(maxDepth, depth);
        for(Node child : node.children) dfs(child, depth + 1);
    }
}
```

**BFS with a queue**

```java
class Solution {
    
    public int maxDepth(Node root) {
        if(root == null)    return 0;
        Queue<Node> queue = new LinkedList<>();
        int depth = 0;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                Node cur = queue.poll();
                for(Node node : cur.children)   queue.offer(node);
            }
            depth++;
        }
        return depth;
    }
}
```

