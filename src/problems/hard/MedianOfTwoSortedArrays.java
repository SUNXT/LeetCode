package problems.hard;

import utils.Logger;
import utils.LoggerFactory;

/**
 * LeetCode 4
 * 求两个已排好序的数组的中位数，实际上就是合并两个以排好序的数组
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    private Logger logger = LoggerFactory.getLogger(this);

    public static void main(String[] args){
        MedianOfTwoSortedArrays test = new MedianOfTwoSortedArrays();
        test.test();
    }

    public void test(){
        int[] nums1 = new int[]{1,4,5,6};
        int[] nums2 = new int[]{1,2,6,9,11,18};
        printArray(nums1);
        printArray(nums2);
        printArray(mergerTwoSortedArrays(nums1, nums2));
        logger.d("中位数：" + findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] resultNums = mergerTwoSortedArrays(nums1, nums2);
        if (resultNums.length % 2 == 0){
            return ( resultNums[(resultNums.length / 2) - 1] + resultNums[resultNums.length / 2]) / 2.0d;
        }else {
            return resultNums[resultNums.length / 2];
        }
    }

    private void printArray(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Integer i: arr){
            sb.append(i).append(", ");
        }
        sb.append("]");
        logger.d(sb.toString());
    }

    private int[] mergerTwoSortedArrays(int[] nums1, int[] nums2){
        int[] nums3 = new int[nums1.length + nums2.length];
        int p = 0, q = 0, curr = 0;
        while (p < nums1.length && q < nums2.length){
            if (nums1[p] < nums2[q]){
                nums3[curr ++] = nums1[p ++];
            }else {
                nums3[curr ++] = nums2[q ++];
            }
        }
        // 看哪个数组不为空，将剩余的部分赋值到nums3中
        while (p < nums1.length){
            nums3[curr ++] = nums1[p ++];
        }
        while (q < nums2.length){
            nums3[curr ++] = nums2[q ++];
        }
        return nums3;
    }
}
