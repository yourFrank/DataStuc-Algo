package com.ty.quicksort.leetcode;

import java.util.Arrays;

/**
 *
 * 这道题因为数字只有0,1,2 . 而我们partition 的过程就是对一个数进行比较，把小于和大于的数放在两侧
 * 对于这道题我们只需要选择和1这个数进行比较，使用三路排序的一次partition就可以解决了
 */
public class SelectColors {

    public void sortColors(int[] nums) {

        int pivot = 1; //此时就不用随机取元素，也不用放到第一个位置了。只需要和1进行比较就可以了
//        循环不变量[0,i] < 1
//            [i+1,k) ==1
//            [j,nums.length-1] >1;
        int i=-1;
        int j=nums.length;
        int k=i+1;
        while (k<j){
            if (nums[k]==0){
                i++;
                swap(nums,i,k);
                k++;
            }else if (nums[k]==2){
                j--;
                swap(nums,j,k);
            }else{
                k++;
            }
        }
    }

    private static  void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{2,0,2,1,1,0};
        SelectColors selectColors=new SelectColors();
        selectColors.sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

}
