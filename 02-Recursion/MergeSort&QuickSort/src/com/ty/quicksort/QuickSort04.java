package com.ty.quicksort;

import com.ty.utils.ArrayGenerator;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Random;

/**
 * 三路快排：
 * 时间复杂度:最坏复杂度O（n²)（每次都选到了最值，几乎不可能） 期望时间复杂度: O(nlogn)
 * 在双路快排的基础上我们完全可以找到和v这个元素相等的值,维护一个和v元素相等的区间，这个区间确定好位置后就不需要再排了
 * 实际应用中可以用来解决select k 问题（第k小元素）
 */
class QuickSort04 {
    private QuickSort04() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random random) {
        if (l >= r) {
            return;
        }
        int left = l + random.nextInt(r - l + 1);
        swap(arr, left, l); //将这个元素作为第一个元素
//        [l+1,lt]<v,[lt+1,i)=v [gt,r]>v
        int lt = l;
        int gt = r + 1;
        int i = lt + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {
                swap(arr, lt+1, i);
                lt++;
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                gt--;   //此时k不能+1,因为交换后的当前元素还没有判断区间
                swap(arr, gt, i);
            } else {
                i++;
            }

        }
        swap(arr, l, lt);
        sort(arr, l, lt - 1, random); // 注意这里，大大减少了分治的区间，区间 [lt, gt - 1] 不必递归求解
        sort(arr, gt, r, random);
    }

    /* 不同的循环不变量实现
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random random) {
        if (l >= r) {
            return;
        }
        int left = l + random.nextInt(r - l + 1);
        swap(arr, left, l); //将这个元素作为第一个元素
//        [l+1,i-1]<v, [i,k)=v  [j+1,r]>v
        int i = l + 1; //当i=l+1时为空区间,满足[l+1,i-1]<v
        int k = i;
        int j = r; //当j=r时,[r+1,r]为空,满足[j+1,r]>v
        while (k < j + 1) {
            if (arr[k].compareTo(arr[l]) < 0) {
                swap(arr, i, k);
                i++;
                k++;
            } else if (arr[k].compareTo(arr[l]) > 0) {
                j--;   //此时k不能+1,因为交换后的当前元素还没有判断区间
                swap(arr, j+1, k);
            } else {
                k++;
            }
        }
        swap(arr, l, i - 1); //此时区间[l,i-2]<v [i-1,k]=v,[j+1,r]>v
        sort(arr, l, i - 2, random);
        sort(arr, j + 1, r, random);
    }
*/

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 100;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n,n);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
