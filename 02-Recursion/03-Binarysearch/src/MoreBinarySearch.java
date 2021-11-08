/**
 * 二分查找变种
 */
public class MoreBinarySearch {

    //1、upper:查找大于target的最小值  [l,r]中
    public static <E extends Comparable<E>> int upper(E[] nums, E target) {
        int l = 0;
        int r = nums.length; //此时我们将r定义到len的位置，为了防止不存在的和最后一个索引重复
        while (l < r) {  //因为这个问题一定有解，并且当l==r的时候这个解一定使我们的解，所以循环条件是l<r
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) >= 0) {  //满足，但是不一定是最小的，因此继续往左寻找，但是这个值要保留
                r = mid;
            } else {
                l = mid + 1;  //mid<=target , 直接向右寻找
            }
        }
        return l; //最后return l/r  即可

    }

    // 使用求解 >= target 的最小值索引的思路，实现 search二分查找
    // 在有序数组 data 中判断 target 是否存在，存在则返回相应索引，不存在则返回-1
    public static <E extends Comparable<E>> int search2(E[] nums, E target) {
        int l = 0;
        int r = nums.length; //此时我们将r定义到len的位置，为了防止不存在的和最后一个索引重复
        while (l < r) {  //因为这个问题一定有解，并且当l==r的时候这个解一定使我们的解，所以循环条件是l<r
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) >= 0) {  //满足，但是不一定是最小的，因此继续往左寻找，但是这个值要保留
                r = mid;
            } else {
                l = mid + 1;  //mid<=target , 直接向右寻找
            }
        }

        if (l < nums.length && nums[l].compareTo(target) == 0)  //如果最小值等于target，则返回
            return l;
        return -1;

    }

    //2、ceil: 如果数组中存在元素，返回最大索引，如果不存在元素，返回upper。ex: ceil(5.0)=5 ceil(5.5)=6
    // 我们可以先查找upper,upper-1如果等于该元素（证明该元素存在）则返回，否则返回upper
    public static <E extends Comparable<E>> int upper_ceil(E[] nums, E target) {
        int upper = upper(nums, target);
        if (upper - 1 >= 0 && nums[upper - 1].compareTo(target) == 0) {
            return upper - 1;
        }
        return upper;
    }

    //3、lower_ceil: 如果数组中存在元素，返回最小索引，如果不存在元素，返回upper
    // 我们可以先查找upper,upper-1如果等于该元素（证明该元素存在）则返回，否则返回upper

//    public static <E extends Comparable<E>> int lower_ceil(E[] nums, E target) {
//        int u = upper(nums, target);
//        while (u - 1 >= 0 && nums[u - 1].compareTo(target) == 0) {
//            u--;
//        }
//        return u;
//
//    }

    public static <E extends Comparable<E>> int lower_ceil(E[] data, E target) {
        int l = 0, r = data.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) < 0) l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    //4、小于target的最大值 [l,r]
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = -1, r = data.length - 1;  //此时L从-1开始，当数组中不存在这个值时返回-1这个索引
        while (l < r) {
            int mid = l + (r - l + 1) / 2;  //如果这样选，剩两个数时每次mid都会一直选择左边的，下面l=mid就会死循环
            if (data[mid].compareTo(target) < 0) l = mid;  //这个数字也要保留
            else
                r = mid - 1;
        }
        return l;
    }

    // 省略 upper_floor, lower_floor...


    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        for (int i = 0; i <= 6; i++) {
            System.out.print(MoreBinarySearch.lower_ceil(arr, i) + " ");
        }
        System.out.println();
        System.out.println(MoreBinarySearch.search2(arr,6));

    }
}
