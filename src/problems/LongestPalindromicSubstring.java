package problems;

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
        test.longestPalindrome("babadada");
    }

    public String longestPalindrome(String s) {
        return solution1(s);
    }

    /**
     * 通过，但是，时间太长，大部分人只需要花 20ms以内
     * Runtime: 437 ms, faster than 12.29% of Java online submissions for Longest Palindromic Substring.
     * 算法：通过查找所有头尾相同的子串，然后对子串再做相同字符的头尾截取操作，然后再判断是否为最大子回文串
     * @param sourceStr
     * @return
     */
    private String solution1(String sourceStr){
        HashMap<String, Boolean> keyMap = new HashMap<>();
        String longestString = "";
        for (int i = 0; i < sourceStr.length(); i ++){
            String subString = sourceStr.substring(i, sourceStr.lastIndexOf(sourceStr.charAt(i)) + 1);
            if (!keyMap.containsKey(subString)){
                keyMap.put(subString, true);
                String s = longestPalindrome(sourceStr.charAt(i), subString);
                if (s.length() > longestString.length()){
                    longestString = s;
                }
            }
        }
        logger.d("longest: " + longestString);
        return longestString;
    }

    private String longestPalindrome(char key, String string){
        while (string.length() > 1){
            if (isPalindromic(string)){
                return string;
            }
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
