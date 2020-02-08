#### Description

There are **N** students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a **direct** friend of B, and B is a **direct** friend of C, then A is an **indirect** friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a **N\*N** matrix **M** representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are **direct** friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

**Example 1:**

```
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
```



**Example 2:**

```
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```

#### Intuition

常规遍历二维数组的思路在这里并不适用

最多可能产生n个朋友圈，所以依次遍历每个学生，设置一个判断是否访问过的数组，从一个学生出发开始DFS

#### Solution

```java
class Solution {
    int n;
    public int findCircleNum(int[][] M) {
        int res = 0;
        n = M.length;
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                res++;
                dfs(M, i, visited);
            }
        }
        return res;
    }
    
    public void dfs(int[][] M, int i, boolean[] visited){
        for(int j = 0 ; j < n ; j++){
            if(M[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }
}
```

