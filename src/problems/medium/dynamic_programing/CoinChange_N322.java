package problems.medium.dynamic_programing;

import utils.Logger;
import utils.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * author: sunxuedian
 * create: 2020/3/9
 */
public class CoinChange_N322 {

    private static Logger logger = LoggerFactory.getDefalutLogger();

    public static void main(String[] args){
        CoinChange_N322 n322 = new CoinChange_N322();
        n322.test(new int[]{1, 5, 2}, 11, 3);
        n322.test(new int[]{2}, 3, -1);
//        n322.test(new int[]{186,419,83,408}, 6249, 20);
    }

    public void test(int[] coins, int amount, int result){
        logger.println("amount = " + amount + ", coins 如下");
        logger.printArray(coins);
        int c = coinChange(coins, amount);
        if (c == result){
            logger.println("用例通过");
        }else {
            logger.println("用例不通过，输出：" + c + ", 期望：" + result);
        }
        logger.println();
    }

    /**
     * 使用动态规划，自下向上，虽然通过，但是耗时是O(n*n*amount) 空间是O(n*amount) 不理想
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return -1;
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 1; i <= coins.length; i ++){
            for (int j = 1; j <= amount; j ++){
                int dpij = dp[i-1][j];
                for (int k = j / coins[i - 1]; k >= 1; k --){
                    // 当这个下标的dp值为0时，判断是不是是第一列，如果是，则是合理的，如果不是则这个组合不成立
                    if (dp[i-1][j- k * coins[i-1]] == 0){
                        if (j - k * coins[i-1] == 0){
//                                if (dpij > (dp[i-1][j-k*coins[i-1]] + k)){
//                                    dpij = dp[i-1][j-k*coins[i-1]] + k;
//                                }
                            // 即如果 dpij > k, 那dpij = k
                            if (dpij == 0 || dpij > k){
                                dpij = k;
                            }
                        }
                    }else {
                        if (dpij == 0 || dpij > (dp[i-1][j-k*coins[i-1]] + k)){
                            dpij = dp[i-1][j-k*coins[i-1]] + k;
                        }
                    }
                }
                dp[i][j] = dpij;
            }
        }
        logger.printArray(dp);
        return dp[coins.length][amount] != 0 ? dp[coins.length][amount] : -1;
    }

    /**
     * 最佳的做法应该是 递归+部分数据记忆存储
     * @param coins
     * @param amount
     * @param map
     * @return
     */
    private int fun(int[] coins, int amount, Map<Integer, Integer> map){
        if (amount == 0){
            return 0;
        }
        int lastMinValue = map.getOrDefault(amount, -1);
        for (int coin: coins){
            int temp = -1;
            if (amount >= coin){
                int i = amount - coin;
                if (map.containsKey(i) && map.get(i) == -1){
                    // 说明余数无法划分，表示无效
                    temp = -1;
                }else {
                    int fr = fun(coins, i, map);
                    if (fr != -1){
                        temp = 1 + fr;
                    }else {
                        temp = -1;
                        map.put(i, -1);
                    }
                }
            }
            if (lastMinValue == -1){
                if (temp != -1){
                    lastMinValue = temp;
                }
            }else {
                if (temp != -1 && temp < lastMinValue){
                    lastMinValue = temp;
                }
            }
        }
        return lastMinValue;
    }


}
