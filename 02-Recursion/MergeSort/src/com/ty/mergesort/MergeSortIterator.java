package com.ty.mergesort;

import com.ty.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * 自底向上的归并排序，使用迭代。由小区间逐渐到大区间的合并
 */
public class MergeSortIterator {

    private MergeSortIterator() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr,  arr.length - 1);
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int r) {

        for (int sz = 1; sz < r; sz +=sz) { //sz从1开始每次合并的区间递增sz个单位
            for (int i = 0; i + sz < r; i += sz + sz) {  //每次移动两个sz
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) //如果区间已经有序则不用排，否则进行下面排序
                    merge(arr, i, i + sz - 1, Math.min(r - 1, i + sz + sz - 1)); //最后一步合并时最右边的区间可能越界，如果越界选择最右边r的索引
            }

        }

    }


    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {

        E[] temp = Arrays.copyOfRange(arr, l, r + 1); //拷贝一个区间的数组，默认是右开区间[l,r) ,因此r设置成r+1,拷贝后新数组从0开始
        int i = l; //两个指针分别指向两个区间的第一个节点
        int j = mid + 1;
        for (int k = l; k <= r; k++) { //将l到r的元素合并顺序，此时需要用到temp辅助数组
            if (i == mid + 1) { //i越界
                arr[k] = temp[j - l]; //注意这里temp的下标时从0开始的,偏移了l
                j++;
            } else if (j == r + 1) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) { //注意这里是比较temp中的，而不是比较arr。arr中的元素已经改变，只是用来存放合并后的
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }

        }

    }

    public static void main(String[] args) {
        int n = 10;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
