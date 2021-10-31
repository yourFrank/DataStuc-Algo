package com.ty.InsertionSort;

import com.ty.utils.ArrayGenerator;
import com.ty.utils.SortingHelper;

//插入排序01 :如果前一个元素比当前小，则两两交换，缺点:每次swap操作都要进行三次赋值
public class InsertionSort1 {
    private InsertionSort1(){};

    //循环不变量：保证从[0,i)上是有序的，从[i,n)是未排序的
    public static <E extends Comparable<E>>void sort(E[] arr){
        for (int i=0;i< arr.length;i++){
            //将arr[i]插入到合适的位置
//            for (int j=i;j-1>=0;j--){
//                if (arr[j].compareTo(arr[j-1])<0){ //每次当前元素都可能交换了位置，因此使用j和前一个元素比较而不是i
//                    swap(arr,j,j-1);
//                }else{
//                    break; //注意，如果已经比前一个小了，要注意break。不需要继续向前比较了
//                }
//            }
            for (int j=i;j-1>=0&&arr[j].compareTo(arr[j-1])<0;j--){ //上面的if和for中的判断可以进行合并，这样就可以省去break
                    swap(arr,j,j-1);
            }
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
            SortingHelper.sortTest("InsertionSort", arr); //当n的规模是十倍的时候，消耗的时间是n²倍
        }
    }
}
