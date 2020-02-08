#### Description

An `image` is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate `(sr, sc)` representing the starting pixel (row and column) of the flood fill, and a pixel value `newColor`, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

**Example 1:**

```
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
```

#### Intuition

必须检查起点的原始color是否和newColor相同，如果相同就直接返回数据。如果不检查直接dfs会`stackoverflow`

```java
class Solution {
    int r,c;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        r = image.length;
        c = image[0].length;
        int raw = image[sr][sc];
        if(raw == newColor) return image;
        dfs(image, sr, sc, raw, newColor);
        return image;
    }
    
    public void dfs(int[][] image, int i, int j, int raw, int newColor){
        if(i < 0 || i >= r || j < 0 || j >= c || image[i][j] != raw)    return;
        image[i][j] = newColor;
        dfs(image, i + 1, j, raw, newColor);
        dfs(image, i - 1, j, raw, newColor);
        dfs(image, i, j + 1, raw, newColor);
        dfs(image, i, j - 1, raw, newColor);
    }
}
```

