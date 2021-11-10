
//寻找旋转数组中的最小元素
//先找好单调区间，这道题单调区间是[min,r],从[min,r]肯定是单调递增的,[l,min]。因此我们可以判断mid和r的关系来取舍一半的区间
//min左侧的元素肯定大于min，min右侧的元素肯定小于min
class Solution {
    public int findMin(int[] nums) {
        int l=0;

        int r=nums.length-1;

        while (l<r){
            int mid=l+(r-l)/2;
            if (nums[mid]<nums[r]){
                r=mid;
            }else {
                l=mid-1;
            }

        }
        return nums[l];
    }

}


//寻找旋转数组（2）
//这时候[min,r]可能是单调递增的区间也可能存在m=r的情况
//当l=mid=r 的时候，最小值可能在m的左侧，也可能在m的右侧
class Solution2{
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {

            int mid=l+(r-l)/2;
            if (nums[mid]<nums[r]){ //右侧是单调区间
                r=mid; //保留当前元素
            }else if (nums[mid]>nums[r]){
                l=mid+1;
            }else {
                r--;
            }
        }
        return nums[l];
    }

}
//先找好单调区间，随便找一个mid 则肯定有一侧是单调的[l,mid]或者[mid,r]
//并且左侧上升区间都大于l，右侧上升区间都小于l，我们可以
class Solution3{
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while (l<r){
            int mid=l+(r-l)/2;
            if (nums[mid]==target){
                return mid;
            }
            else if (nums[mid]>nums[l]){ //此时nums[mid]在上升区间,则nums[l，mid]是单调递增的
                if (target>=nums[l]&&target<=nums[mid]){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }else { //此时在[mid,r]的区间是单调递增的，判断target是否在这个区间
                if (target<=nums[r]&&target>nums[mid]){
                    l=mid+1;
                }else {
                    r=mid-1;
                }
            }
        }

        return nums[l]==target?l:-1;
    }




    public static void main(String[] args) {
        Solution3 solution=new Solution3();
        int nums[]={3,1};
        int search = solution.search(nums, 1);
        System.out.println(search);
    }

}


class Solution4{
    public int peakIndexInMountainArray(int[] arr) {
        int l=0;
        int r=arr.length-1;
        while (l<r){
            int mid=l+(r-l)/2;
            if (arr[mid]>arr[mid+1]){
                l=mid+1;
            }else {
                r=mid;
            }
        }
        return l;
    }

}

class Solution5{

    //    time = [1,2,3,3], m = 2
//    T越大，m越小。 m和T成单调性
    public int minTime(int[] time, int m) {
        int l=0;
        int r= Arrays.stream(time).sum();
        while (l<r){
            int mid=l+(r-l)/2;
            if (spentTime(mid,time,m)){
                r=mid;
            }else {
                l=mid+1;
            }

        }
        return l;
    }
    //[1,2,3,3]  2
    private boolean spentTime(int T, int[] time,int m) {
        int len=time.length-m;
        int sum=0;
        int left=Math.max(0,len-m);
        for (int i=len-1;i>=left;i--){
            sum+= time[i];
        }
        return T>=sum;

    }

    public static void main(String[] args) {
        Solution5 solution=new Solution5();
        int time[]={999,999,999,};

        solution.minTime(time,2);
    }
}
