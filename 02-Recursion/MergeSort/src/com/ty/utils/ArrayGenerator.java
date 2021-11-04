package com.ty.utils;

import java.util.Random;

public class ArrayGenerator {
    private ArrayGenerator() {}

    /**
     * 生成n个有序数组
     * @param n 数组规模
     * @return
     */
    public static Integer[] generatorOrderedArray(int n){
        Integer arr[]=new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i]=i;
        }
        return arr;
    }

    /**
     * 生成n个无序数组
     * @param n 数组规模
     * @param bound 随机数边界最大值
     * @return
     */
    public static Integer[] generatorRandomArray(int n,int bound){
        Random random=new Random();
        Integer arr[]=new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i]=random.nextInt(bound); //随机数，最大值为bound用户指定
        }
        return arr;
    }
}