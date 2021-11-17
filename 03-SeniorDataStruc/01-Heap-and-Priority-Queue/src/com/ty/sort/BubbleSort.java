package com.ty.sort;

public class BubbleSort {
    private BubbleSort(){}

    public static <E extends Comparable<E>> void sort(E[] data){

        for (int i=0;i+1 <data.length;i++){ //当剩一个元素的时候就不用排序了因此是i+1<data.length
            //在[n-i,n)上已经排好序了
            //通过冒泡在arr[n-i-1]位置上放上合适的元素
//            for (int j=0;j+1<=data.length-i-1;j++){
            for (int j=0;j<data.length-i-1;j++){
                if (data[j].compareTo(data[j+1])>0){
                    //每次查看相邻的元素，将大的元素向后移动
                    swap(data,j,j+1);
                }
            }
        }

    }

    //冒泡排序优化
    public static <E extends Comparable<E>> void sort2(E[] data){


        for (int i=0;i+1 <data.length;i++){
            boolean isSwapped=false;
            for (int j=0;j<data.length-i-1;j++){
                if (data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                    isSwapped=true; //如果进行交换了，则设置成true
                }
            }
            if (!isSwapped){ //如果一次排序下来还是false（没有交换）表明数组已经有序。可以退出循环
                break;
            }
        }

    }

    private static<E> void swap(E[] arr, int i, int j) {

        E temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
