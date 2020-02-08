#### Description

There is an **m** by **n** grid with a ball. Given the start coordinate **(i,j)** of the ball, you can move the ball to **adjacent** cell or cross the grid boundary in four directions (up, down, left, right). However, you can **at most** move **N** times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

 

**Example 1:**

<img src="https://assets.leetcode.com/uploads/2018/10/13/out_of_boundary_paths_1.png" style="zoom:48%;" />

```
Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:
```

#### Solution

```java
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        if(N < 0)   return 0;
        int MOD = 1000000007;
        int[][] count = new int[m][n];
        count[i][j] = 1;
        int res = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        for(int step = 0 ; step < N ; step++){
            int[][] tmp = new int[m][n];
            for(int r = 0 ; r < m ; r++){
                for(int c = 0 ; c < n ;  c++){
                    for(int[] dir : dirs){
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if(nr < 0 || nr >= m || nc < 0 || nc >= n){
                            res = (res + count[r][c]) % MOD;
                        }else
                            tmp[nr][nc] = (tmp[nr][nc] + count[r][c]) % MOD;
                    }
                }
            }
            count = tmp;
        }
        return res;
    }
}
```

