#### Description

n a N x N `grid` composed of 1 x 1 squares, each 1 x 1 square consists of a `/`, `\`, or blank space. These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a `\` is represented as `"\\"`.)

Return the number of regions.

 

**Example 1:**

```
Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:
```

#### Intuition

[参考leetcode解法](https://leetcode.com/problems/regions-cut-by-slashes/discuss/205674/C%2B%2B-with-picture-DFS-on-upscaled-grid)

#### Solution

```java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        boolean[][] map = new boolean[n*3][n*3];
        for(int i = 0 ; i < n * 3 ; i++)    Arrays.fill(map[i], true);
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i].charAt(j) == '/')    map[3*i+2][3*j] = map[3*i+1][3*j+1] = map[3*i][3*j+2] = false;
                if(grid[i].charAt(j) == '\\')   map[3*i][3*j] = map[3*i+1][3*j+1] = map[3*i+2][3*j+2] = false;
            }
        }
        int count = 0;
        for(int i = 0 ; i < 3 * n ; i++){
            for(int j = 0 ; j < 3 * n ; j++){
                if(map[i][j]){
                    count++;
                    dfs(map, i, j);
                }
            }
        }
        return count;
    }
    
    public void dfs(boolean[][] map, int i, int j){
        if(i < 0 || i >= map.length || j < 0 || j >= map.length || !map[i][j])  return;
        map[i][j] = false;
        dfs(map, i - 1, j);
        dfs(map, i + 1, j);
        dfs(map, i, j + 1);
        dfs(map, i, j - 1);
    }
}
```

