package problems.easy;

import utils.Logger;
import utils.LoggerFactory;

/**
 * author: sunxuedian
 * create: 2020/3/22
 *
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class MaxProfit_N121 {

    private static Logger logger = LoggerFactory.getDefalutLogger();

    public static void main(String[] args){
        MaxProfit_N121 maxProfit_n121 = new MaxProfit_N121();
        maxProfit_n121.test(new int[]{7,1}, 0);
        maxProfit_n121.test(new int[]{7,1,5}, 4);
        maxProfit_n121.test(new int[]{7,1,5,3}, 4);
        maxProfit_n121.test(new int[]{7,1,5,3,6}, 5);
        maxProfit_n121.test(new int[]{7,1,5,3,6,4}, 5);
    }

    public void test(int[] prices, int result){
        logger.printArray(prices);
        int max = maxProfit(prices);
        logger.println("期望：" + result + "，计算结果：" + max + ", 通过 = " + (max == result));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int max = 0;
        // 记录当前最低和最高的数字
        int low = 0;
        for (int i = 1; i < prices.length; i ++){
            if (prices[i] >= prices[low]){
                if (prices[i] - prices[low] > max){
                    max = prices[i] - prices[low];
                }
            }else {
                low = i;
            }
        }
        return max;
    }
}
