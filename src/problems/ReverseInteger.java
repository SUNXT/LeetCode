package problems;

import utils.Logger;
import utils.LoggerFactory;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    private Logger logger = LoggerFactory.getLogger(this);

    public static void main(String[] args){
        ReverseInteger test = new ReverseInteger();
        Logger logger = LoggerFactory.getLogger(test);
        int input = 1534236469;
        logger.d("input: " + input);
        logger.d("output: " + test.reverse(input));
        int max = (int) Math.pow(2, 30);
        logger.d("" + max);
    }

    /**
     * 做这套题需要考虑越界的情况，如果反转后，超出了int的最大数值，则需要判断
     * @param x
     * @return
     */
    public int reverse(int x) {
        int absX = Math.abs(x);
        long result = 0;
        while (absX > 0){
            result = result * 10 + absX % 10;
            absX /= 10;
            logger.d("" + result);
        }
        if (result > Integer.MAX_VALUE){
            return 0;
        }
        if (x >= 0){
            return (int) result;
        }else {
            return (int) -result;
        }
    }
}

