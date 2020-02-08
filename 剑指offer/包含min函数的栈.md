定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

#### Solution

```java
import java.util.Stack;

public class Solution {

    Stack<Integer> s = new Stack<>();
    Stack<Integer> minS = new Stack<>();
    public void push(int node) {
        s.push(node);
        if(minS.isEmpty())    minS.push(node);
        else if(node <= minS.peek())    minS.push(node);
    }
    
    public void pop() {
        if(s.peek() == minS.peek())    minS.pop();
        s.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int min() {
        return minS.peek();
    }
}
```

