package problems.easy;

import utils.Logger;
import utils.LoggerFactory;

/**
 * author: sunxuedian
 * create: 2020/3/22
 *
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class MaxSubArray_N53 {

    private static Logger logger = LoggerFactory.getDefalutLogger();

    public static void main(String[] args){
        MaxSubArray_N53 n53 = new MaxSubArray_N53();
//        n53.test(new int[]{1,2}, 3);
//        n53.test(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6);
//        n53.test(new int[]{8,-19}, 8);
//        n53.test(new int[]{8,-19,5}, 8);
//        n53.test(new int[]{8,-19,5,-4,20}, 21);
//        n53.test(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6);
        n53.test(new int[]{1,2,-1,-2,2,1,-2,1,4,-5,4}, 6);
    }

    public void test(int[] prices, int result){
        logger.printArray(prices);
        int max = maxSubArray(prices);
        logger.println("期望：" + result + "，计算结果：" + max + ", 通过 = " + (max == result));
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max = nums[0];
        int temp = max;
        int p = 0, q = 0;
        for (int i = 1; i < nums.length; i ++){
            // 先判断，如果当前这个数值比max还大，那直接更新max
            if (nums[i] > max){
                max = nums[i];
            }
            // 接下来判断
            // 判断这个值是否大于0
            if (nums[i] > 0){
                // 判断前面累加的temp是否小于0，如果小于0，则更新temp，因为temp都已经小于0了，可以废弃掉前面的子集
                if (temp <= 0){
                    q = p = i;
                    temp = nums[i];
                }else {
                    // 如果大于0的话，则保留前面的子集，然后判断temp+当前数字，是否大于max，如果是，则更新max
                    if (temp + nums[i] > max){
                        max = temp + nums[i];
                        q = i;
                    }
                    // 此时，temp+=nums[i]
                    temp += nums[i];
                }
            }else {
                // nums[i] 小于0的情况，temp加上去
                temp += nums[i];
            }
        }
        int[] subArray = new int[q - p + 1];
        System.arraycopy(nums, p, subArray, 0, subArray.length);
        logger.printArray(subArray);
        return max;
    }

}
