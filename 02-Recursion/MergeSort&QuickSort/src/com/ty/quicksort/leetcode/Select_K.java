package com.ty.quicksort.leetcode;

import java.util.Random;
/**
 * 快速排序可以解决select K问题，在partition的过程中，我们可以确定第k个元素所在的位置，递归过程每次判断后处理一半的数即可
 * 时间复杂度T(n)= n+ n/2+n/4 。。。1 = O（2n）= O（n） 等比数列
 * 下面是求第k个最小元素的模板,注意k对应到数组中索引的时候要k-1, 并且当k等于0的时候，k-1=-1 此时直接返回一个空数组即可
 * 对于第k个最大元素，就是对应第arr.length-k 索引最小元素
 */
public class Select_K {

//    public int[] getLeastNumbers(int[] arr, int k) {
//        selectK(arr,0,arr.length-1,k-1,new Random());
//        return Arrays.copyOf(arr,k);  使用Arrays.copyof方法， 第二个参数指的时数组的大小，此时传入k即可
//    }

    private int selectK(int[] arr, int l, int r, int k, Random rnd){

        int p = partition(arr, l, r, rnd);

        if(k == p) return arr[p];  //k此时已经归位，返回即可

        if(k < p) return selectK(arr, l, p - 1, k, rnd); //如果k<p，此时在左边进行递归
        return selectK(arr, p + 1, r, k, rnd);

    }

    public int partition(int[] nums, int l, int r, Random random){
        int pivot = l+random.nextInt(r - l + 1);
        swap(nums,l,pivot);
//        [l+1,i)<=v  (j,r]>=v
        int i=l+1;
        int j=r;
        while (true){
            while (i<=j&& nums[i]<nums[l]){
                i++;
            }
            while (i<=j&& nums[j]>nums[l]){
                j--;
            }
            if (i>=j){
               break;
            }
            swap(nums,i,j);
            i++;
            j--;
        }
        swap(nums,l,j);
        return j;

    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }



}
