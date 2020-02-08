#### Description

In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for **exactly** certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

**Example 1:**

```
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
```

#### Solution

```java
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs, 0);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos){
        int curPrice = directPrice(price, needs);
        for(int i = pos ; i < special.size() ; i++){
            List<Integer> offer = special.get(i);
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0 ; j < needs.size() ; j++){
                if(offer.get(j) > needs.get(j)){
                    tmp = null;
                    break;
                }
                tmp.add(needs.get(j) - offer.get(j));
            }
            if(tmp != null){
                curPrice = Math.min(curPrice, offer.get(offer.size() - 1) + helper(price, special, tmp, i));
            }
        }
        return curPrice;
    }
    
    public int directPrice(List<Integer> price, List<Integer> needs){
        int total = 0;
        for(int i = 0 ; i < needs.size() ; i++){
            total += price.get(i) * needs.get(i);
        }
        return total;
    }
}
```

