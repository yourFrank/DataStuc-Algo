package leetcode;

import java.util.Arrays;

/** H小时内吃掉所有香蕉的最小速度K。
 * 根据题意，我们可以知道当吃香蕉速度k和时间H成单调性，速度越大，花费的时间越少
 * 因此我们可以根据速度求出时间，和H作比较，找出速度的最小值
 *
 */
public class LC875_minEatingSpeed {

    public int minEatingSpeed(int[] piles, int h) {
        int l=1; //最小速度
//        int r= Arrays.stream(piles).max().getAsInt();//获取数组中最大值，当K的最大值
        int r= 1_000_000_000; //这里也可以直接用题目给的条件，直接选取最大值 1 <= piles[i] <= 10^9
        while (l<r){
            int mid=l+(r-l)/2;
            if (eatingTime(piles,mid)<=h){ //如果小于等于h，保留当前元素然后去找更小的速度K
                r=mid;
            }else {
                l=mid+1;
            }
        }
        return l;

    }
        //根据当前的速度求出所需要的时间
    private int eatingTime(int[] piles,int mid) {
        int count=0;
        for (int pile : piles) {
            int time=pile/mid;
            count+= pile%mid==0?time:time+1;
        }
        return count;
    }
}
