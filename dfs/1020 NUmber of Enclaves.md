#### Description

Given a 2D array `A`, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we **cannot** walk off the boundary of the grid in any number of moves.

 

**Example 1:**

```
Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: 
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
```

**Example 2:**

```
Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: 
All 1s are either on the boundary or can reach the boundary.
```



#### Solution

```java
class Solution {
    int r,c;
    public int numEnclaves(int[][] A) {
        r = A.length;
        c = A[0].length;
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j < c ; j++){
                if(A[i][j] == 1 && (i == 0 || j == 0 || i == r - 1 || j == c - 1)){
                    dfs(A, i, j);
                }
            }
        }
        
        int res = 0;
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j < c ; j++){
                if(A[i][j] == 1)    res++;
            }
        }
        return res;
    }
    
    public void dfs(int[][] A, int x, int y){
        if(x < 0 || x >= r || y < 0 || y >= c || A[x][y] == 0)  return;
        A[x][y] = 0;
        dfs(A, x + 1, y);
        dfs(A, x - 1, y);
        dfs(A, x, y + 1);
        dfs(A, x, y - 1);
    }
}
```

