package com.ty.mergesort;

import com.ty.utils.ArrayGenerator;

import java.util.Arrays;


class MergeSort01 {

    private MergeSort01() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {

        if (l >= r) { //只有一个元素就不用排了
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid); //对左边[l,mid]进行排序
        sort(arr, mid + 1, r);//对右边[mid+1,r]进行排序
        merge(arr, l, mid, r); //合并[l,r]区间

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
        int n=20;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }


}

