package problems.medium;

import utils.Logger;
import utils.LoggerFactory;

import java.util.Stack;

/**
 * 该题不是LeetCode题，但是也是一道算法题
 *
 * 题目：输出数组每一位右边的比它大的第一个元素，如果没有比它大的数字，则输入 -1
 * 例如：
 * 输入：4 3 10 8 9 7 3 18 10
 * 输出：10 10 18 9 18 18 18 -1 -1
 *
 * 分析：两种解法
 * 解法一：
 * 暴力遍历，O(n^2)速度，慢
 *
 * 解法二：
 * 用一个新的数组b[]来保存输出的结果，a[]表示输入的数组
 * 利用栈结构，可以达到O(n)速度，只需要遍历一次数组，具体需要用到栈进行配合
 * 栈中存的是数组的下标，然后，从数组第一位开始入栈，然后运算下面的算法，
 * （1）如果，接下来的数字a[i]比栈顶的数字小的话，进栈，i++，进行下一个比较，如果a[i]大于栈顶元素，栈顶出栈，这时候，b[stack.peek()] = a[i]，
 *      如果栈不为空，继续出栈，然后进行赋值b[stack.peek()] = a[i]，因为可以知道，这时候这个a[i]是比栈中的元素都大的第一个数字。
 *
 * （2）如果，此刻栈为空，a[i]要进行进栈操作，直到遍历完a[]，需要把栈中的元素出栈，如果没有比栈中元素还大的数字，赋值-1。
 *
 */
public class FindFirstRightBigNum {

    public static void main(String[] args){
        FindFirstRightBigNum test = new FindFirstRightBigNum();
        test.find(new int[]{4, 3, 10, 8, 9, 7, 3, 18, 10});
        test.find(new int[]{8, 2, 5, 4, 3, 9, 7, 2, 5});
        test.find(new int[]{1, 5, 3, 6, 4, 8, 9, 10});
    }

    public void find(int[] array){
        Logger logger = LoggerFactory.getLogger(this);
        logger.println("原数组：");
        logger.printArray(array);
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i ++){
            // 如果栈为空，直接将下标进行入栈
            if (stack.empty()){
                stack.push(i);
                continue;
            }
            // 进行栈顶比较，如果栈顶元素比array[i]还大的话，直接进栈，跳出循环
            while (!stack.empty()){
                if (array[stack.peek()] < array[i]){
                    result[stack.pop()] = array[i];
                }else {
                    stack.push(i);
                    break;
                }
            }
            // 跳出循环还有一种可能就是，如果，栈为空，需要把i加到栈中，如果是因为栈顶元素比array[i]大的，不需要push(i)
            if (stack.empty()){
                stack.push(i);
            }
        }
        while (!stack.empty()){
            result[stack.pop()] = -1;
        }
        logger.println("结果：");
        logger.printArray(result);
    }

}
