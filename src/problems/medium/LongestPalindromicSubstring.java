package problems.medium;

import utils.Logger;
import utils.LoggerFactory;

import java.util.HashMap;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    private Logger logger = LoggerFactory.getLogger(this);


    public static void main(String[] args){
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
//        test.solution1("abbacca");
//        test.isPalindromic("a");
//        test.isPalindromic("abab");
//        test.isPalindromic("abc");
        String s = "abbacca";
        test.longestPalindrome(s);
        test.longestPalindrome2(s);
    }

    public String longestPalindrome(String s) {
        return new Solution1().solution(s);
    }

    public String longestPalindrome2(String s) {
        return new Solution2().solution(s);
    }

    /**
     * 通过，但是，时间太长，大部分人只需要花 20ms以内
     * Runtime: 437 ms, faster than 12.29% of Java online submissions for Longest Palindromic Substring.
     * 算法：通过查找所有头尾相同的子串，然后对子串再做相同字符的头尾截取操作，然后再判断是否为最大子回文串，截取中，从右往左截取，如果遇到是回文串，则停止并返回
     * 耗时：对字符串的截取，反转判断，耗时较大
     */
    private class Solution1{

        /**
         * 复杂度分析：需要遍历一遍数组，用时O(n) 获取到的子串的最坏遍历情况是子串的长度，所以时间复杂度为 O(n)，加上通过String的index方法，需要的时间复杂度为 O(n)
         * 最坏的情况下，不计算String的index的方法，需要遍历的字符串需要时间为 (n+1)n/2 为O(n^2) 加上String的index，最坏情况下需要O(n^3) 所以效率低
         * @param sourceStr
         * @return
         */
        private String solution(String sourceStr){
            HashMap<String, Boolean> keyMap = new HashMap<>();
            String longestString = "";
            // 取相同头尾相同的字符串，用map去重复
            // 例如输入为 abca 则会取出 abca b c a
            for (int i = 0; i < sourceStr.length(); i ++){
                String subString = sourceStr.substring(i, sourceStr.lastIndexOf(sourceStr.charAt(i)) + 1);
                if (!keyMap.containsKey(subString)){
                    keyMap.put(subString, true);
                    String s = longestPalindrome(sourceStr.charAt(i), subString);
                    if (s.length() >= longestString.length()){
                        longestString = s;
                    }
                }
            }
            logger.d("longest: " + longestString);
            return longestString;
        }

        /**
         * 对头尾相同的字符串进行判断，取出最大的回文串
         * @param key
         * @param string
         * @return
         */
        private String longestPalindrome(char key, String string){
            // 如果字符串的长度大于1，则判断头尾相同的子串是否为回文串，如果是，则返回，停止了循环
            while (string.length() > 1){
                if (isPalindromic(string)){
                    return string;
                }
                // 去掉最后的字符，然后找出新的最后一个字符为key的子字符串
                string = string.substring(0, string.length() - 1);
                string = string.substring(0, string.lastIndexOf(key) + 1);
            }
            return string;
        }

        /**
         * 判断是否为回文串
         * @param s
         * @return
         */
        private boolean isPalindromic(String s){
            return s.equals(new StringBuilder(s).reverse().toString());
        }

    }

    /**
     * 解法2
     * Runtime: 12 ms, faster than 76.91% of Java online submissions for Longest Palindromic Substring.
     * 达到12ms了
     * 算法分析：遍历一遍字符串，对当前移动的指针下标进行左右两边延伸判断，分奇数和偶数回文串，用两个指针，左指针和右指针，各自向两个不同方向移动，如果到边则停止，每次移动都要判断是会否为相同的字符，如果不是也停止
     */
    private class Solution2{
        // 用来记录最大回文串的头尾下标，对原字符串而言
        int mFirstIndex = 0, mEndIndex = 0;

        private String solution(String sourceString){
            logger.d(sourceString);
            if (sourceString == null || "".equals(sourceString)){
                return "";
            }
            // 遍历字符串
            for (int i = 0; i < sourceString.length(); i ++){
                // 计算以当前字符为中心向两边拓展
                IndexResult result = find(i - 1, i + 1, sourceString);
                if (result.endIndex - result.startIndex >= mEndIndex - mFirstIndex){
                    mFirstIndex = result.startIndex;
                    mEndIndex = result.endIndex;
                }
                // 计算以当前字符为左侧开始，求偶数回文串的情况
                result = find(i, i + 1, sourceString);
                if (result.endIndex - result.startIndex >= mEndIndex - mFirstIndex){
                    mFirstIndex = result.startIndex;
                    mEndIndex = result.endIndex;
                }
            }
            logger.d(sourceString.substring(mFirstIndex, mEndIndex + 1));
            return sourceString.substring(mFirstIndex, mEndIndex + 1);
        }

        /**
         * 查找出当前以startIndex和endIndex开始延伸的下标，如果对应的字符不相同，则返回
         * @param startIndex
         * @param endIndex
         * @param sourceString
         * @return
         */
        private IndexResult find(int startIndex, int endIndex, String sourceString){
            IndexResult result = new IndexResult();
            while (startIndex >= 0 && endIndex < sourceString.length()){
                if (sourceString.charAt(startIndex) != sourceString.charAt(endIndex)){
                    break;
                }
                startIndex --;
                endIndex ++;
            }
            // 这里加一和减一的操作的原因是，find中当遇到不相同的字符时，因为上面已经做了 -1 和 +1操作，所以需要加回来
            result.startIndex = startIndex + 1;
            result.endIndex = endIndex - 1;
            return result;
        }

        private class IndexResult{
            int startIndex;
            int endIndex;
        }
    }


}
