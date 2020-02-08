#### Description

Given a 2D board containing `'X'` and `'O'` (**the letter O**), capture all regions surrounded by `'X'`.

A region is captured by flipping all `'O'`s into `'X'`s in that surrounded region.

**Example:**

```
X X X X
X O O X
X X O X
X O X X
```

After running your function, the board should be:

```
X X X X
X X X X
X X X X
X O X X
```

**Explanation:**

Surrounded regions shouldn’t be on the border, which means that any `'O'` on the border of the board are not flipped to `'X'`. Any `'O'` that is not on the border and it is not connected to an `'O'` on the border will be flipped to `'X'`. Two cells are connected if they are adjacent cells connected horizontally or vertically.



#### Solution

```java
class Solution {
    int m, n;
    public void solve(char[][] board) {
        if(board == null)   return;
        if(board.length == 0)    return;
        m = board.length;
        n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        //find all invalid ndoes
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'O' && (i == 0 || j == 0 || i == m - 1 || j == n - 1) && !visited[i][j])
                    dfs(board, i, j, visited);
            }
        }
        //flip false 'O' node
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'O' && !visited[i][j])    board[i][j] = 'X';
            }
        }
    }
    
    public void dfs(char[][] board, int i, int j, boolean[][] visited){
        if(i < 0 || i >=m || j < 0 || j >= n || board[i][j] != 'O' || visited[i][j])    return;
        visited[i][j] = true;
        dfs(board, i + 1, j, visited);
        dfs(board, i - 1, j, visited);
        dfs(board, i, j + 1, visited);
        dfs(board, i, j - 1, visited);
    }
}
```

