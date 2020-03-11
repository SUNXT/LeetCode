package problems.medium;

import utils.Logger;
import utils.LoggerFactory;

/**
 * author: sunxuedian
 * create: 2020/3/6
 */
public class RankTeams_N1366 {

    private static Logger logger = LoggerFactory.getDefalutLogger();

    public static void main(String[] args){
        String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
        String[] votes1 = {"WXYZ","XYZW"};
        String[] votes2 = {"BCA","CAB","CBA","ABC","ACB","BAC"};
        String[] votes3 = {"FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"};
        String[] votes4 = {"AXYB","AYXB","AXYB","AYXB"};
//        for (String vote: votes3){
//            logger.d(vote);
//        }
        logger.d("输出：" + rankTeams(votes4));
        logger.d("期望：" + "AXYB");
//        int[] test = {1, 2, 4, 3, 5, 4, 5};
//        logger.d("" + findMaxIndex(test));
    }

    public static String rankTeams(String[] votes) {
        int vLength = votes.length;
        if (vLength == 1){
            return votes[0];
        }
        int teamNum = votes[0].length();
        if (teamNum == 1){
            return votes[0];
        }
        int[] charArray = new int[26];
        // 初始化字符下标数组
        for (int i = 0 ; i < 26; i ++){
            charArray[i] = i + 'A';
        }
        int[][] countArray = countArray(votes);
        printTempArray(-1, countArray, charArray);
        rank( 0, 0, 25, countArray, charArray);
        printTempArray(1, countArray, charArray);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < teamNum; i ++){
            builder.append((char)charArray[i]);
        }
        return builder.toString();
    }

    private static void rank(int row, int left, int right, int[][] countArray, int[] charArray){
        if (left < right){
            if (row == countArray.length){
                rank(0, left + 1, 25, countArray, charArray);
                return;
            }
            int[] sortArray = countArray[row];
            logger.println("排序，" + left + " - " + right + ", row = " + row);
            sortArray(left, right, countArray, sortArray, charArray);
            printTempArray(left, countArray, charArray);
            if (sortArray[left] > sortArray[left + 1]){
                logger.println("最后排序的行：" + row);
                logger.println("找到了：" + (char)charArray[left] + ", index = " + left);
                printTempArray(left, countArray, charArray);
                // 递归
                rank(0, left + 1, 25, countArray, charArray);
            }else {
                // 所以第二位也相同，此时，找出最后一个相同的下标，然后再做比较
                int lastIndex = right;
                for (int i = left + 1; i < right; i ++){
                    if (sortArray[i] > sortArray[i + 1]){
                        lastIndex = i;
                        break;
                    }
                }
                logger.println("存在相邻相同的情况: row = " + row + ", 找到index：" + lastIndex + ", 范围：[" + left + " - " + lastIndex + "]");
                printTempArray(left, countArray, charArray);
                logger.println();
                // 接下来继续递归
                rank( row + 1, left, lastIndex, countArray, charArray);
//                rank(true, row + 1, lastIndex + 1, right, countArray, charArray);
            }
        }
    }

    private static void sortArray(int left, int right, int[][] sortArrays, int[] sortArray, int[] charArray){
        for (int i = left; i <= right; i ++){
            int temp = sortArray[i];
            for (int j = i + 1; j <= right; j ++){
                if (sortArray[j] > temp){
                    temp = sortArray[j];
                    // swap
                    swap(sortArrays, i, j);
                    swap(charArray, i, j);
                }
            }
        }
    }

    public static void insertSort(int[] array){
        int length = array.length;
        int j,temp;
        for (int i = 1; i < length; i ++){
            temp = array[i];
            j = i - 1;
            while (j >= 0 && temp < array[j]){
                array[j+1] = array[j];
                j --;
            }
            array[j+1] = temp;
        }
    }

    private static void swap(int[][] a, int i, int j){
        for (int[] a1: a){
            swap(a1, i, j);
        }
    }

    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void printTempArray(int i, int[][] countArray, int[] charArray){
        logger.println("------ " + i + " ------");
        for (int charItem: charArray){
            logger.print(((char) charItem) + "\t");
        }
        logger.println();
        logger.printArray(countArray);
    }

    private static int findMaxIndex(int[] array){
        int index = 0;
        int max = array[0];
        for (int i = 1; i < array.length; i ++){
            if (array[i] > max){
                index = i;
                max = array[i];
            }
        }
        return index;
    }

    private static int[][] countArray(String[] votes){
        int teamNum = votes[0].length();
        int[][] array = new int[teamNum][26];
        for (int i = 0; i < teamNum; i ++){
            for (String vote: votes){
                array[i][vote.charAt(i) - 'A'] ++;
            }
        }
        return array;
    }

}
