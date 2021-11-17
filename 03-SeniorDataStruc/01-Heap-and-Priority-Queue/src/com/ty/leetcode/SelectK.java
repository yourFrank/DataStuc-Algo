package com.ty.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

//top k（在N个元素中选取最大的K个元素） 、select k(在N个元素中选取最小的K个元素)
//时间复杂度O(nlogK) 空间复杂度O(k)
public class SelectK {

    //选取数组中第K大的数字：
    //解决思路: 使用最小堆，首先维护K个元素，接下来的每个元素都和堆首的元素作比较，堆首是这k个中最小的,如果新的比它大，就舍弃这个元素
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap=new PriorityQueue<>(); //默认是从小到大比较，最小堆不用传参数
            for (int i = 0; i < k; i++) {
                minHeap.add(nums[i]);
            }
            for (int i=k;i<nums.length;i++){
                if (nums[i]>minHeap.peek()){
                    minHeap.poll();
                    minHeap.add(nums[i]);
                }
            }
            return minHeap.peek();
        }
    }
    //使用java中PriorityQueue，可以指定比较的方式，Collections.reverseOrder() 可以实现最大堆
}
