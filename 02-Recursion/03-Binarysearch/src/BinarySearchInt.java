public class BinarySearchInt {


    public int searchR(int[] nums, int target) {
        return searchR(nums,0, nums.length-1,target );
    }


    public  int searchR(int[] nums,int l,int r,int target){
        if (l>r){
            return -1;
        }
        int mid=l+(r-l)/2;
        if (nums[mid]==target){
            return mid;
        }else if (nums[mid]>target){
            return searchR(nums,l,mid-1,target);
        }else {
           return  searchR(nums,mid+1,r,target);
        }

    }

//    1、循环不变量在[l,r] 上非递归查找
    public  int search(int[] nums,int target){
        int l=0;
        int r=nums.length-1;
        while (l<=r){
            int mid=l+(r-l)/2;//每次求循环都要求mid,要放入循环内部
            if (nums[mid]==0){
                return mid;
            }else if (nums[mid]>target){
                r=mid-1;
            }else {
                l=mid+1;
            }
        }
        return -1;

    }

    //    2、循环不变量在[l,r） 上非递归查找
    public  int search2(int[] nums,int target){
        int l=0;
        int r=nums.length;
        while (l<r){
            int mid=l+(r-l)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]>target){
                r=mid;
            }else {
                l=mid+1;
            }
        }
        return -1;

    }
}
