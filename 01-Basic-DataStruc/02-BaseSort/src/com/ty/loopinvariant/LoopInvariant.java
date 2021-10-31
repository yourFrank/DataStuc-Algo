package com.ty.loopinvariant;

/**
 * 循环不变量:
 * 通过循环体维持循环不变量用来验证我们算法的正确性
 * <p>
 * 初始化：循环的第一次迭代之前，它为真
 * 保持： 在每一次循环开始时成立，下一次循环开始时仍然成立。（通过循环体维持循环不变量）
 * 终止：在循环终止时，则算法的有效性得以验证
 */
public class LoopInvariant {
    /**
     * 选择排序，循环不变量:在[0,i)上保持有序 [i,n）上保持无序，n为数组的长度
     * 开始时[0,i)上有序，循环过程中[i,n)上选取最小的元素放到i上,i++后，在下一层循环开始时[0,i)有序仍然成立。最后i=len 循环一直到[0,len）,保证(0,len]有序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    swap(arr, minIndex, j);
                }
            }
        }
    }

    /**
     * 插入排序,循环不变量，在[0,i)上有序，在[i,n）上无序
     * 开始时[0,i)上有序，在每一次循环体中都都让i这个位置的元素归位，i++后仍然保持[0,i）上有序成立。最后i=len 循环一直到[0,len）,保证(0,len]有序
     *
     * @param arr
     */

    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0; j--) {
                if (arr[j - 1] < temp) {
                    arr[j] = arr[j - 1];
                }
            }
            arr[j] = temp;
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 力扣第26题，删除有序数组中的重复项，返回长度，不需要考虑后面数组的元素
     * 循环不变量:[0,j]上有序，且数组都不重复
     * 用一个指针j记录不重复元素的索引，另一个指针不断向后循环整个数组，将不等于j的元素保存到j+1这个位置 。最后闭区间[0,j]长度为j-0+1;
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 力扣第283题，将所有0移动到数组的末尾,同时保证非0元素的相对顺序
     * 保证[0,i)上元素是有序的，并且不包含0
     * 用一个指针i来记录不等于0的数组索引位置，另一个指针j循环整个数组找出不等于0的元素，将不等于0的元素和当前i进行交换,i++
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        for (; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 力扣第27题，移除等于val的元素
     * 保证从[0,j）上元素都不等于val,此时j从0开始
     * 这时我们会想到双指针，通过定义一个指针j用来保存不等于val的，另一个指针不断向后循环，把不等于val的和当前j替换。直到循环结束开区间[0,j)。到数组最后长度应该是j
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=val){
                nums[j]=nums[i];
                j++;
            }
        }
        return j;
    }

    /**
     * 力扣80. 有序数组nums，删除重复出现的元素 使每个元素 最多出现两次 ，返回删除后数组的新长度。
     * 保证从[0,j]上元素最多出现两次
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int j=1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i]!=nums[j-1]){  //因为最多出现两次，所以和上一个比较就行
                j++;
                nums[j]=nums[i];
            }
        }
        return j+1;
    }


    /**
     *
     * 总结: 先定义好循环不变量，确定循环开始的时候也要满足区间范围，在每次循环中维持这个循环不变量，这样在循环终止时循环不变量的性质成立
     * 对于以上的题型，题目告诉我们不需要考虑超出长度后的元素（重点），因此我们可以直接赋值即可，根据题意要求将符合条件的元素保留下来
     * 开区间[left,right) ,长度为right-left， 闭区间[left,right]，长度为right-left+1
     */

}



