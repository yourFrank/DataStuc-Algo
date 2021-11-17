package com.ty.quicksort;


import com.ty.utils.ArrayGenerator;

import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort02优化：使用双路排序解决相同的数组会排序变成O(n平方)的复杂度
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
//        [l+1,i-1]<=v, [j+1,r]>=v  //注意等于也要交换，这样才可以让相等的元素均匀分布到两侧（想象一下全是相同的数组，如果等于不作交换的话，首先循环的i会一直走下去，一直到j）
        int i=l+1;
        int j=r;
        while (true){
            // 当i等于j的时候要继续判断是否移动，如果不移动无法判断该节点的大小，没法做最后的和第一个元素l交换
            while (i<=j&&arr[i].compareTo(arr[l])<0){  //一直找到一个i大于等于的v的索引。
                i++;
            }
            while (i<=j&&arr[j].compareTo(arr[l])>0){// 一直找到一个j小于等于v的索引
                j--;
            }
            if (i>=j){ //如果i==j时说明这个元素就是v(左边找到i>=v，右边找到的i<=v，此时i=j说明就是v)，就不需要处理了
                break;
            }
            swap(arr,i,j);  //将这两个索引交换
            i++;  //交换后继续走,这里一定要继续移动。如果不移动会一直交换这两个节点死循环
            j--;

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
