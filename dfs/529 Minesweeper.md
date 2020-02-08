#### Description

Let's play the minesweeper game ([Wikipedia](https://en.wikipedia.org/wiki/Minesweeper_(video_game)), [online game](http://minesweeperonline.com/))!

You are given a 2D char matrix representing the game board. **'M'** represents an **unrevealed** mine, **'E'** represents an **unrevealed** empty square, **'B'** represents a **revealed** blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, **digit** ('1' to '8') represents how many mines are adjacent to this **revealed** square, and finally **'X'** represents a **revealed** mine.

Now given the next click position (row and column indices) among all the **unrevealed** squares ('M' or 'E'), return the board after revealing this position according to the following rules:

1. If a mine ('M') is revealed, then the game is over - change it to **'X'**.
2. If an empty square ('E') with **no adjacent mines** is revealed, then change it to revealed blank ('B') and all of its adjacent **unrevealed** squares should be revealed recursively.
3. If an empty square ('E') with **at least one adjacent mine** is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
4. Return the board when no more squares will be revealed.



#### Solution

**DFS**

```java
class Solution {
    int[] xo = {-1,-1,-1,0,0,1,1,1};
    int[] yo = {-1,0,1,-1,1,-1,0,1};
    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click[0], click[1]);
        return board;
    }
    
    public void dfs(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || (board[x][y] != 'E' && board[x][y] != 'M')) return;
        char cur = board[x][y];
        if(cur == 'M'){
            board[x][y] = 'X';
            return;
        }
        int num = calMine(board, x, y);
        if(num != 0)    board[x][y] = (num + "").toCharArray()[0];
        else{
            board[x][y] = 'B';
            for(int i = 0 ; i < 8 ; i++){
                int nx = x + xo[i];
                int ny = y + yo[i];
                dfs(board, nx, ny);
            }
        }
    }
    
    public int calMine(char[][] board, int x, int y){
        int num = 0;
        for(int i = 0 ; i < 8 ; i++){
            int nx = x + xo[i];
            int ny = y + yo[i];
            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) continue;
            if(board[nx][ny] == 'M' || board[nx][ny] == 'X')    num++;
        }
        return num;
    }
  
}
```

