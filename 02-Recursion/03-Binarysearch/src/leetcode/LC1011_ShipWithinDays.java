package leetcode;

import java.util.Arrays;

/**
 * D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * D天和运载能力之间成单调性，当运载能力越高，所需要的天数就越少,因此可以使用二分查找
 */
public class LC1011_ShipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt(), r = Arrays.stream(weights).sum();  //确定查找的区间，最小值应该是重物的最小值，最大值是所有的和
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (spentTime(mid, weights, days)) { //如果花费的时间比要求的时间少或相等的时候，保留当前元素继续选小一点的载重
                r = mid;
            } else {
                l = mid + 1;
            }

        }
        return l;
    }

    //根据示例，看该如何操作这个天数和包裹量，而不是凭空想
    private boolean spentTime(int mid, int[] weights, int days) {
        // need 为需要运送的天数 ，因为无论如何都要1天
        // cur 为当前这一天已经运送的包裹重量之和
        int need = 1, cur = 0;

        for (int weight : weights) {
            if (cur + weight > mid) {
                ++need;
                cur = 0;
            }
            cur += weight;
        }

        return need <= days;
    }
}
