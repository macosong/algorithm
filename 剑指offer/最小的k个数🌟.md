输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

#### Solution

**1.基于快排**

时间复杂度$O(n*log_2k)$

```java
import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] a, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(k == 0)    return res;
        int low = 0, high = a.length - 1;
        while(low <= high){
            int p = a[low];
            int i = low, j = high;
            while(i < j){
                while(i < j && a[j] >= p)    j--;
                while(i < j && a[i] <= p)    i++;
                if(i < j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            a[low] = a[i];
            a[i] = p;
            
            if(i == k - 1){
                for(int m = 0 ; m <= i; m++)    res.add(a[m]);
                break;
            }else if(i < k - 1){
                low = i + 1;
            }else{
                high = i - 1;
            }
        }
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
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] a, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(k == 0 || k > a.length)    return res;
        PriorityQueue<Integer> q =  new PriorityQueue<>((o1,o2) -> o2 - o1);
        for (int cur : a){
            q.offer(cur);
            if (k-- > 0)    continue;
            else    q.poll();
        }
        while (!q.isEmpty())    res.add(q.poll());
        return res;
    }
}
```

