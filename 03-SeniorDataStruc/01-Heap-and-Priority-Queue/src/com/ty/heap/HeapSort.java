package com.ty.heap;

public class HeapSort {
    //最简单的堆排序
    public static<E extends Comparable<E>> void sort(E[] data){
        MaxHeap<E> maxHeap=new MaxHeap<>();
        for (E e:data){
            maxHeap.add(e);
        }
        for (int i=data.length-1;i>=0;i--){
            data[i]=maxHeap.extractMax();
        }

    }

    //优化的堆排序(原地排序，不使用辅助数组)
    public static <E extends Comparable<E>> void sort2(E[] data){
        //1、首先使用heaptify将数组构建成堆
        for (int i=(data.length- 2 / 2);i>=0;i--){
            siftDown(data,i,data.length);
        }
        //2、将堆首和最后一个元素交换，交换后siftDown维持堆[0,i]
        for (int i=data.length-1;i>=0;i--){
            swap(data,0,i);
            siftDown(data,0,i); //此时堆的size变成i（i代表索引=data.length-1），第i个元素是最大元素已经有序
        }

    }

    //单独实现siftDown方法
    private  static <E extends Comparable<E>> void siftDown(E[] data,int k,int n) {

        while (k*2+1 < n - 1) {
            int j = k*2+1;
            if (j + 1 < n && data[j+1].compareTo(data[j])>0) {
                j = j + 1; //此时找到j指向最大孩子节点的索引
            }
            if (data[k].compareTo(data[j]) >= 0) //如果比k比孩子节点都大了，循环就可以停止了
                break;

           swap(data,k, j);
            k = j;

        }

    }


    private static<E extends Comparable<E>> void swap(E[] data,int i, int j) {
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
