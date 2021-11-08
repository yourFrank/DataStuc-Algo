/**
 * 二分查找，要求数组有序的情况
 */
public class BinarySearch01 {

    private BinarySearch01(){};

    public <E extends Comparable<E>> int searchR(E[] nums,E k){
        return searchR(nums,0, nums.length-1,k );
    }

    //1、递归实现二分查找 [l,r]区间
    public static  <E extends Comparable<E>> int searchR(E[] nums,int l,int r,E k){
        if (l>r){
            return -1;
        }
        int mid=l+(r-l)/2;
        if (nums[mid].equals(k)){
            return mid;
        }else if (nums[mid].compareTo(k)>0){
           return searchR(nums,l,mid-1,k);
        }else {
            return searchR(nums,mid+1,r,k);
        }
    }

    // 2、非递归实现二分查找
    public static <E extends Comparable<E>> int search(E[] nums,E k){
        int l=0;
        int r=nums.length-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            if (nums[mid].compareTo(k)==0){
                return mid;
            }else if (nums[mid].compareTo(k)>0){
                r=mid-1;
            }else {
                l=mid+1;
            }
        }
        return -1;

    }

}
