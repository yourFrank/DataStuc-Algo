//    *             *
//   *            *           (1)   (2)       (3)
//  *      =>           *      r   l          m
// *                  *      m        r    l
//*                 *      l        m            r
//
//
//寻找旋转数组中的最小元素
//在不断缩小区间的过程中只会出现上面三种位置的变化 ,1) l<r m<r r=m  2)l>r m<r r=m 3)l>r  m>r l=m+1
//综上可以直接判断mid和r的关系 , 当mid<r r=mid  否则l=mid+1


class Solution {
    public int findMin(int[] nums) {
        int l=0;

        int r=nums.length-1;

        while (l<r){
            int mid=l+(r-l)/2;
            if (nums[mid]<nums[r]){
                r=mid;  //当前元素可能是最小值
            }else {
                l=mid+1;
            }
        }
        return nums[l];
    }
}


//寻找旋转数组（2）

// [2,2,2,0,1]  3313   3333313   10 1 10 10
// 1) l==r : m>r r=m / m=r ? / m<r r=m
// 2) l<r :  m<r r=m / m=r r=m
// 3) l>r :  m>r l=m+1 / m=r r=m

class Solution2{
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (pivot==high&&low==high){

            }
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}
