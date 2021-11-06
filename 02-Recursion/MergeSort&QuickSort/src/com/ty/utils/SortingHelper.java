package com.ty.utils;


public class SortingHelper {

    private SortingHelper() {
    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) { //如果后一个比前一个小，说明排序不正确，返回false
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {
        long startTime = System.nanoTime(); //使用纳秒时间
//        if (sortName.equals("SelectionSort")) {
//            SelectionSort2.sort(arr);
//        }else  if (sortName.equals("InsertionSort")) {
//            InsertionSort.sort(arr);
//        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0; //1s=1000毫秒=1000微秒=1000纳秒

        if (!isSorted(arr)) { //如果排序失败，则抛出异常
            throw new RuntimeException(sortName + "failed");
        }
        System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, time)); //算法名: 数组长度 : 花费的时间s
    }

}
