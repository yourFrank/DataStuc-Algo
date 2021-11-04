package com.ty.insertionsort;

import com.ty.utils.ArrayGenerator;

import java.util.Arrays;

//插入排序
public class InsertionSort {
    private InsertionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E temp = arr[i]; //首先将当前元素保存下来，后移时会将当前元素进行覆盖
            int j;
            for (j = i; j > 0; j--) { //从i开始判断，可能i就是要插入的位置
                if (temp.compareTo(arr[j - 1]) < 0) { //前一个元素与元素i进行比较
                    arr[j] = arr[j - 1]; //元素后移
                } else {
                    break;
                }
            }
            arr[j] = temp; //结束后j-1比当前元素大了，因此j就是要插入的元素的位置

        }
    }

    //基于区间的插入排序
    public static <E extends Comparable<E>> void sortInSection(E[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            E temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1].compareTo(temp) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        InsertionSort.sortInSection(arr, 3, 5);
        System.out.println(Arrays.toString(arr));

    }

}
