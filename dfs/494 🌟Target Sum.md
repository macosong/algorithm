#### Description

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols `+` and `-`. For each integer, you should choose one from `+` and `-` as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

**Example 1:**

```
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
```

#### Intuition

https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation

#### Solution

```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int n : nums)   sum += n;
        if(S > sum || (S + sum) % 2 == 1)   return 0;
        return search(nums, (S + sum) / 2);
    }
    
    public int search(int[] nums, int s){
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for(int n : nums){
            for(int i = s ; i >= n ; i--){
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }
}
```

