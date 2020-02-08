#### Description

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

**Example 1:**

```
Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
```

**Example 2:**

```
Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
```

#### Solution

**BFS**

```java
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == 0)   q.offer(new int[]{i,j});
                else    matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int[] dir : dirs){
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                //(r,c)是0或者是已经访问的1，则跳过
                if(r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] <= matrix[cur[0]][cur[1]] + 1)    continue;
                //否则就是未访问过的1
                q.offer(new int[]{r,c});
                matrix[r][c] = matrix[cur[0]][cur[1]] + 1;
            }
        }
        return matrix;
        
    }
}
```

