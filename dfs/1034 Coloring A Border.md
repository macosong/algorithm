#### Description

Given a 2-dimensional `grid` of integers, each value in the grid represents the color of the grid square at that location.

Two squares belong to the same *connected component* if and only if they have the same color and are next to each other in any of the 4 directions.

The *border* of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).

Given a square at location `(r0, c0)` in the grid and a `color`, color the border of the connected component of that square with the given `color`, and return the final `grid`.

 

**Example 1:**

```
Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
Output: [[3, 3], [3, 2]]
```

**Example 2:**

```
Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
Output: [[1, 3, 3], [2, 3, 3]]
```

**Example 3:**

```
Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
```

#### Solution

```java
class Solution {
    int tc, m, n;
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        tc = grid[r0][c0];
        m = grid.length;
        n = grid[0].length;
        List<int[]> boards = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        search(grid, dirs, r0, c0, boards, visited);
        for(int[] board : boards)   grid[board[0]][board[1]] = color;
        return grid;
    }
    
    public void search(int[][] grid, int[][] dirs, int i, int j, List<int[]> boards, boolean[][] visited){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != tc || visited[i][j])  return;
        visited[i][j] = true;
        //judge if the node is border
        if(isBorder(grid, i, j))    boards.add(new int[]{i, j});
        for(int[] dir : dirs)   search(grid, dirs, i + dir[0], j + dir[1], boards, visited);
    }
    
    public boolean isBorder(int[][] grid, int i, int j){
        if(i == 0 || j == 0 || i == m - 1 || j == n - 1)    return true;
        if(grid[i + 1][j] != tc || grid[i - 1][j] != tc || grid[i][j + 1] != tc || grid[i][j - 1] != tc)    return true;
        return false;
    }
}
```

