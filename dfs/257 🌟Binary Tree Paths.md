#### Description

Given a binary tree, return all root-to-leaf paths.

**Note:** A leaf is a node with no children.

**Example:**

```
Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
```

#### Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<List<TreeNode>> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if(root == null)    return ans;
        dfs(root, new ArrayList<>());
        for(List<TreeNode> list : res){
            String cur = "";
            for(int i = 0 ; i < list.size() ; i++){
                cur += list.get(i).val;
                if(i != list.size() - 1)    cur += "->";
            }
            ans.add(cur);
        }
        return ans;
    }
    
    public void dfs(TreeNode node, List<TreeNode> tmp){
        tmp.add(node);
        if(node.left == null && node.right == null) res.add(tmp);
        else{
            if(node.left != null)   dfs(node.left, new ArrayList<>(tmp));
            if(node.right != null)  dfs(node.right, new ArrayList<>(tmp));
        }
    }
}
```

**改进一**，可以直接在遍历的时候就构造字符串

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root != null)    dfs(root, "", res);
        return res;
    }
    
    public void dfs(TreeNode node, String path, List<String> res){
        if(node.left == null && node.right == null) res.add(path + node.val);
        if(node.left != null)   dfs(node.left, path + node.val + "->", res);
        if(node.right != null)  dfs(node.right, path + node.val + "->", res);
    } 
}
```

**改进二**，实际上，Java中String的拼接是一个时间复杂度很高的行为，javap -c反编译

```java
public class Test{
  public static void main(String[] args){
    String s1 = "111";
    String s2 = "222";
    String str = s1 + s2;
    System.out.println(str);
  }
}
```

反编译上面Java文件可以看到

```shell
Compiled from "Test.java"
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String 111
       2: astore_1
       3: ldc           #3                  // String 222
       5: astore_2
       6: new           #4                  // class java/lang/StringBuilder
       9: dup
      10: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
      13: aload_1
      14: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      17: aload_2
      18: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      21: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      24: astore_3
      25: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      28: aload_3
      29: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      32: return
}
```

实际上String的“+”拼接每次都新建两个StringBuilder对象，然后append和toString输出，在递归中大量使用string拼接会产生大量的StringBuilder对象并多次调用toString方法，时间空间上消耗很大。

所以我们只需要一个StringBuilder对象来进行路径的记录就可以。要记得setLength回溯

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root != null)    dfs(root, new StringBuilder(), res);
        return res;
    }
    
    public void dfs(TreeNode node, StringBuilder sb, List<String> res){
        int len = sb.length();
        sb.append(node.val);
        if(node.left == null && node.right == null) res.add(sb.toString());
        else{
            sb.append("->");
            if(node.left != null)   dfs(node.left, sb, res);
            if(node.right != null)  dfs(node.right, sb, res);
        }
        sb.setLength(len); //回溯
    }
}
```

