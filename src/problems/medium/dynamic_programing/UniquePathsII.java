package problems.medium.dynamic_programing;

/**
 * author: sunxuedian
 * create: 2020/2/29
 *
 * LeetCode 63题
 *
 * 题目：
 *
     一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

     机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

     现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

     网格中的障碍物和空位置分别用 1 和 0 来表示。

     说明：m 和 n 的值均不超过 100。

     示例 1:

     输入:
     [
       [0,0,0],
       [0,1,0],
       [0,0,0]
     ]
     输出: 2
     解释:
     3x3 网格的正中间有一个障碍物。
     从左上角到右下角一共有 2 条不同的路径：
     1. 向右 -> 向右 -> 向下 -> 向下
     2. 向下 -> 向下 -> 向右 -> 向右

 */
public class UniquePathsII {

    /**
     * 效率为 1ms, 写法不是最优
     * 分析
     * 这道题加到了障碍，但是思路和"求不同路径"一样
     *
     * 以下 0 < i <= m, 0 < j <= n
     * 思路：使用动态规划的方法，用数组记录中间的计算数值
     * 现在路径矩阵用C(i,j)表示 如果C(i,j)=1 则为路障，如果C(i,j)=0 表示可以走
     * 先总结出规律，从A到B点，只能往右和往下，也就是不能往回走，但是，如果矩阵中，有障碍的话，不能走，用S(i,j)来表示从A(0,0)点开始到B(i,j)点的所有独特的路径总数
     * 然后
     *
     * 1、当i = 1时，S(1,j) = S(1,j-1) 如果C(1,j-1) = 1, 则S(i-1,0)=0，表示不通，没有可达路径；当j = 1时，同样的道理
     *
     * 2、分析发现，到B(i,j)的路径可以分为到B(i,j-1)的所有路径加上到B(i-1,j)的所有路径，但是需要看是否有路障
     * 即：当i，j都大于1的时候
     * S(i,j)=S(i,j-1)+S(i-1,j) 当C(i,j-1)=0 和 C(i-1,j)=0
     * S(i,j)=S(i-1,j) 当 C(i,j-1) = 1 且 C(i-1,j) = 0 时
     * S(i,j)=S(i,j - 1) 当 C(i,j-1) = 0 且 C(i-1,j) = 1 时
     * S(i,j)= 0 当 C(i,j-1) = 1 且 C(i-1,j) = 1 时
     *
     * **
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 如果第一个点就是障碍物的话，则直接为0
        if (obstacleGrid[0][0] == 1) return 0;
        // 如果最后一个点也是障碍物的话，也是直接输出0
        if (obstacleGrid[m-1][n-1] == 1) return 0;
        // 用一个二维数组sArray[i,j] 来记录到达第i+1，j+1个点的不同路径数
        int [][] sArray = new int[m][n];
        // 先处理i = 0和j=0的情况
        // 第一个点初始化为1
        sArray[0][0] = 1;
        // 处理第一列
        for (int j = 1; j < m; j ++){
            if (obstacleGrid[j-1][0] == 0 && sArray[j-1][0] == 1){
                sArray[j][0] = 1;
            }else {
                // 如果中间已经有障碍，则后面都是0
                break;
            }
        }
        // 处理第一行
        for (int i = 1; i < n; i ++){
            if (obstacleGrid[0][i-1] == 0 && sArray[0][i-1] == 1){
                sArray[0][i] = 1;
            }else {
                // 如果中间已经有障碍，则后面都是0
                break;
            }
        }
        // 处理当i > 0 且 j > 0的情况
//        S(i,j)=S(i,j-1)+S(i-1,j) 当C(i,j-1)=0 和 C(i-1,j)=0
//        S(i,j)=S(i-1,j) 当 C(i,j-1) = 1 且 C(i-1,j) = 0 时
//        S(i,j)=S(i,j - 1) 当 C(i,j-1) = 0 且 C(i-1,j) = 1 时
//        S(i,j)= 0 当 C(i,j-1) = 1 且 C(i-1,j) = 1 时
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                if (obstacleGrid[i][j-1] == 0){
                    if (obstacleGrid[i-1][j] == 0){
                        sArray[i][j] = sArray[i][j-1] + sArray[i-1][j];
                    }else {
                        sArray[i][j] = sArray[i][j-1];
                    }
                }else {
                    if (obstacleGrid[i-1][j] == 0){
                        sArray[i][j] = sArray[i-1][j];
                    }else {
                        sArray[i][j] = 0;
                    }
                }
            }
        }
        return sArray[m-1][n-1];
    }


    /**
     * 最优算法
     * 以下 0 < i <= m, 0 < j <= n
     * 思路：使用动态规划的方法，用数组记录中间的计算数值
     * 现在路径矩阵用C(i,j)表示 如果C(i,j)=1 则为路障，如果C(i,j)=0 表示可以走
     * 先总结出规律，从A到B点，只能往右和往下，也就是不能往回走，但是，如果矩阵中，有障碍的话，不能走，用S(i,j)来表示从A(0,0)点开始到B(i,j)点的所有独特的路径总数
     *
     * 分析：初始化第一行和第一列和上面一样，然后
     * 如果C(i,j)=1的话，那么 S(i,j)=0
     * 如果C(i,j)=0的话，和普通求不同路径一样，S(i,j)=S(i,j-1)+S(i-1,j)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 用一个二维数组sArray[i,j] 来记录到达第i+1，j+1个点的不同路径数
        int [][] sArray = new int[m][n];
        // 处理第一列
        for (int j = 0; j < m; j ++){
            if (obstacleGrid[j][0] == 0){
                sArray[j][0] = 1;
            }else {
                // 如果中间已经有障碍，则后面都是0
                break;
            }
        }
        // 处理第一行
        for (int i = 0; i < n; i ++){
            if (obstacleGrid[0][i] == 0){
                sArray[0][i] = 1;
            }else {
                // 如果中间已经有障碍，则后面都是0
                break;
            }
        }
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                if (obstacleGrid[i][j] == 1){
                    sArray[i][j] = 0;
                }else {
                    sArray[i][j] = sArray[i][j-1] + sArray[i-1][j];
                }
            }
        }
        return sArray[m-1][n-1];
    }
}
