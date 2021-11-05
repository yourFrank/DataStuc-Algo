package com.ty.quicksort;

import com.ty.utils.ArrayGenerator;

import java.util.Arrays;
import java.util.Random;


/**
 * QuickSort01优化：解决有序数组会排序变成O(n平方)的复杂度
 */
public class QuickSort02 {
    private QuickSort02() {
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


    /*问题1:   如果每次都选择第一个元素，当元素有序（从大到小或从小到大）的情况下，每次分区这个元素j都会在一侧，j的另一侧是剩下的所有元素。也就是说分区严重不均
            这样递归的深度就为n,当n很大时会有栈溢出的风险，并且此时快排会变成O(n*n)的复杂度

     问题1解决方案：选择一个随机数作为基准元素,而不是每次选择第一个数,这样我们就没法保证有这么一组数据每次都是O(n*n)的复杂度，
     并且当n很大时，我们每次随机选出的元素都是最小或者最大的几乎是不可能的1/n*(n-1)...1  =1/n! 的概率
     */
    public static <E extends Comparable<E>> int partition(E[] arr, int l, int r,Random random) {
        // int left = l;
//        int left=l+new Random().nextInt(r-l);//因为没有直接选择[l,r]区间元素的方法，因此通过选择[0,r-l]+l 的方式来获取 ,
//        可以将random在外面new好传递进来，这样不用每次partition的时候都new，消耗内存时间
        int left=l+random.nextInt(r-l);
        swap(arr,left,l); //将这个元素作为第一个元素
        int j = l;
        for (int i = l; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }

        swap(arr, j, l);
        return j;

    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 20;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
