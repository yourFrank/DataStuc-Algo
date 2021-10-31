package com.ty.InsertionSort;

import com.ty.utils.ArrayGenerator;
import com.ty.utils.SortingHelper;

import java.util.Arrays;

//改变循环不变量
public class InsertionSort3 {
    private InsertionSort3() {
    }

    //实现循环不变量:在[0,i)上是未排序的, 在[i,n)上是有序的，
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = arr.length-1; i >=0; i--) {
            E temp=arr[i];
            int j;
            for (j=i;j+1< arr.length;j++){
                if (temp.compareTo(arr[j+1])>0){
                    arr[j]=arr[j+1];
                }else {
                    break;
                }
            }
            arr[j]=temp;
        }
    }

    public static void main(String[] args) {
        int dataSize[]={10000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);

            SortingHelper.sortTest("InsertionSort", arr); //当n的规模是十倍的时候，消耗的时间是n²倍

        }
    }
}
