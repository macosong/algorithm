#### Description

In a given 2D binary array `A`, there are two islands. (An island is a 4-directionally connected group of `1`s not connected to any other 1s.)

Now, we may change `0`s to `1`s so as to connect the two islands together to form 1 island.

Return the smallest number of `0`s that must be flipped. (It is guaranteed that the answer is at least 1.)

**Example 1:**

```
Input: [[0,1],[1,0]]
Output: 1
```

**Example 2:**

```
Input: [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
```

**Example 3:**

```
Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
```

#### Intuition

1. 先通过dfs找到第一个island，用一个队列保存这个island所有点的坐标
2. 从第一个island开始BFS搜索第二个island，可以理解为由外向内一圈一圈地搜索，直到找到第二个island的某个点，返回此时的圈数即最近距离

#### Solution

```java
class Solution {
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> q = new LinkedList<>();
        boolean found = false;
        for(int i = 0 ; i < m ; i++){
            if(found)   break;
            for(int j = 0 ; j < n ; j++){
                if(A[i][j] == 1){
                    dfs(A, visited, dirs, q, i, j);
                    found = true;
                    break;
                }
            }
        }
        
        
        //bfs
        int step = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int[] cur = q.poll();
                for(int[] dir : dirs){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                        if(A[x][y] == 1)    return step;
                        visited[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    public void dfs(int[][] A, boolean[][] visited, int[][] dirs, Queue<int[]> q, int i, int j){
        if(i < 0 || i >= A.length || j < 0 || j >= A[0].length || visited[i][j] || A[i][j] == 0) return;
        visited[i][j] = true;
        q.offer(new int[]{i, j});
        for(int[] dir : dirs){
            dfs(A, visited, dirs, q, i + dir[0], j + dir[1]);
        }
    }
}
```

