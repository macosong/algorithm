#### Description

On a 2D plane, we place stones at some integer coordinate points. Each coordinate point may have at most one stone.

Now, a *move* consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

**Example 1:**

```
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
```

**Example 2:**

```
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
```

**Example 3:**

```
Input: stones = [[0,0]]
Output: 0
```

#### Intuition

根据题意，给定一个矩阵，需要移除同行或同列的点，问最多可以移除多少个点。

首先要计算矩阵中存在的连通图的个数，用island表示，一个island就是根据行号或列号连接起来的点的集合，每个island最终需要剩下一个点。

所以有多少个island，就表示最后剩下多少个点。`n - island`即是需要删除的点的个数，即为所求。

#### Solution

**DFS**

时间复杂度$O(n^2)$

```java
class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];
        int island = 0;
        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                island++;
                dfs(stones, i, stones[i][0], stones[i][1], visited);
            }
        }
        return n - island;
    }
    
    public void dfs(int[][] stones, int i, int x, int y, boolean[] visited){
        if(visited[i])  return;
        visited[i] = true;
        for(int j = 0 ; j < stones.length; j++){
            if(visited[j])  continue;
            if(stones[j][0] == x || stones[j][1] == y){
                dfs(stones, j, stones[j][0], stones[j][1], visited);
            }
        }
    }
}
```

**并查集**

