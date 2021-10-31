package com.ty.InsertionSort;

/*插入排序2(优化): 使用额外元素保存当前元素，不断将小于当前元素的值向后移动一个。最后归位，每次只是一次赋值操作（相比之前交换3次赋值会快，但是时间复杂度上不变）
复杂度 O(n²)：当内层循环每次都要走到1时（最坏） ，时间复杂度为O（n²）。而对于有序数组 内层循环每次只是查看一下前一个就退出了，因此是O（n）级别，但是我们通常只看最坏的因此是O(n²)
对比选择排序: 选择排序无论元素顺序是否有序，每次都要查看后面的所有元素然后找出最小的元素，因此最好最坏都是O(n²)


 */
public class InsertionSort2 {
    private InsertionSort2() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E temp=arr[i]; //首先将当前元素保存下来，后移时会将当前元素进行覆盖
            int j;
            for (j=i;j>0;j--){ //从i开始判断，可能i就是要插入的位置
                if (temp.compareTo(arr[j-1])<0){ //前一个元素与元素i进行比较
                    arr[j]=arr[j-1]; //元素后移
                }else {
                    break;
                }
            }
            arr[j]=temp; //结束后j-1比当前元素大了，因此j就是要插入的元素的位置

        }
    }

}
