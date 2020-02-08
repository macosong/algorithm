#### Description

Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.

 

**Example:**

```
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
```



#### Solution

```java
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(new LinkedList<>(), 0, nums, res);
        return res;
    }
    
    public void dfs(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res){
        if(list.size() > 1)    res.add(new LinkedList<>(list));
        Set<Integer> used = new HashSet<>();	//防止结果集res里出现重复的数组
        for(int i = index ; i < nums.length ; i++){
            if(used.contains(nums[i]))  continue;
            if(list.size() == 0 || nums[i] >= list.peekLast()){
                used.add(nums[i]);
                list.add(nums[i]);
                dfs(list, i + 1, nums, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
```

