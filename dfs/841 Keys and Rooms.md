#### Description

There are `N` rooms and you start in room `0`. Each room has a distinct number in `0, 1, 2, ..., N-1`, and each room may have some keys to access the next room. 

Formally, each room `i` has a list of keys `rooms[i]`, and each key `rooms[i][j]` is an integer in `[0, 1, ..., N-1]` where `N = rooms.length`. A key `rooms[i][j] = v` opens the room with number `v`.

Initially, all the rooms start locked (except for room `0`). 

You can walk back and forth between rooms freely.

Return `true` if and only if you can enter every room.



**Example 1:**

```
Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.
```

**Example 2:**

```
Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.
```



#### Solution

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for(int i : rooms.get(0)){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, rooms, visited);    
            }
            
        }
        for(boolean visit : visited)    if(!visit)  return false;
        return true;
    }
    
    public void dfs(int i, List<List<Integer>> rooms, boolean[] visited){
        List<Integer> cur = rooms.get(i);
        for(int j = 0 ; j < cur.size() ; j++){
            if(!visited[cur.get(j)]) {
                visited[cur.get(j)] = true;
                dfs(cur.get(j), rooms, visited);
            }
        }
    }
}
```

