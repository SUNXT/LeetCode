package problems;

import utils.Logger;
import utils.LoggerFactory;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    private Logger logger = LoggerFactory.getLogger(this);

    public static void main(String[] args){
        ZigZagConversion test = new ZigZagConversion();
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= 3; i ++){
            s.append("0");
        }
        test.convert("A", 3);
    }

    public String convert(String sourceStr, int numRows) {
        // 如果行数为1的情况下，直接返回
        // 如果字符串的长度比行数减1还小的话，直接返回
        if (numRows == 1 || sourceStr.length() < numRows - 1){
            return sourceStr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int x = (numRows - 1) * 2;
        for (int i = 0; i < numRows; i ++){
            if (i == 0 || i == numRows - 1){
                // 头尾行
                int p = i;
                while (p < sourceStr.length()){
                    logger.print(p + " ");
                    stringBuilder.append(sourceStr.charAt(p));
                    p += x;
                }
                logger.println("");
            }else {
                // 非头尾行
                logger.print(i + " ");
                stringBuilder.append(sourceStr.charAt(i));
                int k = 2 * numRows - i - 2;
                int j = i + x;
                int w = 0;
                while (k < sourceStr.length() || j < sourceStr.length()){
                    // 偶数的情况下，计算k，奇数计算j
                    if (w % 2 == 0){
                        if (k < sourceStr.length()){
                            logger.print(k + " ");
                            stringBuilder.append(sourceStr.charAt(k));
                            k += x;
                            w ++;
                        }
                    }else {
                        if (j < sourceStr.length()){
                            logger.print(j + " ");
                            stringBuilder.append(sourceStr.charAt(j));
                            j += x;
                            w ++;
                        }
                    }
                }
                logger.println("");
            }

        }
        logger.d("input: " + sourceStr);
        logger.d("output: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

}
