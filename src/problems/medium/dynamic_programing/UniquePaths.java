package problems.medium.dynamic_programing;

import utils.Logger;
import utils.LoggerFactory;

/**
 * author: sunxuedian
 * create: 2020/2/29
 *
 * LeetCode 62 - 不同路径
 *
 * 类别：动态规划、数组
 * 题目：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * 说明：m 和 n 的值均不超过 100。

    示例 1:

    输入: m = 3, n = 2
    输出: 3
    解释:
    从左上角开始，总共有 3 条路径可以到达右下角。
    1. 向右 -> 向右 -> 向下
    2. 向右 -> 向下 -> 向右
    3. 向下 -> 向右 -> 向右

    示例 2:

    输入: m = 7, n = 3
    输出: 28

 */
public class UniquePaths {

    private static Logger logger = LoggerFactory.getLogger(UniquePaths.class);

    public static void main(String[] args){
        UniquePaths uniquePaths = new UniquePaths();
        logger.println("m = 7, n = 3; count = " + uniquePaths.uniquePaths(7, 3));
    }

    /**
     * 以下 0 < i <= m, 0 < j <= n
     * 不同路径
     * 思路：使用动态规划的方法，用数组记录中间的计算数值
     * 先总结出规律，从A到B点，只能往右和往下，也就是不能往回走，用S(i,j)来表示从A(0,0)点开始到B(i,j)点的所有独特的路径总数
     * 然后
     * 1、分析发现，S(1,j) 和 S(i,1) 都是等于1，只有一个方向，即：当i=1或j=1的时候，S(i,j)=1
     * 2、分析发现，到B(i,j)的路径可以分为到B(i,j-1)的所有路径加上到B(i-1,j)的所有路径，即：S(i,j)=S(i,j-1)+S(i-1,j)，当i，j都大于1的时候
     * 3、分析发现，S(i,j)=S(j,i)
     * 所以，表达式为
     * S(i,j) = 1                   (i=1或j=1）
     * S(i,j) = S(i,j-1)+S(i-1,j)   (i>1且j>1)
     * S(i,j) = S(j,i)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 如果m和n有一个是1，直接返回1
        if (m == 1 || n == 1){
            return 1;
        }
        // 使用数组将中间计算的步骤保存起来
        int[][] sArray = new int[m][n];
        // 初始化数组，当sArray[i,j]中i和j为0的初始化为1
        for (int i = 0; i < m; i ++){
            sArray[i][0] = 1;
        }
        for (int j = 0; j < n; j ++){
            sArray[0][j] = 1;
        }
        // 逐步把数组填满，遍历数组，从1到m，1到n
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                // S(i,j) = S(i,j-1)+S(i-1,j)
                sArray[i][j] = sArray[i - 1][j] + sArray[i][j - 1];
            }
        }
        return sArray[m-1][n-1];
    }

}
