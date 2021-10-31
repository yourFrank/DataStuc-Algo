package com.ty.SelectionSort;

import java.util.Arrays;

/**
 * 选择排序。 O(n²)
 * 复杂度分析: 当n等于0时，内层循环n次;当n等于1时，内层循环n-1次......当n等于n-1时,内层循环进行了1次。总共: n+n-1+....1 = 等差数列 (1+n)*n/2
 *
 */
public class SelectionSort1 {

    private SelectionSort1() {
    }

    ;

    public static void sort(int[] arr) {
//     循环不变量 : 保证从[0,i)有序的，arr[i...n) 是未排序的 。第一个元素也是要进行选择的，因此右边为开区间. i代表当前元素
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            //每一次选择arr[i,n]中的最小值
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) { //如果比mindIndex小，则设置当前下标（打擂台）
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex); //一层循环后找出最小的元素下标和i进行交换
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 3, 6, 5};
        SelectionSort1.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
