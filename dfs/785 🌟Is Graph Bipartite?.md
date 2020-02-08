#### Description

Given an undirected `graph`, return `true` if and only if it is bipartite.

Recall that a graph is *bipartite* if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: `graph[i]` is a list of indexes `j` for which the edge between nodes `i` and `j` exists. Each node is an integer between `0` and `graph.length - 1`. There are no self edges or parallel edges: `graph[i]` does not contain `i`, and it doesn't contain any element twice.

```
Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
```

#### Intuition

问题可以抽象为：给所有的点涂色，其中一条边的两个顶点只能涂相反的颜色，看最终这个图所有点能否被正确涂色。

假设一个点可以有三种状态：0-未涂色，1-白色，-1-黑色。

遍历所有的点：

1）如果当前点未涂色，那么给他涂一种颜色，然后尝试给它所有点邻接顶点涂相反的颜色（DFS）

2）如果当前点已有颜色，那么看它当前点颜色和接下来应该涂点颜色是否相同

#### Solution

```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n =graph.length;
        int[] colors = new int[n];
        for(int i = 0 ; i < n ; i++){
            if(colors[i] == 0 && !dfs(graph, colors, i, 1))   return false;
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int[] colors, int i, int color){
        if(colors[i] != 0)   return colors[i] == color;
        colors[i] = color;
        for(int j : graph[i]){
            if(!dfs(graph, colors, j, -color))   return false;
        }
        return true;
    }
}
```

