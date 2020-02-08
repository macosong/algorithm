#### Description

Given a set of `N` people (numbered `1, 2, ..., N`), we would like to split everyone into two groups of **any** size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if `dislikes[i] = [a, b]`, it means it is not allowed to put the people numbered `a` and `b` into the same group.

Return `true` if and only if it is possible to split everyone into two groups in this way.

 

**Example 1:**

```
Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
```

**Example 2:**

```
Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
```

**Example 3:**

```
Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
```

#### Solution

```java
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[][] graph = new int[N][N];
        for(int[] dislike : dislikes){
            graph[dislike[0] - 1][dislike[1] - 1] = 1;
            graph[dislike[1] - 1][dislike[0] - 1] = 1;
        }
        int[] colors = new int[N];
        for(int i = 0 ; i < N ; i++){
            if(colors[i] == 0 && !dfs(graph, i, colors, 1, N)) return false;
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int index, int[] colors, int color, int n){
        if(colors[index] != 0)  return colors[index] == color;
        colors[index] = color;
        int[] line = graph[index];
        for(int i = 0 ; i < n ; i++)
            if(line[i] == 1 && !dfs(graph, i, colors, -color, n))   return false;
        return true;
    }
}
```

