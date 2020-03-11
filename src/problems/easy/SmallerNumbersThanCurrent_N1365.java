package problems.easy;


import utils.Logger;
import utils.LoggerFactory;

/**
 * LeetCode_1365
  有多少小于当前数字的数字
     给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

     换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

     以数组形式返回答案。

      

     示例 1：

     输入：nums = [8,1,2,2,3]
     输出：[4,0,1,1,3]
     解释：
     对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     对于 nums[1]=1 不存在比它小的数字。
     对于 nums[2]=2 存在一个比它小的数字：（1）。
     对于 nums[3]=2 存在一个比它小的数字：（1）。
     对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     示例 2：

     输入：nums = [6,5,4,8]
     输出：[2,1,0,3]
     示例 3：

     输入：nums = [7,7,7,7]
     输出：[0,0,0,0]
      

     提示：

     2 <= nums.length <= 500
     0 <= nums[i] <= 100

 * author: sunxuedian
 * create: 2020/3/5
 */
public class SmallerNumbersThanCurrent_N1365 {

    public static void main(String[] args){
        SmallerNumbersThanCurrent_N1365 smallerNumbersThanCurrent_n1365 = new SmallerNumbersThanCurrent_N1365();
        int[] nums = new int[]{7,7,7,7};
        int[] nums2 = new int[]{6,5,4,8};
        Logger logger = LoggerFactory.getDefalutLogger();
        logger.printArray(nums);
        logger.printArray(smallerNumbersThanCurrent_n1365.smallerNumbersThanCurrent(nums));
        logger.printArray(nums2);
        logger.printArray(smallerNumbersThanCurrent_n1365.smallerNumbersThanCurrent(nums2));
    }

    /**
     * 利用提示，数字的大小有范围，0-100，那可以用数组来存储每个数字出现的次数，保存到tA数组中，
     * 然后再用另外一个数组，tB，长度和tA一样，来求出每个比当前index小的数字有多少个，可以总结出来，tB[0]=0; tB[i] = tB[i-1] + tA[i-1] (i >=1时）
     * 最后再查表取出答案，从tB中查
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] tA = new int[101];
        for (int num : nums) {
            tA[num]++;
        }
        int[] tB = new int[101];
        for (int i = 1; i < 101; i ++){
            tB[i] = tB[i-1] + tA[i-1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i ++){
            result[i] = tB[nums[i]];
        }
        return result;
    }
}
