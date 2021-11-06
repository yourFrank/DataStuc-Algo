package com.ty.mergesort.leetcode;

import java.util.Arrays;

/**
 * 寻找逆序对的总数 :
 * 使用归并排序，向上merge的过程中可以统计出来后面的元素比前面小的个数
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
//        int[] temp=new int[nums.length];
        int[] temp = Arrays.copyOf(nums, nums.length );
        return countPairs(nums,0, nums.length-1,nums.clone());
    }
    public int countPairs(int[] nums,int l,int r,int[] temp){
        if (l>=r){
            return 0;
        }
        int mid=l+(r-l)/2;
        int leftCount = countPairs(nums, l, mid ,temp);
        int rightCount = countPairs(nums, mid+1, r,temp);
        int mergeCount=0;
        if (nums[mid] > nums[mid + 1]) {   //优化1: 如果arr[mid]<=arr[mid+1] 就不需要排序了，否则进行排序
            mergeCount  = merge(nums, l, r, mid, temp);
        }
        return leftCount + rightCount + mergeCount;

    }
    public int merge(int[] nums,int l,int r,int mid,int[] temp){
        System.arraycopy(nums,l,temp,l,r-l+1);
        int i=l;
        int j=mid+1;
        int count=0;
        for (int k = l; k <= r; k++) {
            if (i==mid+1){
                nums[k]=temp[j];
                j++;
            }else if (j==r+1){
                nums[k]=temp[i];
                i++;
            }else if (temp[i]<=temp[j]){
                nums[k]=temp[i];
                i++;
            }else {
                count+=mid-i+1;  //当遇到后面一个元素大的时候，前面一组元素到mid之间的元素肯定都比它小
                nums[k]=temp[j];
                j++;
            }
        }
        return count;

    }





}
