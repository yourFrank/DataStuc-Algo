package com.ty.selectionsort;

import com.ty.utils.ArrayGenerator;
import com.ty.utils.SortingHelper;

/**
 * 实现带泛型的选择排序
 */
public class SelectionSort2 {

    private SelectionSort2() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {  //细节1:对象进行比较，必须要求实现了排序接口，泛型上这里extends是实现接口的意思
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {  //细节2: 对象之间要使用compareTo进行比较
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int dataSize[]={10000,100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", arr); //当n的规模是十倍的时候，消耗的时间是n²倍
        }
    }
}
