package com.ty.selectionsort;

import com.ty.utils.ArrayGenerator;
import com.ty.utils.SortingHelper;

//反向实现选择排序
public class SelectionSort3 {
    private SelectionSort3(){}

//    实现循环不变量[i,n）是有序的，[0,i)上是未排序的
    public static<E extends Comparable> void sort(E arr[]){
        for (int i = arr.length-1; i >=0 ; i--) {
            int maxIndex=i;
            for (int j=i;j>=0;j--){
                if (arr[j].compareTo(arr[maxIndex])>0){
                    maxIndex=j;
                }
            }
            swap(arr,maxIndex,i);
        }
    }

    private static<E> void swap(E[] arr, int i, int j) {
        E temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        int dataSize[]={10000,100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
            SortingHelper.sortTest("SelectionSortReverse", arr); //当n的规模是十倍的时候，消耗的时间是n²倍
        }
    }
}
