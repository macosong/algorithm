#### Description

In a directed graph, we start at some node and every turn, walk along a directed edge of the graph. If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is *eventually safe* if and only if we must eventually walk to a terminal node. More specifically, there exists a natural number `K` so that for any choice of where to walk, we must have stopped at a terminal node in less than `K` steps.

Which nodes are eventually safe? Return them as an array in sorted order.

The directed graph has `N` nodes with labels `0, 1, ..., N-1`, where `N` is the length of `graph`. The graph is given in the following form: `graph[i]` is a list of labels `j` such that `(i, j)` is a directed edge of the graph.

```
Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.
```

#### Intuition

用color表示图中每个节点的状态，white表示未访问，grey表示不安全，black表示安全。这样可以避免递归过程中的重复遍历。

每访问一个节点，先假设它是不安全的，然后判断它的指向的节点是否安全。

#### Solution

```java
class Solution {
    enum Color{
        White, Grey, Black;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        Color[] color = new Color[n];
        Arrays.fill(color, Color.White);
        
        for(int i = 0 ; i < n ; i++)
            if(!isSafe(graph, i, color)) res.add(i);
        return res;
    }
    
    public boolean isSafe(int[][] graph, int i, Color[] color){
        color[i] = Color.Grey;
        for(int next : graph[i]){
            if(color[next] == Color.Grey)   return true;
            if(color[next] == Color.White && isSafe(graph, next, color))    return true;
        }
        color[i] = Color.Black;
        return false;
    }
}
```

