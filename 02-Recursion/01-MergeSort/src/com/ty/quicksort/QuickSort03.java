package com.ty.quicksort;


import com.ty.utils.ArrayGenerator;

import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort02优化：解决相同的数组会排序变成O(n平方)的复杂度
 *
 */
public class QuickSort03 {

    private QuickSort03() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {

        Random random=new Random();
        sort(arr, 0, arr.length - 1,random);
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r,Random random) {
        if (l >= r) {
            return;
        }
        int j = partition(arr, l, r,random);
        sort(arr, l, j - 1,random);
        sort(arr, j + 1, r,random);

    }

    /**
     *  问题1:  当传入的数组是相同元素(或者相同元素数量很多时),使用这种方法，还是会将相同的元素全都移入到一侧。导致分区不均匀
    *   解决:  通过双路排序的方式，划分出>=v和<=v的元素分别在两侧
    */
    public static <E extends Comparable<E>> int partition(E[] arr, int l, int r,Random random) {
        int left=l+random.nextInt(r-l+1);
        swap(arr,left,l); //将这个元素作为第一个元素
//        [l+1,i-1]<=v, [j+1,r]>=v
        int i=l+1;
        int j=r;
        while (true){
            while (i<=j&&arr[i].compareTo(arr[l])<=0){  //一直找到一个i大于的v的索引
                i++;
            }
            while (i<=j&&arr[j].compareTo(arr[l])>=0){// 一直找到一个j小于v的索引
                j--;
            }
            if (i>=j){ //如果i==j时说明这个元素就是v
                break;
            }
            swap(arr,i,j);  //将这两个索引交换

        }
        swap(arr,l,j);
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 50;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
