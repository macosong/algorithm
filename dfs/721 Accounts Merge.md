#### Description

Given a list `accounts`, each element `accounts[i]` is a list of strings, where the first element `accounts[i][0]` is a *name*, and the rest of the elements are *emails* representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails **in sorted order**. The accounts themselves can be returned in any order.

**Example 1:**

```
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
```

#### Intuition

本质上是一个图问题。用一个hashmap记录所有的email及它和它相邻email的关联关系。



#### Solution

```java
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> name = new HashMap<>();
        for(List<String> account : accounts){
            String userName = account.get(0);
            for(int i = 1 ; i < account.size() ; i++){
                graph.putIfAbsent(account.get(i), new HashSet<>());
                name.put(account.get(i), userName);
                if(i == 1)  continue;
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }
        
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        for(String email : graph.keySet()){
            if(visited.add(email)){
                List<String> list = new LinkedList<>();
                dfs(graph, email, list, visited);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }
        return res;
    }
    
    public void dfs(Map<String, Set<String>> graph, String email, List<String> list, Set<String> visited){
        list.add(email);
        for(String next : graph.get(email)){
            if(visited.add(next)){
                dfs(graph, next, list, visited);
            }
        }
    }
}
```

