package com.ty.quicksort;

import com.ty.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * 单路快排，在一端整理出小于标准点和大于标准点： 对完全有序的会退化
 */
public class QuickSort01 {
    private QuickSort01() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {

        sort(arr,0,arr.length-1);
    }
    public static <E extends Comparable<E>> void sort(E[] arr,int l,int r) {
        if (l>=r){
            return ;
        }
        int j = partition(arr, l, r); //j中左边比j小，右边比j大
        sort(arr,l,j-1);
        sort(arr,j+1,r);

    }


    public static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        //保证从[l+1,j]元素小于l   [j+1,i-1]元素大于l
        int left = l;
        int j = l; //j从l开始，[l+1,j]区间为空区间
        for (int i = l; i <= r; i++) {  //每次移动i,i指向当前元素
            if (arr[i].compareTo(arr[l]) < 0) {
//                swap(arr, i, j+1);
//                j++;
                j++;   //或者上面那种都可以，只要维持循环不变量即可
                swap(arr, i, j);

            }
        }

        swap(arr, j, l); //最后将l元素放入中间j的位置
        return j;

    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n=100;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

