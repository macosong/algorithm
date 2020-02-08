输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

#### Solution

两个指针分别从数组的前后搜索，因为有序所以找到的第一对和等于S的两个数的乘积一定最小。

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] a,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if(a == null || a.length == 0)    return res;
        int i = 0, j = a.length - 1;
        while(i < j){
            if(a[i] + a[j] == sum){
                res.add(a[i]);
                res.add(a[j]);
                break;
            }else if(a[i] + a[j] < sum)    i++;
            else    j--;
        }
        return res;
    }
}
```

