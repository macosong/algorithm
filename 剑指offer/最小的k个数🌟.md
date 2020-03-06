输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

#### Solution

**1.基于快排**

时间复杂度$O(N)$

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] a, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = a.length;
        if(k > n)    return res;
        int left = 0, right = n - 1;
        while(left < right){
            int p = a[left], i= left, j = right;
            while(i < j){
                while(i < j && a[j] >= p)    --j;
                while(i < j && a[i] <= p)    ++i;
                if(i < j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            a[left] = a[i];
            a[i] = p;
            if(i == k)    break;
            else if(i < k){
                left = i + 1;
            }else{
                right = i - 1;
            }
        }
        for(int i = 0 ; i < k ; ++i)    res.add(a[i]);
        return res;
    }
}
```

**2.基于最大堆**

堆最多只放k个数，如果超过k个数，那么每来一个数先进堆，然后移除堆顶元素，保留最小的k个数。

堆排序时间为$log_2k$，总的时间复杂度$O(n*log_2k)$

```java
import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] a, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(a == null || a.length == 0 || k > a.length || k == 0)    return res;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> (o2 - o1));
        for(int i = 0 ; i < a.length ; ++i){
            if(i < k)    maxHeap.offer(a[i]);
            else{
                if(a[i] < maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.offer(a[i]);
                }
            }
        }
        for(int tmp : maxHeap)    res.add(tmp);
        return res;
    }
}
```

