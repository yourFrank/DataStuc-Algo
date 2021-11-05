package com.ty.mergesort;

import com.ty.insertionsort.InsertionSort;
import com.ty.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * 优化版的mergesort
 */
public class MergeSort02 {
    private MergeSort02() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length ); //优化3：提前准备好数组，而不是每次归并的时候再去内存分配空间new
        sort(arr, 0, arr.length - 1,temp);
    }


    /**
     *
     * 优化2，当数组规模小的情况下，使用插入排序。但是在某些高级语言中对小规模数据可能并没有变快，因此一般不适用此优化，面试可以提
     */
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r,E[] temp) {

        //如果数组规模小于等于16则使用插入排序,进行插入排序比归并快
        if (r - l <= 15) {
            InsertionSort.sortInSection(arr,l,r);
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid,temp);
        sort(arr, mid + 1, r,temp);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {   //优化1: 如果arr[mid]<=arr[mid+1] 就不需要排序了，否则进行排序
            merge(arr, l, mid, r,temp);
        }
    }


    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r,E[] temp) {

//        E[] temp = Arrays.copyOfRange(arr, l, r + 1); //每次merge都要在内存开辟空间(类似链表开辟node，这都是比较耗时的操作)
        //此时就不需要开辟新的内存空间了，只需要保证要排序的这个空间[l,r]数据一致即可(因为之前小规模中的数已经排序了，但是temp一直是最初始的状态)
        System.arraycopy(arr,l,temp,l,r-l+1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                arr[k] = temp[j - l];
                j++;
            } else if (j == r + 1) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }

        }

    }

    public static void main(String[] args) {
        int n=50;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        MergeSort02.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
