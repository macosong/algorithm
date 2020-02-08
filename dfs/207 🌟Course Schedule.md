#### Description

There are a total of *n* courses you have to take, labeled from `0` to `n-1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite **pairs**, is it possible for you to finish all courses?

**Example 1:**

```
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**

```
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
```

#### Intuition

这是一个拓扑排序的问题，使用一个hashmap保存图结构，一个数组保存每个节点的入度。

循环步骤为：记录当前入度为0的节点个数，然后删除它们及其相连的边。一直迭代此步骤，直到不能进行下去为止。如果还有节点没有删除，那么就返回false，否则返回true。

#### Solution

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i++)   map.put(i, new ArrayList<>());
        for(int[] pre : prerequisites){
            map.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < numCourses ; i++)   if(indegree[i] == 0)    q.offer(i);
        int count = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            count++;
            
            for(int next : map.get(cur)){
                if(--indegree[next] == 0)   q.offer(next);
            }
        }
        return count == numCourses;
    }
}
```

