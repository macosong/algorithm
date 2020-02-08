#### Description

Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used **exactly** one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

**Example 1:**

```
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
```



**Example 2:**

```
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
```

#### Solution

```java
class Solution {
    public boolean makesquare(int[] nums) {
        if(nums.length == 0)    return false;
        int sum = 0;
        for(int i : nums)   sum += i;
        if(sum % 4 != 0)    return false;
        
        Arrays.sort(nums);
        reverse(nums);
        
        return dfs(nums, new int[4], 0, sum / 4);
        
    }
    
    public boolean dfs(int[] nums, int[] a, int index, int target){
        if(index == nums.length){
            if(a[0] == target && a[1] == target && a[2] == target)  return true;
            return false;
        }
        
        for(int i = 0 ; i < 4 ; i++){
            if(a[i] + nums[index] > target) continue;
            a[i] += nums[index];
            if(dfs(nums, a, index + 1, target)) return true;
            a[i] -= nums[index];
        }
        
        return false;
    }
    
    public void reverse(int[] nums){
        for(int i = 0 ; i < nums.length / 2 ; i++){
            int tmp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = tmp;
        }
    }
}
```

