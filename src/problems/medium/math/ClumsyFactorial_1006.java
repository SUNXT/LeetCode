package problems.medium.math;

import java.util.Deque;
import java.util.LinkedList;

/**
 * author: sunxuedian
 * create: 2021/4/2
 *
 * 1006 笨阶乘
 *
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 *
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 *
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 *
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 *
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：4
 * 输出：7
 * 解释：7 = 4 * 3 / 2 + 1
 * 示例 2：
 *
 * 输入：10
 * 输出：12
 * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 *  
 *
 * 提示：
 *
 * 1 <= N <= 10000
 * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clumsy-factorial
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ClumsyFactorial_1006 {

    public static void main(String[] args){

    }

    /**
     * 按照 * / + - 的顺序
     * @param n: 1 <= N <= 10000
     * @return
     */
    public int clumsy(int n) {
        int result  = 0;
        // 用于标记下一步需要的运算
        // -1 表示初始操作，用'+='操作
        // 0 表示后续的 '+= 操作
        // 1 表示后续的 '-=' 操作
        int i = -1;
        while (n > 0){
            // 初始操作
            if (i == -1){
                // 当n > 2时，n-2>0，可使用 n * (n - 1) / (n - 2)，然后下一步的操作为 +，标记 i = 0，同时，n = n - 3
                if (n > 2){
                    result += (n * (n - 1) / (n - 2));
                    n -= 3;
                    i = 0;
                }else if (n == 2){
                    // 此时，循环已经结束，n = 2，初始的n=2，此时计算结果为 2 * 1
                    result += (n * (n - 1));
                    break;
                }else {
                    // 此时，循环结束，n = 1，初始n=1，计算结果为 1
                    result += n;
                    break;
                }
            }else if (i == 0){
                // 直接 += n
                result += n;
                n -= 1;
                i = 1;
            }else {
                if (n > 2){
                    result -= (n * (n - 1) / (n - 2));
                    n -= 3;
                    i = 0;
                }else if (n == 2){
                    // 此时，循环已经结束，n = 2
                    result -= (n * (n - 1));
                    break;
                }else {
                    // 此时，循环结束，n = 1
                    result -= n;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 官方题解一，使用栈
     * 根据题意，「笨阶乘」没有显式括号，运算优先级是先「乘除」后「加减」。我们可以从 NN 开始，枚举 N - 1N−1、N-2N−2 直到 11 ，枚举这些数的时候，认为它们之前的操作符按照「乘」「除」「加」「减」交替进行。
     *
     * 出现乘法、除法的时候可以把栈顶元素取出，与当前的 NN 进行乘法运算、除法运算（除法运算需要注意先后顺序），并将运算结果重新压入栈中；
     *
     * 出现加法、减法的时候，把减法视为加上一个数的相反数，然后压入栈，等待以后遇见「乘」「除」法的时候取出。
     *
     * 最后将栈中元素累加即为答案。由于加法运算交换律成立，可以将栈里的元素依次出栈相加。
     *
     * @param N
     * @return
     */
    public int clumsy1(int N) {
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(N);
        N--;

        int index = 0; // 用于控制乘、除、加、减
        while (N > 0) {
            if (index % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
            index++;
            N--;
        }

        // 把栈中所有的数字依次弹出求和
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * 使用数学分析，可以得出简单的算法，官方题解二
     * @param N
     * @return
     */
    public int clumsy2(int N) {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }

        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }
}
